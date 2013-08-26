(ns airvantage-api-clojure.gui
  (:use [seesaw.core])
  (:use [seesaw.mig]))

;; Example GUI
(def server-field (text "http://na.airvantage.net"))
(def client-id-field (text :tip "Enter client Id"))
(def client-secret-f (text :tip "Enter client secret"))
(def login-f (text :tip "Enter your user email"))
(def pwd-f (text :tip "Enter your password"))

(def grid (grid-panel :border "Login information"
                      :columns 2
                      :items ["Server" server-field
                              "Client id" client-id-field
                              "Client secret" client-secret-f
                              "Email" login-f
                              "Password" pwd-f]))

(def go-btn (button :text "Go"))

(def result-panel (grid-panel :border "User Info"
                              :columns 2
                              :items []))

(def f (frame :title "Airvantage API Example"
                :content (vertical-panel

                          :items [grid
                                  go-btn
                                  result-panel])
                                  ))

;; Suprised seesaw does not contain this.. anyway...
(defn clear! [container]
  (apply remove! container (select container [:*])))

(defn server
  "The name of the server (as entered by the user)"
  []
  (text server-field))

(defn credentials
  "A map of creadentials, as entered in the ui"
  []
  {:login (text login-f)
   :client-id (text client-id-field)
   :client-secret (text client-secret-f)
   :pwd (text pwd-f)
   })

(defn show-gui!
  "Display the gui, and add a listener on the button click"
  [on-click]
  (clear! result-panel)
  (-> f
      pack!
      show!)
  (listen
   go-btn :action on-click)
  f)

(defn set-data!
  [user-info systems]
  (clear! result-panel)
  (add! result-panel
        "Company name" (-> user-info (get "company") (get "name"))
        "User name" (-> user-info (get "name"))
        "Number of systems" (str (systems "count")))
  (pack! f))
