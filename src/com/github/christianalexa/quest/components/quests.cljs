(ns com.github.christianalexa.quest.components.quests
  (:require [re-frame.core :as rf]))

;; -----------------------------------------------------------------------------
;; event handlers
(rf/reg-event-db
 ::toggle-expand-collapse
 (fn [db [_ uuid]]
   (update-in db [:backlog uuid :expanded?] not)))

;; ----------------------------------------------------------------------------- 
;; subs
(rf/reg-sub
 ::backlog
 (fn [db _]
   (:backlog db)))

;; ----------------------------------------------------------------------------- 
;; views

(defn SubTasks
  "SubTasks renders the subtasks for a given Quest."
  [item-val]
  [:div.subtasks-87a43
   [:p "subtasks"]
   [:ul (map-indexed (fn [idx subtask]
                       [:li.level-left {:key idx}
                        [:label.checkbox
                         [:input {:type "checkbox"}]
                         (:subtask-name subtask)]])
                     (:subtasks item-val))]])

(defn Quest
  "Quest renders an expandable list of information to complete the Quest (e.g. subtasks)"
  [idx item-key item-val]
  [:li {:key idx}
   [:div.level-left
    [:button.button.expand-collapse-btn-411db
     {:on-click #(rf/dispatch [::toggle-expand-collapse item-key])}
     (if (:expanded? item-val) "-" "+")]
    [:span.quest-item-d4b50 (:quest-name item-val)]]
   (when (:expanded? item-val)
     [SubTasks item-val])])

(defn Backlog
  "Backlog renders a list of Quests."
  []
  (let [backlog @(rf/subscribe [::backlog])]
    [:div
     [:h1 "Quest Backlog"]
     [:ul (map-indexed (fn [idx [item-key item-val]]
                         [Quest idx item-key item-val])
                       backlog)]]))