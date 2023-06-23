(ns bhlie.modern.rando
  (:require [reagent.core :as r]))

(defn Rando []
  (let [rand (r/atom (rand-int 11))]
    (r/create-class {:reagent-render (fn [_]
                                       [:div [:h2 "Random number is: " @rand]
                                        [:h2 {:className (if (not= @rand 7) "hidden" "showing")} "YOU WIN!"]
                                        [:button {:on-click #(reset! rand (rand-int 11))
                                                  :className (if (= @rand 7) "hidden" "showing")} "CLICK ME!!!"]])
                     :display-name "Rando"})))

(defn die [{:keys [val rolling?]}]
  [:i {:className (str "fa-solid fa-dice-" val (when @rolling? " fa-shake") " fa-2xl")}])

(defn roll-dice []
  (let [roll-fn (fn [] (+ 1 (js/Math.floor (* 6 (js/Math.random)))))
        roll (r/atom (roll-fn))
        roll2 (r/atom (roll-fn))
        rolling? (r/atom false)
        class-name-mappings {1 "one" 2 "two" 3 "three" 4 "four" 5 "five" 6 "six"}]
    [:div {:className "RollDice"}
     [:div {:className "die"}
      [die {:val (get class-name-mappings @roll) :rolling? rolling?}]
      [die {:val (get class-name-mappings @roll2) :rolling? rolling?}]]
     [:button {:on-click (fn [e] (let [button (.-target e)]
                                   (.setAttribute button "disabled" "")
                                   (set! (.-textContent button) "ROLLING...")
                                   (js/setTimeout (fn [] 
                                                    (reset! roll (roll-fn))
                                                    (.removeAttribute button "disabled")
                                                    (set! (.-textContent button) "ROLL")) 450)
                                   (swap! rolling? not)))} "ROLL"]]))
