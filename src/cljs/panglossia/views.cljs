(ns panglossia.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]
              [reagent.core  :as reagent :refer [atom]]
              [clojure.string :as string]))

;; links

(defn link-to-home-page []
  [re-com/hyperlink-href
   :label "go to Home Page"
   :href "#/"])

(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "go to About Page"
   :href "#/about"])

(defn link-to-word [word]
  [re-com/hyperlink-href
   :label word
   :href (str "#/word/" (string/lower-case word))])



;; definition components

(defn definition-card
  [definition]
  [:div (:entry definition)])

(defn definitions-list
  [definitions]
  [re-com/v-box
   :children [(map definition-card definitions)]])

(defn word-component
  []
  (let [expanded? (atom false)]
    (fn [{:keys [word definitions synonym-ids]}]
      (println definitions)
      [re-com/box
       :attr {:on-click #(swap! expanded? not)}
       :child [:div
               [link-to-word word]
                (when @expanded?
                  (definitions-list definitions))
               ]])))

(defn search-words
  []
  (let [search-input (re-frame/subscribe [:search-input])]
    (fn []
      [re-com/input-text
       :style {:margin-left "20px"
               :margin-top "20px"}
       :change-on-blur? false
       :model @search-input
       :on-change #(re-frame/dispatch [:search-input-entered %])])))

(defn words-component []
  (let [words (re-frame/subscribe [:search-result])]
    (println "SHIT")
    (println @words)
    [re-com/v-box
     :style {:border "1px solid blue"
             :border-radius "4px"
             :margin "20px"
             :padding "10px"}
     :gap "5px"
     :children [[re-com/title
                 :label "All the Words Fit to Define (TM)"
                 :level :level1]
                (map (fn [word] ^{:key (:word word)} [word-component word]) @words)]]))


;; home

(defn home-title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [re-com/title
       :label (str "Hello from " @name ". This is the Home Page.")
       :level :level1])))

(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :children [[home-title]
              [link-to-about-page]
              [search-words]
              [words-component]]])

;; word

(defn wp-definition
  [def-string]
  [re-com/box :child def-string])

(defn wp-synonyms
  [synonyms]
  [re-com/v-box
   :padding "10px"
   :children [[re-com/title
               :label "Synonyms"
               :level :level3]
              [re-com/h-box
               :children (mapv link-to-word synonyms)
               :gap "10px"
               :margin "20px"]]])

(defn wp-definitions
  [defs]
  [re-com/v-box
   :padding "10px"
   :children [[re-com/title
               :label "Definitions"
               :level :level3]
              [re-com/v-box
               :children (mapv wp-definition defs)
               :gap "10px"
               :margin "0px 0px 0px 20px"]]])

(defn wp-body [word]
  [re-com/v-box
   :gap "10px"
   :children [[wp-definitions (:definitions word)]
              [wp-synonyms (:synonyms word)]]])

(defn wp-title [word-string]
  [re-com/title
   :label (str "Entry for " word-string)
   :level :level1])

(defn word-panel []
  (let [word (re-frame/subscribe [:word-panel-word])]
    (fn []
      [re-com/v-box
       :gap "1em"
       :children [[wp-title (:word @word)]
                  [link-to-home-page]
                  [wp-body @word]]])))

;; edit-word

(defn word-definition-input [word]
  (let [initial-definition (first (:definitions word))
        slug (string/lower-case (:word word))]
    (fn []
      [re-com/input-textarea
       :change-on-blur? false
       :model (or initial-definition "")
       :on-change #(re-frame/dispatch [:word-updated slug (assoc word :definitions [%])])])))

(defn ewp-title [word-string]
  [re-com/title
   :label (str "Edit entry for " word-string)
   :level :level1])

(defn edit-word-panel []
  (let [word (re-frame/subscribe [:edit-word])]
    (fn []
      [re-com/v-box
       :gap "1em"
       :children [[ewp-title (:word @word)]
                  [link-to-home-page]
                  [word-definition-input @word]
                  [wp-body @word]]])))

;; about

(defn about-title []
  [re-com/title
   :label "This is the About Page."
   :level :level1])

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[about-title] [link-to-home-page]]])


;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :word-panel [] [word-panel])
(defmethod panels :edit-word-panel [] [edit-word-panel])
(defmethod panels :default [] [:div])

(defn show-panel
  [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :children [[panels @active-panel]]])))
