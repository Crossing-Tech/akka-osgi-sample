package akka.osgi.sample.api

import akka.actor.ActorRef

trait DinningHakkersService {
  def getHacker(name: String, chairNumber: Int): ActorRef
}
