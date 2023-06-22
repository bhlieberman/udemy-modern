(ns bhlie.modern.greetings)

(defn Hello []
  [:<> (for [h (repeat 3 "hello there")] [:h1 h])])