(ns user
  (:require [clojure.tools.namespace.repl :refer [refresh]]
            [com.stuartsierra.component :as component]
            [lambdajam.launcher.dev-system :refer [onyx-dev-env]]))

(def system nil)

(defn init [n-peers]
  (alter-var-root #'system (constantly (onyx-dev-env n-peers))))

(defn start []
  (alter-var-root #'system component/start))

(defn stop []
  (alter-var-root #'system (fn [s] (when s (component/stop s)))))

(defn go [n-peers]
  (init n-peers)
  (start))

(defn reset []
  (stop)
  (refresh))
