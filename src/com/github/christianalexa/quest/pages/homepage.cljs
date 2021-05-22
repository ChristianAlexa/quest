(ns com.github.christianalexa.quest.pages.homepage
  (:require [com.github.christianalexa.quest.components.quests :refer [Backlog]]))

(defn HomePage []
  [:div
   [:h1 "HomePage"]
   [Backlog]])