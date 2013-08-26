(ns clj-airvantage-demo.api
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json]))

;;(def airvantage-url "http://na.airvantage.net/api")
(def airvantage-url "http://murphy:8080/api")

(defn get-body-json
  [url query-params]
  (let [response (client/get (str airvantage-url url)
                             { :query-params query-params })
        body (:body response)]
    ;; (print url response)
    (json/read-str body)))

(defn get-access-token
  [login password api-key api-secret]
  (let [body-json (get-body-json "/oauth/token"
                                 {"grant_type" "password"
                                  "username" login
                                  "password" password
                                  "client_id" api-key
                                  "client_secret" api-secret
                                  })]
    (body-json "access_token")))

(defn get-user-info
  [access-token]
  (get-body-json "/v1/users/current" { "access_token" access-token}))

(defn get-entities
  [type access-token filters]
  (get-body-json (str "/v1/" type)
                 (assoc filters "access_token" access-token)))

;; Etc... we could define as may functions as we want...
(def get-systems (partial get-entities "systems") )
(def get-applications (partial get-entities "applications") )
