(ns bhlie.modern.app
  (:require [reagent.core :as r]
            [goog.dom :as gdom]
            [bhlie.modern.greetings :refer [Hello]]
            ["react-dom/client" :refer [createRoot]]))

(defonce root (createRoot (gdom/getElement "root")))
 
(defn ^:dev/after-load init []
  (.render root (r/as-element [Hello])))