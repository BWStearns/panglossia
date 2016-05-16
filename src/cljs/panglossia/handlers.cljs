(ns panglossia.handlers
    (:require [re-frame.core :as re-frame]
              [panglossia.db :as db]))

;; (re-frame/register-handler
;;  :initialize-db
;;  (fn  [_ _]
;;    db/default-db))

(re-frame/register-handler
  :initialize-db
  (fn [db _]
    (println "FOO")
    (assoc db :words [
                       {:word "Foo"
                        :definitions ["A placeholder word used to indicate that the content is not important. Frequently used by programmers"]
                        :synonyms ["Bar"]}
                       {:word "Bar"
                        :definitions ["A placeholder word used to indicate that the content is not important. Frequently used by programmers"
                                     "A place where people hold drinks. The content of the drink is not important. Frequently used by programmers"]
                        :synonyms ["Foo" "Pub"]}
                       {:word "Barrister"
                        :definitions ["A suit who passes the bar. The content of the suit may not be important. Their clients frequently frequent bars"]
                        :synonyms ["Lawyer"]}
                       {:word "Lawyer"
                        :definitions ["A suit who attended law school. The content of the suit may not be important. Frequently sued by readers"]
                        :synonyms ["Barrister"]}
                       {:word "Judge"
                        :definitions ["A lawyer with an IQ of 100"]
                        :synonyms ["Lawyer"]}
                       {:word "Senator"
                        :definitions ["A lawyer with an IQ of 50"]
                        :synonyms ["Lawyer"]}
                       {:word "Publication"
                        :definitions ["A place where people put words. The content of the words may not be important. Frequently read by readers"]
                        :synonyms [""]}
                       {:word "Pub"
                        :definitions ["A place where people drink booze. The content of the drink is not important. Frequently used by programmers"]
                        :synonyms ["Pub"]}])))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))


(defn handle-search-input-entered
  [app-state [_ search-input]]
  (assoc-in app-state [:search-input] search-input))

(re-frame/register-handler
  :search-input-entered
  handle-search-input-entered)
