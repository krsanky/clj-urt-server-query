(ns org.oldcode.urt.server-query 
	(:gen-class)
	(:require [clojure.string :as str])
	(:import 
		(java.net 
			DatagramSocket 
			DatagramPacket        
			InetAddress
			UnknownHostException)
		(java.io 
			IOException
			ByteArrayOutputStream)))

(def magic (byte-array (map unchecked-byte (repeat 4 0xff))))

(defn send-msg [socket msg host port]
	(let [msg' (->> msg 
				 (.getBytes)
				 (concat magic)
				 (byte-array))
          host' (InetAddress/getByName host)
          packet (new DatagramPacket msg' (count msg') host' port)]
		(.send socket packet)))
		
(defn get-status [host port] 
	(let [socket (new DatagramSocket)
	      host' (InetAddress/getByName host)]
		(send-msg socket "getstatus" host port)

		(let [buffer (byte-array 65507)
              dpacket (new DatagramPacket buffer (count buffer) host' port)]
 
			;; Decrease value speeds things up, increase slows things down.
			(.setSoTimeout socket 10000)
			;; this is where we should preempt check for the exc. case:
			(.receive socket dpacket)
			(new String (.getData dpacket) 0 (.getLength dpacket)))))

(defn get-status-urtctf []
	(get-status "216.52.148.134" 27961))

(defn get-vars [status-response]
	(as-> status-response v 
		(str/split-lines v)
		(get v 1)
		(str/replace-first v #"\\" "")
		(str/split v #"\\")
		(apply hash-map v)))		

(defn strip-quotes [s]
	(if (and (.startsWith s "\"")
             (.endsWith s "\""))
		(subs s 1 (dec (count s)))
		s))

(defn reorder-player-list [[score ping name-]]
	[(strip-quotes name-) score ping])

(defn get-players [status-response]
	(as-> status-response v
		(str/split-lines v)
		(drop 2 v)
		(map #(str/split % #" ") v)
		(map reorder-player-list v)))

(defn pp-player [player-list] 
	(println (str (first player-list) " " (second player-list) " " (nth player-list 2))))
(defn pp-players [players-list] 
	(doseq [p players-list] (pp-player p)))

