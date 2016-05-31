(ns panglossia.db)

(def default-db
  {:words (sorted-map
            0 {:id 0
               :word "Foo"
               :synonym-ids [1]}
            1 {:id 1
               :word "Bar"
               :synonym-ids [0 7]}
            2 {:id 2
               :word "Barrister"
               :synonym-ids [3]}
            3 {:id 3
               :word "Lawyer"
               :synonym-ids [2]}
            4 {:id 4
               :word "Judge"
               :synonym-ids [3]}
            5 {:id 5
               :word "Senator"
               :synonym-ids [3]}
            6 {:id 6
               :word "Publication"
               :synonym-ids []}
            7 {:id 7
               :word "Pub"
               :synonym-ids [1]})
   :definitions (sorted-map
                  0 {:id 0
                     :entry "A placeholder word used to indicate that the content is not important. Frequently used by programmers"
                     :word-id 0}
                  1 {:id 1
                     :entry "A placeholder word used to indicate that the content is not important. Frequently used by programmers"
                     :word-id 1}
                  2 {:id 2
                     :entry "A place where people hold drinks. The content of the drink is not important. Frequently used by programmers"
                     :word-id 1}
                  3 {:id 3
                     :entry "A suit who passes the bar. The content of the suit may not be important. Their clients frequently frequent bars"
                     :word-id 2}
                  4 {:id 4
                     :entry "A suit who attended law school. The content of the suit may not be important. Frequently sued by readers"
                     :word-id 3}
                  5 {:id 5
                     :entry "A lawyer with an IQ of 100"
                     :word-id 4}
                  6 {:id 6
                     :entry "A lawyer with an IQ of 50"
                     :word-id 5}
                  7 {:id 7
                     :entry "A place where people put words. The content of the words may not be important. Frequently read by readers"
                     :word-id 6}
                  8 {:id 8
                     :entry "A place where people drink booze. The content of the drink is not important. Frequently used by programmers"
                     :word-id 7})
   :search-input ""
   :active-panel ""
   :word-panel-word ""
   :edit-word ""
   :unsaved-modified-words {}
   :name "Panglossia"})



