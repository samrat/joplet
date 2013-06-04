(ns joplet.handler
  (:use compojure.core
        sandbar.stateful-session)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [joplet.app :as app]))

(defroutes app-routes
  (GET "/" [] (app/index))
  (GET "/auth" [] (app/auth))
  (GET "/login" [] (app/login))
  (GET "/put-demo" [] (app/put-file))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (wrap-stateful-session)))
