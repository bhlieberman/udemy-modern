(ns bhlie.modern.app
  (:require [reagent.core :as r]
            [goog.dom :as gdom] 
            [bhlie.modern.pokedex :refer [display-app]]
            [bhlie.modern.slots :refer [slot-machine]]
            ["react-dom/client" :refer [createRoot]]))

(defonce root (createRoot (gdom/getElement "root")))

(defn main []
  [display-app])

(defn ^:dev/after-load init []
  (.render root (r/as-element [main])))