(ns bhlie.modern.slots
  (:require [reagent.core :as r]))

(defn machine [{:keys [props]}]
  (let [display @props
        win? (or (every? #{"🍊"} display)
                 (every? #{"🍇"} display)
                 (every? #{"🍒"} display))]
    [:div
     [:h1 (if win? "You win!" "You lose!")] 
     display]))

(defn slot-machine []
  (let [fruits ["🍊" "🍇" "🍒"]
        roll (repeatedly 3 #(rand-nth fruits))
        display (r/atom roll)]
    [:div
     [machine {:props display}]
     [:button {:on-click (fn [] (reset! display (repeatedly 3 #(rand-nth fruits))))} "ROLL"]]))