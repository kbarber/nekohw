(ns sh.bob.sh.nekohw.main
  (:use [neko.activity :only [defactivity set-content-view!]]
        [neko.threading :only [on-ui]]
        [neko.ui :only [make-ui]]
        [neko.application :only [defapplication]]
        [neko.debug :only [safe-for-ui]]
        [neko.notify :only [toast]]
        [neko.find-view :only [find-view]]))

(defapplication sh.bob.sh.nekohw.Application)

(def current-player (atom "X"))

(defn swap-current-player!
  "Swaps the current player from X to O or vice versa"
  []
  (swap! current-player (fn [v] (if (= v "X") "O" "X"))))

(defn reset-current-player!
  "Resets the current player back to X"
  []
  (swap! current-player (fn [_] "X")))

(defn tile-click!
  "An on-click handler that handles a user clicking on a tile"
  [btn]
  (when (= " " (.getText btn))
    (do
      (.setText btn @current-player)
      (swap-current-player!))))

(defn clear-all-tiles!
  "Clear all tile objects"
  [view]
  (.setText (::one (.getTag view)) " ")
  (.setText (::two (.getTag view)) " ")
  (.setText (::three (.getTag view)) " ")
  (.setText (::four (.getTag view)) " ")
  (.setText (::five (.getTag view)) " ")
  (.setText (::six (.getTag view)) " ")
  (.setText (::seven (.getTag view)) " ")
  (.setText (::eight (.getTag view)) " ")
  (.setText (::nine (.getTag view)) " "))

(defn reset-game!
  "Reset the game"
  [btn]
  (let [main-view (.getParent (.getParent btn))]
    (safe-for-ui (clear-all-tiles! main-view))
    (reset-current-player!)
    (toast "Game reset" :short)))

;; TODO: Just a place-holder, not used yet
(defn end-game!
  []
  (toast "End of game"))

(defn tile
  [id]
  [:button {:text " "
            :on-click tile-click!
            :id id}])

(defn tile-row
  [a b c]
  [:linear-layout {} (tile a) (tile b) (tile c)])

(def layout
  [:linear-layout {:orientation :vertical
                   :id-holder true}
   (tile-row ::one ::two ::three)
   (tile-row ::four ::five ::six)
   (tile-row ::seven ::eight ::nine)
   [:linear-layout {}
    [:button {:text "Reset"
              :on-click reset-game!}]]])

(defactivity sh.bob.sh.nekohw.HelloActivity
  :def activity
  :on-create
    (fn [this bundle]
      (on-ui
       (set-content-view! activity
        (make-ui layout)))))
