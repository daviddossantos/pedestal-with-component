(ns pedestal-with-component.pedestal.pedestal
  (:require [io.pedestal.http :as http]
            [com.stuartsierra.component :as component]))

(defn test?
  [service-map]
  (= :test (:env service-map)))

(defrecord Pedestal [service-map service]
  component/Lifecycle
  (start [this]
    (println "\nCreating your server...")
    (if service
      this
      (cond-> service-map
        true http/create-server
        (not (test? service-map)) http/start
        true (partial assoc this :service))))
  (stop [this]
    (println "\nStopping your server...")
    (when (and service (not (test? service-map)))
      (http/stop service))
    (assoc this :service nil)))

(defn new-pedestal
  []
  (map->Pedestal {}))
