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
   [:div {:style {
                  :background-color "black"
                  :width            unit-in-pixels
                  :height           unit-in-pixels
                  :left             (* (nth (nth (:snake @app-state) 0) 0) unit-in-pixels)
                  :top              (* (nth (nth (:snake @app-state) 0) 1) unit-in-pixels)
                  :position         "absolute"
                  }}]
   ])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
