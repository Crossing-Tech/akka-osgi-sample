package akka.osgi.sample.service

import akka.osgi.sample.api.DinningHakkersService
import akka.actor.{Props, ActorSystem}
import akka.osgi.sample.internal.Hakker

class DinningHakkersServiceImpl(system: ActorSystem) extends DinningHakkersService {
  def getHacker(name: String, chairNumber: Int) = {
    system.actorOf(Props(new Hakker(name, chairNumber)))
  }
}
