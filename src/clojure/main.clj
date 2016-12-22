(ns main
	(:gen-class)
	(:require [org.oldcode.urt.server-query :as sq]))

(defn -main [& args]
	(as-> (sq/get-status-urtctf) v
		(sq/get-players v)
		(sq/pp-players v)))
	

