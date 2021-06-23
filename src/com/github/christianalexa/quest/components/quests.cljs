(ns com.github.christianalexa.quest.components.quests
  (:require [re-frame.core :as rf]))

(def max-allowed-quests
  "No more than n quests can be accepted at a time."
  25)

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

(rf/reg-sub
 ::num-accepeted-quests
 (fn [db _]
   (count (:backlog db))))

;; ----------------------------------------------------------------------------- 
;; views

(defn QuestDetails
  []
  [:div.quest-details-9412e
   [:p "The Kirin Tor of Dalaran"]
   [:p "Description"]
   [:p "Rewards"]
   [:div
    [:button.button.is-small.is-pulled-left.quest-book-button-57c78 {:on-click #(js/console.log "accepted")} "Accept"]
    [:button.button.is-small.is-pulled-right.quest-book-button-57c78 {:on-click #(js/console.log "declined")} "Decline"]]])

(defn QuestCounter
  "QuestCounter displays the number of accepted quests versus the allowed maximum."
  []
  (let [num-accepted-quests @(rf/subscribe [::num-accepeted-quests])]
    [:div.quest-counter-text-4581a
     [:p "Quests: "
      [:span num-accepted-quests "/" max-allowed-quests]]]))

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
    [:li.draggable-57u49 {:draggable true}
     [:div.level-left
      [:button.button.expand-collapse-btn-411db
       {:on-click #(rf/dispatch [::toggle-expand-collapse quest-key])}
       (if (:expanded? quest-val) "-" "+")]
      [:span.quest-item-d4b50.columns.is-vcentered (:quest-name quest-val)]]
     (when (:expanded? quest-val)
       [:div.subtasks-87a43
        [QuestTextArea]
        [:p num-completed "/" num-subtasks]
        [SubTasks quest-key quest-val]])]))

(defn Backlog
  "Backlog renders a list of Quests."
  []
  (let [backlog @(rf/subscribe [::backlog])]
    [:div.backlog-container-2359c
     [:ul (map (fn [[quest-key quest-val]]
                 ^{:key quest-key} [Quest quest-key quest-val])
               backlog)
      ;; TODO - add new quest
      ;; [:li
      ;;  [:button.button.expand-collapse-btn-411db  "+"]]
      ]]))