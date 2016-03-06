(ns jlsnake.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"
                          :snake [[1 1] [2 2] [3 3]]
                          }))

(def size 25)
(def unit-in-pixels 20)
(defn hello-world []
  [:div
   [:div {:style {:width            (* size unit-in-pixels)
                  :height           (* size unit-in-pixels)
                  :background-color "#ebebeb"}}]
   (map-indexed (fn [i [x y]]
          [:div {:style {
                         :background-color "black"
                         :width            unit-in-pixels
                         :height           unit-in-pixels
                         :left             (* x unit-in-pixels)
                         :top              (* y unit-in-pixels)
                         :position         "absolute"
                         }
                 :key i
                 }]
          ) (:snake @app-state))
   ])

(defn update-state [state]
  (println state)
  state
)

(defn game-loop []
  (swap! app-state update-state)
  (js/setTimeout game-loop 2000)
)

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(game-loop)
