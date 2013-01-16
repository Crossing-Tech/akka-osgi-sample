package akka.osgi.sample.command

import org.osgi.framework.{ServiceEvent, ServiceListener, BundleContext, BundleActivator}
import akka.osgi.sample.api.DinningHakkersService
import akka.actor.{ActorRef, PoisonPill}

class Activator extends BundleActivator {
  println("Command Activator created")
  var hakker : Option[ActorRef] = None

  def start(context: BundleContext) {
    println("Command Bundle started")
    val serviceListner: ServiceListener = new ServiceListener {
      def serviceChanged(event: ServiceEvent) {
        event.getType match {
          case ServiceEvent.REGISTERED => startHakker(context.getService(event.getServiceReference()).asInstanceOf[DinningHakkersService], context.getBundle.getSymbolicName + ":" + context.getBundle.getBundleId)
          case ServiceEvent.UNREGISTERING => println("lost DinningHakkerService")
        }
      }
    }
    val filter = "(objectclass=" + classOf[DinningHakkersService].getName() + ")"
    context.addServiceListener(serviceListner, filter)
    println("service filter: " + classOf[DinningHakkersService].getName())

    //Small trick to create an event if the service is registred before this start listing for
    Option(context.getServiceReference(classOf[DinningHakkersService].toString)).foreach(x => {
      serviceListner.serviceChanged(new ServiceEvent(ServiceEvent.REGISTERED, x))
    })
  }

  def startHakker(service: DinningHakkersService, name: String){
    println("got a DinningHakkerService")
    hakker = Some(service.getHacker(name, (math.floor(math.random * 5)).toInt))
  }

  def stop(context: BundleContext) {
    hakker.foreach(_ ! PoisonPill)
    println("Command Bundle stopped")
  }
}
