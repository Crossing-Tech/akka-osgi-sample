# akka-osgi-sample #

This is an akka sample for OSGi testing working with akka 2.1.

## project behaviour ##

1. The project gets connected to a given ActorSystem
2. It creates a ClusteredAware Router and a Routee
3. It sends a String message to every connected Routee through the Router

## setup ##

You may use either Maven or sbt to build this project:

### sbt ###
``$> sbt package``

``$> sbt osgi-bundle``

### Maven ###
``$> mvn install ``

## in OSGi ##

This project requires at least the following bundles, for example in Apache Karaf :
``karaf@root>``

``install http://repo1.maven.org/maven2/com/typesafe/akka/akka-actor_2.10/2.1.0/akka-actor_2.10-2.1.0.jar ``

``install http://repo1.maven.org/maven2/com/typesafe/akka/akka-osgi_2.10/2.1.0/akka-osgi_2.10-2.1.0.jar  ``

`` install http://repo1.maven.org/maven2/com/typesafe/akka/akka-remote_2.10/2.1.0/akka-remote_2.10-2.1.0.jar ``

``install http://repo1.maven.org/maven2/com/typesafe/akka/akka-cluster-experimental_2.10/2.1.0/akka-cluster-experimental_2.10-2.1.0.jar``

