package akka.osgi.xtechsample.internal

import akka.actor.Actor

class ClusteredRoutee extends Actor{
   def receive = {
     case msg: String => println(msg)
   }
 }
