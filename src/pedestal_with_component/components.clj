(ns pedestal-with-component.components
  (:require [pedestal-with-component.service :as service]
            [com.stuartsierra.component :as component]
            [io.pedestal.http :as http]
            [pedestal-with-component.pedestal.pedestal :as pedestal]))

(defn new-system
  [env]
  (component/system-map
    :service-map {:env          env
                  ::http/routes service/routes
                  ::http/type   :jetty
                  ::http/port   8890
                  ::http/join?  false}
    :pedestal (component/using (pedestal/new-pedestal) [:service-map])))

(def create-and-start-system (new-system :dev))
