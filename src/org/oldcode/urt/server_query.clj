(ns org.oldcode.urt.server-query
  (:gen-class)
  (:require [clojure.string :as str])
  (:import
   (java.net
    DatagramSocket
    DatagramPacket
    InetAddress)))

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
          timeouts 0
          dpacket (new DatagramPacket buffer (count buffer) host' port)]

			;; Decrease value speeds things up, increase slows things down.
      (.setSoTimeout socket 5000)

			;;try java.net.SocketTimeoutException ...
      (.receive socket dpacket)

      (new String (.getData dpacket) 0 (.getLength dpacket)))))

(defn get-status-urtctf []
  (get-status "216.52.148.134" 27961))

(defn get-vars [status-response]
  (-> status-response
      (str/split-lines)
      (get 1)
      (str/replace-first #"\\" "")
      (str/split #"\\")
      (apply hash-map)))

(defn clean-name [n]
  "remove quotes, strip outer spaces, and color codes."
  (str/replace n #"^\"|\"$" ""))

(defn get-players [status-response]
  (->> status-response
       (str/split-lines)
       (drop 2)
       (map #(str/split % #" " 3))
       (map #(vector (clean-name (nth % 2)) (first %) (second %)))))

(defn pp-player [player-list]
  (println (str (first player-list) " " (second player-list) " " (nth player-list 2))))

(defn pp-players [players-list]
  (doseq [p players-list] (pp-player p)))
