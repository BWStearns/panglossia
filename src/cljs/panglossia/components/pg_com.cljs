(ns panglossia.subs
  (:require [reagent.core :as reagent])
  (:require-macros [re-com.core :refer [handler-fn]])
  (:require [re-com.box      :refer [v-box box line flex-child-style]]
            [re-com.util     :refer [deep-merge]]
            [re-com.validate :refer [title-levels-list title-level-type? css-style?
                                     html-attr? string-or-hiccup?] :refer-macros [validate-args-macro]]))


