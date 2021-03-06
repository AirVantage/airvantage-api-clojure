(ns airvantage-api-clojure.core
  (:require [airvantage-api-clojure.api :as api])
  (:require [airvantage-api-clojure.gui :as gui])
  (:gen-class :main :true))

(defn token-from-ui
  []
  (let [{:keys [login pwd client-id client-secret]} (gui/credentials)]
    (api/get-access-token (gui/server) login pwd client-id client-secret)))

(defn load-user-info []
  (let [server (gui/server)
        token (token-from-ui)
        user-info (api/get-user-info server token)
        systems (api/get-systems server token {})]
    (gui/set-data! user-info systems)))

(defn -main
  [& args]
  (gui/show-gui! (fn [e] (load-user-info))))
