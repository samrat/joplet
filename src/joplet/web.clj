(ns joplet.web
  (:use [ring.adapter.jetty :as ring]
        [joplet.handler :as joplet]))

(defn start [port]
  (run-jetty joplet/app {:port port :join? false}))

(defn -main []
  (let [port (Integer/parseInt 
               (or (System/getenv "PORT") "8080"))]
  (start port)))