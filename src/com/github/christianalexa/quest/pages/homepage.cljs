(ns com.github.christianalexa.quest.pages.homepage
  (:require [com.github.christianalexa.quest.components.quests :refer [Backlog QuestCounter QuestDetails]]))

(defn HomePage []
  [:div.homepage-6198d
   [:div.level
    [:h1.level-item.has-text-centered.main-title-392f1 "Classic Quest Log"]]
   [QuestCounter]
   [:div.level-left
    [:div.level-item
     [Backlog]]
    [:div.level-item
     [QuestDetails]]]])