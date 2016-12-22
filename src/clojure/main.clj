(ns main
	(:gen-class)
	(:require [org.oldcode.urt.server-query :as sq]))

(defn -main [& args]
	(-> (sq/get-status-urtctf) 
		(sq/get-players)
		(sq/pp-players)))
	

