(ns com.github.christianalexa.quest.db)

(def default-db
  {:name "re-frame"
   :backlog {"111" {:quest-name "Foo the food"
                    :position 1
                    :expanded? false
                    :subtasks {"333" {:subtask-name "bar" :position 1 :completed? false}
                               "444" {:subtask-name "baz" :position 2 :completed? false}}}

             "222" {:quest-name "Buzz the grass"
                    :position 1
                    :expanded? false
                    :subtasks {"555" {:subtask-name "aaa" :position 1 :completed? false}
                               "777" {:subtask-name "bbb" :position 2 :completed? false}
                               "888" {:subtask-name "ccc" :position 3 :completed? false}}}}})
