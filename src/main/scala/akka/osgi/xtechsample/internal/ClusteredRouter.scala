package akka.osgi.xtechsample.internal

import akka.actor.Actor
import akka.cluster.Cluster

class ClusteredRouter extends Actor {
   val address = Cluster(context.system).selfAddress
   def receive = {
     case msg: String => msg + s" sent from Cluster($address)"
   }
 }
