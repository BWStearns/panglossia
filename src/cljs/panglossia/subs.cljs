(ns panglossia.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
  :name
  (fn [db]
    (reaction (:name @db))))

(re-frame/register-sub
  :active-panel
  (fn [db _]
    (reaction (:active-panel @db))))

(re-frame/register-sub
  :search-input
  (fn [db]
    (reaction (:search-input @db))))

(re-frame/register-sub
  :words
  (fn [db _]
    (reaction (vals (:words @db)))))

(re-frame/register-sub
  :search-result
  (fn [db _]
    (reaction (:search-result @db))))

(re-frame/register-sub
  :word-panel-word
  (fn [db _]
    (reaction (:word-panel-word @db))))

(re-frame/register-sub
  :edit-word
  (fn [db _]
    (reaction
      (merge
        {:word "" :definitions "" :synonyms []}
        (:edit-word @db)))))
