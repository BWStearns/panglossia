(ns panglossia.db)

(def default-db
  {:words {:foo {:word "Foo"
                 :definitions ["A placeholder word used to indicate that the content is not important. Frequently used by programmers"]
                 :synonyms ["Bar"]}
           :bar {:word "Bar"
                 :definitions ["A placeholder word used to indicate that the content is not important. Frequently used by programmers"
                               "A place where people hold drinks. The content of the drink is not important. Frequently used by programmers"]
                 :synonyms ["Foo" "Pub"]}
           :barrister {:word "Barrister"
                       :definitions ["A suit who passes the bar. The content of the suit may not be important. Their clients frequently frequent bars"]
                       :synonyms ["Lawyer"]}
           :lawyer {:word "Lawyer"
                    :definitions ["A suit who attended law school. The content of the suit may not be important. Frequently sued by readers"]
                    :synonyms ["Barrister"]}
           :judge {:word "Judge"
                   :definitions ["A lawyer with an IQ of 100"]
                   :synonyms ["Lawyer"]}
           :senator {:word "Senator"
                     :definitions ["A lawyer with an IQ of 50"]
                     :synonyms ["Lawyer"]}
           :publication {:word "Publication"
                         :definitions ["A place where people put words. The content of the words may not be important. Frequently read by readers"]
                         :synonyms [""]}
           :pub {:word "Pub"
                 :definitions ["A place where people drink booze. The content of the drink is not important. Frequently used by programmers"]
                 :synonyms ["Bar"]}}
   :search-input ""
   :active-panel ""
   :word-panel-word ""
   :edit-word ""
   :unsaved-modified-words {}
   :name "Panglossia"})



