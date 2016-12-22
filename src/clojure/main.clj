(ns main
	(:gen-class)
    (:import org.oldcode.urt.MessageResponse)
	(:require [org.oldcode.urt.server-query :as sq]))

;; urtctf east 27961
(def addr (byte-array [
	(unchecked-byte 216)
	(unchecked-byte 52)
	(unchecked-byte 148)
	(unchecked-byte 134)]))

(defn query-urtctf []
	(let [qr (new MessageResponse addr 27961)]
		(.sendMessage qr "getstatus")
		(def r (.getResponse qr))
		(println (String. r))))

(defn -main [& args]
;;	(query-urtctf))
;;	(def status (sq/get-status "216.52.148.134" 27961))
	(def status (sq/get-status-urtctf))
	(println status))

