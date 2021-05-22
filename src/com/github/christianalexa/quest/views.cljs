(ns com.github.christianalexa.quest.views
  (:require
   [re-frame.core :as re-frame]
   [com.github.christianalexa.quest.subs :as subs]
   [com.github.christianalexa.quest.pages.homepage :refer [HomePage]]))

(defn main-panel []
  [HomePage])
