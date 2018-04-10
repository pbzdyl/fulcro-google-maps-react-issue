(ns nes.stop
  (:require [devcards.core]
            [fulcro.client.cards :as cards :refer-macros [defcard-fulcro]]
            [nes.ui.components :as comp]))

(defcard-fulcro Stop
  "# Stop"
  (cards/make-root comp/Stop
    {:id 1
     :name "Some place"
     :description "Description placeholder"
     :location {:lat 37.778519
                :lon -122.405640}})
  {}
  {:inspect-data true})
