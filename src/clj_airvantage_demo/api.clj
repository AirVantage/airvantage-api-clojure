(ns clj-airvantage-demo.api
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json]))

(defn get-body-json
  [server url query-params]
  (let [response (client/get (str server "/api/" url)
                             { :query-params query-params })
        body (:body response)]
    (json/read-str body)))

(defn get-access-token
  [server login password api-key api-secret]
  (let [body-json (get-body-json server
                                 "/oauth/token"
                                 {"grant_type" "password"
                                  "username" login
                                  "password" password
                                  "client_id" api-key
                                  "client_secret" api-secret
                                  })]
    (body-json "access_token")))

(defn get-user-info
  [server access-token]
  (get-body-json server "/v1/users/current" { "access_token" access-token}))

(defn get-entities
  [type server access-token filters]
  (get-body-json server
                 (str "/v1/" type)
                 (assoc filters "access_token" access-token)))

;; Etc... we could define as may functions as we want...
(def get-systems (partial get-entities "systems") )
(def get-applications (partial get-entities "applications") )
