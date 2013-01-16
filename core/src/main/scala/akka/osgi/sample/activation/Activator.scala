package akka.osgi.sample.activation

import akka.osgi.ActorSystemActivator
import akka.osgi.sample.internal.{Table, Chopstick}
import org.osgi.framework.{ServiceRegistration, BundleContext}
import akka.actor.{Props, ActorSystem}
import java.util.{Dictionary, Properties}
import akka.osgi.sample.internal.Table
import akka.osgi.sample.service.DinningHakkersServiceImpl
import akka.osgi.sample.api.DinningHakkersService

class Activator extends ActorSystemActivator {

  var service: Option[ServiceRegistration] = None

  def configure(context: BundleContext, system: ActorSystem) {
    println("Core bundle configured")
    system.actorOf(Props[Table], "table")
    registerHakkersService(context, system)
    println("Hakker serice registred")
  }

  def registerHakkersService(context: BundleContext, system: ActorSystem) {

    val hakkersService = new DinningHakkersServiceImpl(system)

    service.foreach(_.unregister()) //Cleanup   //TODO required??
    service = Some(context.registerService(classOf[DinningHakkersService].getName, hakkersService, (new Properties()).asInstanceOf[Dictionary[String, Any]]))

  }


  override def stop(context: BundleContext) {
    unregisterHakkersService(context)
    println("Hakker service unregistred")
    super.stop(context)
  }

  def unregisterHakkersService(context: BundleContext) {
    service foreach (_.unregister())
  }

  override def getActorSystemName(context: BundleContext): String = "akka-osgi-sample"
}
