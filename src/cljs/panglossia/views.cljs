(ns panglossia.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]
              [clojure.string :as string]))

;; definition components

(defn matches-query?
  [search-input word]
  (println "fuck" word)
  (if (not search-input)
    true
    (boolean (re-find (re-pattern (string/lower-case search-input))
                      (string/lower-case (:word word))))))

(defn word-definition-body
  [word]
  (if (:show-definition word)
    [:ul (map (fn [d] [:li d]) (:definitions word))]))

(defn word-component
  [word]
  [:li
   [:span {:on-click #(re-frame/dispatch [:word-clicked (:word word)])} (:word word)]
   [:div (word-definition-body word)]])

(defn words-component []
  (let [all-words (re-frame/subscribe [:words])
        search-input (re-frame/subscribe [:search-input])]
    (fn []
      [:div "All the Words!"
       [:ul (map word-component
                 (filter (partial matches-query? @search-input) @all-words))]])))

(defn search-words
  []
  (let [search-input (re-frame/subscribe [:search-input])]
    (fn []
      [re-com/input-text
       :change-on-blur? false
       :model @search-input
       :on-change #(re-frame/dispatch [:search-input-entered %])])))


;; home

(defn home-title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [re-com/title
       :label (str "Hello from " @name ". This is the Home Page.")
       :level :level1])))

(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "go to About Page"
   :href "#/about"])

(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :children [[home-title] [link-to-about-page]]])


;; about

(defn about-title []
  [re-com/title
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [re-com/hyperlink-href
   :label "go to Home Page"
   :href "#/"])

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[about-title] [link-to-home-page]]])


;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :default [] [:div])

(defn show-panel
  [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :children [[panels @active-panel] [search-words] [words-component]]])))
