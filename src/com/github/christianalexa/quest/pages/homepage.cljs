(ns com.github.christianalexa.quest.pages.homepage
  (:require [com.github.christianalexa.quest.components.quests :refer [Backlog QuestCounter QuestDetails]]))

(defn HomePageHeader
  []
  [:h1.level-item.has-text-centered.main-title-392f1 "Classic Quest Log"])

(defn HomePageContentContainer
  []
  [:div.backlog-questbook-container-738d2.columns
   [:div.column
    [Backlog]]
   [:div.column
    [QuestDetails]]])

(defn HomePage []
  [:div.homepage-6198d
   [HomePageHeader]
   [QuestCounter]
   [HomePageContentContainer]])