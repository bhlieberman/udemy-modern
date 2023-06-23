(ns bhlie.modern.pokedex
  (:require [reagent.core :as r]
            [clojure.set :refer [difference]]))

(def base-url "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon")

(def pokemon [{:id 4 :name "Charmander" :type "fire" :base-experience 62 :img (str base-url "/4.png")}
              {:id 7 :name "Squirtle" :type "water" :base-experience 63 :img (str base-url "/7.png")}
              {:id 11 :name "Metapod" :type "bug" :base-experience 72 :img (str base-url "/11.png")}
              {:id 12 :name "Butterfree" :type "flying" :base-experience 178 :img (str base-url "/12.png")}
              {:id 25 :name "Pikachu" :type "electric" :base-experience 112 :img (str base-url "/25.png")}
              {:id 39 :name "Jigglypuff" :type "normal" :base-experience 95 :img (str base-url "/39.png")}
              {:id 94 :name "Gengar" :type "poison" :base-experience 225 :img (str base-url "/94.png")}
              {:id 133 :name "Eevee" :type "normal" :base-experience 65 :img (str base-url "/133.png")}])

(defn pokecard [{:keys [id img name type base-experience]}]
  [:div {:className "pokemon"
         :key id}
   [:h3 name]
   [:img {:src img}]
   [:p {:className "poke-info"} "Type: " type]
   [:p {:className "poke-info"} "EXP: " base-experience]])

(defn pokedex [{:keys [pokemon is-winner]}]
  [:div {:className "pokedex"}
   [:h2 "Pokedex"]
   (for [p pokemon]
     [pokecard p])
   (cond-> [:span]
     (true? is-winner) (conj {:className "winner"} "Winner!")
     (false? is-winner) (conj {:className "loser"} "Loser!"))])

(defn display-app []
  (let [new-hand-xf (comp (random-sample 0.5) (take 4) (distinct))
        new-hand-1 (r/atom (into #{} new-hand-xf pokemon))
        new-hand-2 (r/atom (difference (set pokemon) @new-hand-1))
        total-exp-1 (reduce + 0 (map :base-experience @new-hand-1))
        total-exp-2 (reduce + 0 (map :base-experience @new-hand-2))]
    [:div
     [pokedex {:pokemon @new-hand-1 :total-exp total-exp-1 :is-winner (<= total-exp-2 total-exp-1)}]
     [pokedex {:pokemon @new-hand-2 :total-exp total-exp-2 :is-winner (<= total-exp-1 total-exp-2)}]]))

