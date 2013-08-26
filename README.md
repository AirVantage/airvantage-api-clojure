# airvantage-api-clojure

An example project of how to use Airvantage API in clojure

## Using

* You should have [Leiningen 2](http://leiningen.org/) installed.
* Create an Api client on Airvantage (so that you have a "client id" and a "client secret")
* Run the application :

```lein run```

* The server should be something like "http://na.airvantage.net"
* There is not a lot of error handling at the moment, so if things break... all apologies

## Self-executable jar

You can create an 'uberjar' for the project :

```
lein uberjar
java -jar target/airvantage-api-clojure-0.1.0-SNAPSHOT-standalone.jar
```

## Namespaces

### airvantage-api-clojure.api

This namespace contains generic functions to access Airvantage API using
the simpler OAuth "Resource Owner" flow.

It uses [clj-http.client](http://github.com/medSage/clj-http) to connect to the Airvantage server.

### airvantage-api-clojure.gui

A simple GUI to enter the credentials, using the wonderfull [seesaw](https://github.com/daveray/seesaw) library.

### airvantage-api-clojure.core

Main namespace, simply binds clicking on the button of the UI to getting an OAuth access token,
and querying Airvantage APIs for the logged user details, and the number of systems.


## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
