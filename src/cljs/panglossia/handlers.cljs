(ns panglossia.handlers
    (:require [re-frame.core :as re-frame]
              [panglossia.db :as db]
              [clojure.string :as string]))


(re-frame/register-handler
  :initialize-db
  (fn [db]
    (assoc db :words 
      {:foo {:word "Foo"
             :definitions ["A placeholder word used to indicate that the content is not important. Frequently used by programmers"]
             :synonyms ["Bar"]
             :show-definition false}
       :bar {:word "Bar"
             :definitions ["A placeholder word used to indicate that the content is not important. Frequently used by programmers"
                           "A place where people hold drinks. The content of the drink is not important. Frequently used by programmers"]
             :synonyms ["Foo" "Pub"]
             :show-definition false}
       :barrister {:word "Barrister"
             :definitions ["A suit who passes the bar. The content of the suit may not be important. Their clients frequently frequent bars"]
             :synonyms ["Lawyer"]
             :show-definition false}
       :lawyer {:word "Lawyer"
             :definitions ["A suit who attended law school. The content of the suit may not be important. Frequently sued by readers"]
             :synonyms ["Barrister"]
             :show-definition false}
       :judge {:word "Judge"
             :definitions ["A lawyer with an IQ of 100"]
             :synonyms ["Lawyer"]
             :show-definition false}
       :senator {:word "Senator"
             :definitions ["A lawyer with an IQ of 50"]
             :synonyms ["Lawyer"]
             :show-definition false}
       :publication {:word "Publication"
             :definitions ["A place where people put words. The content of the words may not be important. Frequently read by readers"]
             :synonyms [""]
             :show-definition false}
       :pub {:word "Pub"
             :definitions ["A place where people drink booze. The content of the drink is not important. Frequently used by programmers"]
             :synonyms ["Pub"]
             :show-definition false}}
      :search-input "")))

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

(defn handle-word-clicked
  [app-state [_ word]]
  (println "DAMMIT" word)
  (assoc-in app-state 
            [:words ((comp keyword string/lower-case) word) :show-definition] 
            (not (get-in app-state [:words ((comp keyword string/lower-case) word) :show-definition]))))

(re-frame/register-handler
  :word-clicked
  handle-word-clicked)