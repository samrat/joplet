(ns joplet.app
  (:require [ring.util.response :as ring]
            [jopbox.client :as dropbox])
  (:use sandbar.stateful-session
        hiccup.core
        hiccup.page)
  (:load "dropbox_keys"))

(def consumer (dropbox/make-consumer dropbox-key
                                     dropbox-secret))

(defn login []
  (let [request-token (dropbox/fetch-request-token consumer)
        auth-url      (dropbox/authorization-url consumer
                                                 request-token)]
    (do (session-put! :request-token request-token)
        (ring/redirect auth-url))))

(defn auth []
  (let [request-token (session-get :request-token)
        resp (dropbox/fetch-access-token-response consumer
                                                  request-token)]
    (do (session-put! :resp resp)
        (session-put! :foo "bar")
        (ring/redirect "/"))))

(defn index []
  (if-let [resp (session-get :resp)]
    (let [info (dropbox/account-info consumer resp)
          display-name (:display_name info)]
      (html5 [:body
              [:div
               "Hey there, " display-name]
              [:div
               [:a {:href "/put-demo"} "Put a file"]]]))
    (ring/redirect "/login")))

(defn put-file []
  (if-let [resp (session-get :resp)]
    (do (spit "/tmp/joplet_test.txt" "Hello, this is a Joplet test")
        (dropbox/upload-file consumer
                             resp
                             :sandbox
                             "joplet_test.txt"
                             "/tmp/joplet_test.txt")
        (ring/redirect "/")))
  (ring/redirect "/login"))