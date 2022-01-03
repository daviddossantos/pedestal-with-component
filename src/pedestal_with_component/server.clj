(ns pedestal-with-component.server
  (:require [pedestal-with-component.components :as components]
            [com.stuartsierra.component :as component]))

(def run-dev (alter-var-root #'components/create-and-start-system component/start))
(def stop (alter-var-root #'components/create-and-start-system component/stop))
