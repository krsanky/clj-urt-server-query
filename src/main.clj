(ns main
  (:gen-class)
  (:require [org.oldcode.urt.server-query :as sq]
            [org.oldcode.urt.urtctf :as urtctf]))

(defn -main [& args]
  (-> (sq/get-status-urtctf)
      (sq/get-players)
      (sq/pp-players))
  (urtctf/poll-for-urtctf-slot))
