(defproject joplet "0.1.0-SNAPSHOT"
  :description "A demo web-app using jopbox."
  :url "http://github.com/samrat/joplet"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [sandbar/sandbar "0.4.0-SNAPSHOT"]
                 [hiccup "1.0.3"]
                 [ring/ring-jetty-adapter "1.1.6"]
                 [jopbox "0.2.0"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler joplet.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
