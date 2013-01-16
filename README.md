akka-osgi-sample
================
This project may be used to test akka bundles in OSGi Frameworks. The build tool (sbt for the moment) provide scripts to run in an OSGi Framework (Karaf only for the moment) a version of the DinningHakkers that runs on several nodes unsing the akka-cluster module.

Clustered DinningHakker
-----------------------

How to use it
-------------

just run:
``sbt clean``
``sbt package``
``sbt osgi-bundle``
``sbt osgi-run``
From this point, you may change the created bundles if you want to test other bundles. Afterwards, launch the OSGi Framework using
``./osgi-run.sh``
Then try to restart some bundles, to test the stability of the bundles:

``list`` to get the list of the bundles
``restart #bundle_number`` to restart the bundle using its ID
``exit`` or CTRL-D to exit the Karaf console