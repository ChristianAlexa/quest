(ns com.github.christianalexa.quest.components.quests
  (:require [re-frame.core :as rf]))

;; -----------------------------------------------------------------------------
;; event handlers
(rf/reg-event-db
 ::toggle-expand-collapse
 (fn [db [_ uuid]]
   (update-in db [:backlog uuid :expanded?] not)))

(rf/reg-event-db
 ::toggle-subtask-completed-status
 (fn [db [_ quest-key subtask-key]]
   (update-in db [:backlog quest-key :subtasks subtask-key :completed?] not)))

;; ----------------------------------------------------------------------------- 
;; subs
(rf/reg-sub
 ::backlog
 (fn [db _]
   (:backlog db)))

(rf/reg-sub
 ::subtask-completed?
 (fn [db [_ quest-key subtask-key]]
   (get-in db [:backlog quest-key :subtasks subtask-key :completed?])))

;; ----------------------------------------------------------------------------- 
;; views

(defn QuestTextArea
  []
  [:textarea.textarea.is-small {:placeholder "Add quest description"
                                :rows 10}])

(defn SubTasks
  "SubTasks renders the subtasks for a given Quest."
  [quest-key quest-val]
  [:ul (doall (map-indexed (fn [idx [subtask-key subtask-val]]
                             (let [completed? @(rf/subscribe [::subtask-completed? quest-key subtask-key])]
                               [:li.level-left {:key idx}
                                [:label.checkbox
                                 [:input {:type "checkbox" :checked completed? :on-change #(rf/dispatch [::toggle-subtask-completed-status quest-key subtask-key])}]
                                 (:subtask-name subtask-val)]]))
                           (:subtasks quest-val)))])

(defn Quest
  "Quest renders an expandable list of information to complete the Quest (e.g. subtasks)"
  [quest-key quest-val]
  (let [num-completed (count (filter #(= true (:completed? (second %))) (:subtasks quest-val)))
        num-subtasks (count (:subtasks quest-val))]
    [:li
     [:div.level-left
      [:button.button.expand-collapse-btn-411db
       {:on-click #(rf/dispatch [::toggle-expand-collapse quest-key])}
       (if (:expanded? quest-val) "-" "+")]
      [:span.quest-item-d4b50 (:quest-name quest-val)]]
     (when (:expanded? quest-val)
       [:div.subtasks-87a43
        [QuestTextArea]
        [:p num-completed " of " num-subtasks " subtasks"]
        [SubTasks quest-key quest-val]])]))

(defn Backlog
  "Backlog renders a list of Quests."
  []
  (let [backlog @(rf/subscribe [::backlog])]
    [:div
     [:h1 "Quest Backlog"]
     [:ul (map (fn [[quest-key quest-val]]
                 ^{:key quest-key} [Quest quest-key quest-val])
               backlog)]]))