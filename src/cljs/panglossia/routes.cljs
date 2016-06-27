(ns panglossia.routes
    (:require-macros [secretary.core :refer [defroute]])
    (:import goog.History)
    (:require [secretary.core :as secretary]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [re-frame.core :as re-frame]))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  ;; --------------------
  ;; define routes here
  (defroute "/" []
    (re-frame/dispatch [:set-active-panel :home-panel]))

  (defroute "/about" []
    (re-frame/dispatch [:set-active-panel :about-panel]))

  (defroute "/word/:word-slug" [word-slug]
    (re-frame/dispatch [:set-word-panel-word word-slug])
    (re-frame/dispatch [:set-active-panel :word-panel]))

  (defroute "/word/:word-slug/edit" [word-slug]
    (re-frame/dispatch [:set-edit-word word-slug])
    (re-frame/dispatch [:set-active-panel :edit-word-panel]))


  ;; --------------------
  (hook-browser-navigation!))
