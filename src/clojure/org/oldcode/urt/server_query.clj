(ns org.oldcode.urt.server-query 
	(:gen-class)
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

(defn get-status [host port] 
	(let [msg-raw (.getBytes "getstatus")
	      msg (byte-array (concat magic msg-raw))
          host- (InetAddress/getByName host)
	      send-socket (new DatagramSocket)
          send-packet (new DatagramPacket msg (count msg) host- port)]
		(.send send-socket send-packet)

;;         ByteArrayOutputStream baos = new ByteArrayOutputStream();
;;baos.write(dpacket.getData(), 0, dpacket.getLength());

		(let [buffer (byte-array 2048)
              dpacket (new DatagramPacket buffer (count buffer))]

 
			(loop [] 
				(let [stop (atom false)]
					(println "pre-try...")
					(try 
						(let [dpacket (new DatagramPacket buffer (count buffer) host- port)]
							;; Decrease value speeds things up, increase slows things down.
							(.setSoTimeout send-socket 10000)
							;; this is where we should preempt check for the exc. case:
							(.receive send-socket dpacket)
 
							(println (new String (.getData dpacket) 0 (.getLength dpacket))))
						(catch IOException e ;;we shouldn't use an exception for flow control
							(do					 
						    	(println (str "EXC: " (.getMessage e)))
							 	(swap! stop #(or % true)))))
					(if-not stop (recur)))))))
             

;;         //System.out.println(baos);
;;         byte[] bytes = baos.toByteArray();
;;         return bytes;
 
		;;	 (defn [old-val]
		;;		(or old-val true))			
