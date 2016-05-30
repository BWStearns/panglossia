(ns panglossia.handlers
    (:require [re-frame.core :as re-frame]
              [panglossia.db :as pgdb]
              [clojure.string :as string]))

(re-frame/register-handler
  :initialize-db
  (println pgdb/default-db)
  (fn [_ _]
    pgdb/default-db))

(re-frame/register-handler
 :set-active-panel
 (fn [app-state [_ active-panel]]
   (assoc app-state :active-panel active-panel)))


(defn handle-search-input-entered
  [app-state [_ search-input]]
  (assoc-in app-state [:search-input] search-input))

(re-frame/register-handler
  :search-input-entered
  handle-search-input-entered)

(defn handle-set-word-panel-word
  [app-state [_ word-slug]]
  (assoc-in app-state [:word-panel-word]
            (get-in app-state [:words (keyword word-slug)])))

(re-frame/register-handler
  :set-word-panel-word
  handle-set-word-panel-word)

(defn handle-set-edit-word
  [app-state [_ word-slug]]
  (assoc-in app-state [:edit-word]
            (get-in app-state [:words (keyword word-slug)])))

(re-frame/register-handler
  :set-edit-word
  handle-set-edit-word)

;; Needs to handle mutliple definitions. Whompwhomp.
(defn handle-word-updated
  [app-state [_ word-slug new-word-data]]
  (println (str "inhandler " word-slug (:definitions new-word-data)))
  (assoc-in app-state [:words (keyword word-slug)]
            new-word-data))

(re-frame/register-handler
  :word-updated
  handle-word-updated)
