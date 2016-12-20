(ns query-ut.core
	(:gen-class)
    (:import org.oldcode.urt.MessageResponse)
	(:require 
		[org.oldcode.urt.server-detail :as sd]
		[aleph.udp :as udp]
		[manifold.stream :as s]
		[manifold.deferred :as d]
		[clojure.string :as str]
		[byte-streams :as bs]))

(def gs-msg (byte-array [
	(unchecked-byte 0xff)
	(unchecked-byte 0xff)
	(unchecked-byte 0xff)
	(unchecked-byte 0xff)
	(byte \g)
	(byte \e)
	(byte \t)
	(byte \s)
	(byte \t)
	(byte \a)
	(byte \t)
	(byte \u)
	(byte \s)]))

(defn aleph-udp-test []
	;; 216.52.148.134:27961 	
	(println "udp...")
	(def server-port 27961)
	(let [skt @(udp/socket {:port 20000})]
		(s/put! skt {
			:host "216.52.148.134"
			:port 27961
			:message gs-msg})
		@(s/take! skt "X")))

;; urtctf east
(def addr (byte-array [
	(unchecked-byte 216)
	(byte 52)
	(unchecked-byte 148)
	(unchecked-byte 134)]))

(defn -main
	"I don't do a whole lot ... yet."
	[& args]
	(println "Hello, World!")
	(let [qr (new MessageResponse addr 27961)]
		(.sendMessage qr "getstatus")
		(def r (.getResponse qr))
		(println (String. r))))

