(ns panglossia.utils
  (:require #?(:cljs [clojure.string :as string]
               :clj [])))

;; Put things like slugify functions in here.
(defn slugify [s]
  #?@(:cljs (keyword (string/lowercase s))
      :clj (keyword (clojure.string/lower-case s))))
