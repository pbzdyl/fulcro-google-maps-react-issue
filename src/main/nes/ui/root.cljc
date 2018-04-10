(ns nes.ui.root
  (:require
    [fulcro.client.mutations :as m]
    [fulcro.client.data-fetch :as df]
    #?(:cljs [fulcro.client.dom :as dom] :clj [fulcro.client.dom-server :as dom])
    [nes.api.mutations :as api]
    [fulcro.client.primitives :as prim :refer [defsc]]
    [fulcro.i18n :as i18n :refer [tr trf]]
    [nes.ui.semantic-ui :as su]))

;; The main UI of your application

(defsc Root [this props]
  (su/ui-container
    (su/ui-label-group
      (su/ui-label :#myid #js {:onClick #(println "Hello2!")} "Hi2")
      (su/ui-label :#myid #js {:onClick #(println "Hello!")} "Hi"))
    (su/ui-button :#myid2 {:onClick #(println "Hi")} "Hello")))
