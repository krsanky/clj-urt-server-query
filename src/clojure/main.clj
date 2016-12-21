(ns main
	(:gen-class)
    (:import org.oldcode.urt.MessageResponse)
	(:require 
		[org.oldcode.urt.server-detail :as sd]
		[org.oldcode.urt.server-query :as sq]))

;; urtctf east 27961
(def addr (byte-array [
	(unchecked-byte 216)
	(unchecked-byte 52)
	(unchecked-byte 148)
	(unchecked-byte 134)]))

(defn -main
	"I don't do a whole lot ... yet."
	[& args]
	(println "Hello, World!")
	(sq/test2)
	(let [qr (new MessageResponse addr 27961)]
		(.sendMessage qr "getstatus")
		(def r (.getResponse qr))
		(println (String. r))))

