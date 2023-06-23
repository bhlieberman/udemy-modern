(ns bhlie.modern.greetings)

(defn Hello [{:keys [children]}]
  [:<> (for [h (repeat 3 "hello there")] [:h1 h])])