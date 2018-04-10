(ns nes.ui.google-maps
  #?(:cljs
     (:require
       ["google-maps-react" :refer [GoogleApiWrapper Map Marker]]
       [fulcro.client.dom :as dom]
       [nes.ui.utils :as utils]
       [fulcro.client.dom-common :as dom-common]
       goog.object)))

#?(:cljs
   (js/console.log "GAW" (GoogleApiWrapper #js {:apiKey "AIzaSyCgYhu25gFBLguTTF0kWBd9Qt6BrcDUxnM"})))

#?(:cljs
   (js/console.log "Map" Map))

#?(:cljs
   (def google-api-wrapper GoogleApiWrapper))

#?(:clj
   (def google-api-wrapper identity))

#?(:cljs
   (def google-map
     (utils/factory-apply Map)))

#?(:clj
   (def google-map nil))

#?(:cljs
   (def marker
     (utils/factory-apply Marker)))

#?(:clj
   (def marker nil))
