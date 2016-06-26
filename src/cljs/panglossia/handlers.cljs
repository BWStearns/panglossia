(ns panglossia.handlers
    (:require [re-frame.core :as re-frame]
              [panglossia.db :as pgdb]
              [clojure.string :as string]))

(re-frame/register-handler
  :initialize-db
  (println pgdb/default-db)
  (fn [_ _]
    (handle-search-input-entered pgdb/default-db "")))

;; HELPERS

(defn get-defs-for-word-id
  [db word-id]
  (filter $(= word-id (:word-id (second %)))
          (:definitions db)))

(defn all-words
  [db]
  (vals (:words db)))

(defn search-collection
  [db collection-key search-by term]
  (filter
    (fn [item]
       (re-find (re-pattern (str "(?i)" term)) (get item search-by)))
    (vals (get db collection-key))))

(defn search-words
  [db term]
  (search-collection db :words :word term))

(defn search-definitions
  [db term]
  (search-collection db :definitions :entry term))

(defn search-words-by-definitions
  [db term]
  (mapv (:words db) (map :word-id (search-definitions db term))))


;; HANDLERS

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))


(defn handle-search-input-entered
  [db [_ search-input]]
  (assoc-in db [:search-input] search-input)
  (assoc-in db [:search-result] (search-words db search-input)))

(re-frame/register-handler
  :search-input-entered
  handle-search-input-entered)

(defn handle-set-word-panel-word
  [db [_ word-slug]]
  (assoc-in db [:word-panel-word]
            (get-in db [:words (keyword word-slug)])))

(re-frame/register-handler
  :set-word-panel-word
  handle-set-word-panel-word)

(defn handle-set-edit-word
  [db [_ word-slug]]
  (assoc-in db [:edit-word]
            (get-in db [:words (keyword word-slug)])))

(re-frame/register-handler
  :set-edit-word
  handle-set-edit-word)

;; Needs to handle mutliple definitions. Whompwhomp.
(defn handle-word-updated
  [db [_ word-slug new-word-data]]
  (println (str "inhandler " word-slug (:definitions new-word-data)))
  (assoc-in db [:words (keyword word-slug)]
            new-word-data))

(re-frame/register-handler
  :word-updated
  handle-word-updated)

