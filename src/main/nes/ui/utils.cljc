(ns nes.ui.utils
  #?(:cljs
     (:require [fulcro.util]
       [fulcro.client.dom-common :as dom-common]
       [fulcro.client.dom :as dom])))

#?(:cljs
   (defn factory-apply
     [class]
     (fn [& args]
       (let [conformed-args (fulcro.util/conform! :fulcro.client.dom/dom-element-args args)
             {attrs :attrs
              children :children
              css :css} conformed-args
             children (mapv second children)
             attrs (or (second attrs) {})
             props (dom-common/add-kwprops-to-props attrs css)]
         (dom/create-element class (dom/convert-props props) (fulcro.util/force-children children))))))
