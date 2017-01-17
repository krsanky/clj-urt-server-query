(ns org.oldcode.urt.urtctf
	(:require [org.oldcode.urt.server-query :as sq]
	          [org.oldcode.noise :as noise]))

(defn poll-for-urtctf-slot []
	(while
		(-> (sq/get-status-urtctf) 
			(sq/get-players)
			(count)
			(>= 15))
		(do
			(Thread/sleep 5000)
			(println "not yet...")))
	(println "ready..."))


