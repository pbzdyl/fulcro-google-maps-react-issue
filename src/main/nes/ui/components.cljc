(ns nes.ui.components
  (:require
    [nes.ui.semantic-ui :as sui]
    #?(:cljs
       ["google-maps-react" :refer [GoogleApiWrapper Map]])
    [nes.ui.utils :as utils]
    [nes.ui.google-maps :as gmaps]
    [fulcro.client.primitives :as prim :refer [defsc]]
    #?(:cljs [fulcro.client.dom :as dom] :clj
    [fulcro.client.dom-server :as dom])))

#?(:clj
   (def GoogleApiWrapper (constantly nil)))

;; A good place to put reusable components
(defsc PlaceholderImage [this {:keys [w h label]}]
  (let [label (or label (str w "x" h))]
    (dom/svg #js {:width w :height h}
             (dom/rect #js {:width w :height h :style #js {:fill "rgb(200,200,200)"
                                                           :strokeWidth 2
                                                           :stroke "black"}})
             (dom/text #js {:textAnchor "middle" :x (/ w 2) :y (/ h 2)} label))))

(def ui-placeholder (prim/factory PlaceholderImage))

(defsc MyMap [this {:keys [lat lon]
                    :as props}]
  {:query [:lat :lon]}
  #?(:cljs
     (js/console.log "This:" this "Props:" props "Lat" lat "Lon" lon))
  (dom/div {:style {:width "250px" :height "250px"}}
    (gmaps/google-map {:zoom 14
                       :google (.-google props)
                       :initialCenter {:lat 37.778519
                                       :lng -122.405640}
                       :center {:lat 37.778519
                                :lng -122.405640}
                       :style {:width "90%" :height "90%"}
                       :clickableIcons true
                       :disableDefaultUI false}
      (gmaps/marker {;:title "Marker title"
                     :name "Marker name"
                     :position {:lat 49.9086951
                                :lng 20.197881}}))))

(def ui-my-map (prim/factory MyMap))

(defn ui-my-map-glue
  [js-props]
  (ui-my-map (js->clj js-props)))

(def ui-my-map-wrapped
  (let [HOC (GoogleApiWrapper #js {:apiKey "AIzaSyDAiFHA9fwVjW83jUFjgL43D_KP9EFcIfE"}) ;; HOC is a fn: ComponentClass -> WrappedComponentClass
        WrappedMyMap (HOC ui-my-map-glue)]                           ;; WrappedMyMap is a component class that wraps MyMap inside with some added logic for Google API initialization
    (utils/factory-apply WrappedMyMap)))

(defsc Stop [this {:keys [stop/id stop/name stop/description stop/location]}]
  {:ident [:stop/by-id :stop/id]
   :query [:stop/id :stop/name :stop/description {:stop/location (prim/get-query MyMap)}]
   :initial-state {:stop/id :param/id
                   :stop/name :param/name
                   :stop/description :param/name
                   :stop/location :param/location}}
  (sui/ui-card
    (sui/ui-card-content
      (ui-my-map-wrapped location)
      #_(sui/ui-popup {:trigger (sui/ui-button {:floated "right"
                                              :icon true
                                              :basic true
                                              :circular true}
                                             (sui/ui-icon {:name "map outline"}))
                     :hoverable true
                     :position "bottom left"}
                    (ui-my-map this location))
      (sui/ui-card-header
        name)
      (sui/ui-card-description
        description))))
