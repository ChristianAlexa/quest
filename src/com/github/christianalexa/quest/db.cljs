(ns com.github.christianalexa.quest.db)

(def default-db
  {:name "re-frame"
   :backlog {"111" {:quest-name "Foo the food"
                    :position 1
                    :expanded? false
                    :subtasks [{:subtask-name "bar" :position 1}
                               {:subtask-name "baz" :position 2}]}

             "222" {:quest-name "Buzz the grass"
                    :position 1
                    :expanded? false
                    :subtasks [{:subtask-name "aaa" :position 1}
                               {:subtask-name "bbb" :position 2}]}}})
