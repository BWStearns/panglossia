(ns panglossia.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [panglossia.handlers]
              [panglossia.subs]
              [panglossia.routes :as routes]
              [panglossia.views :as views]
              [panglossia.config :as config]))

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (routes/app-routes)
  (mount-root))
