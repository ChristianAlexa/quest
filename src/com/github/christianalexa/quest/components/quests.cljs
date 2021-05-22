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
(defn Backlog []
  [:div
   (let [backlog @(rf/subscribe [::backlog])]
     [:h1 "Backlog"]
     [:ul (map-indexed (fn [idx [item-key item-val]]
                         [:li {:key idx}
                          [:div.level-left
                           [:button.button.expand-collapse-btn-411db
                            {:on-click #(rf/dispatch [::toggle-expand-collapse item-key])}
                            (if (:expanded? item-val) "-" "+")]
                           [:span.quest-item-d4b50 (:quest-name item-val)]]
                          (when (:expanded? item-val)
                            [:div.subtasks-87a43
                             [:p "subtasks"]
                             [:ul (map-indexed (fn [idx subtask]
                                                 [:li.level-left {:key idx}
                                                  [:label.checkbox
                                                   [:input {:type "checkbox"}]
                                                   (:subtask-name subtask)]])
                                               (:subtasks item-val))]])])
                       backlog)])])