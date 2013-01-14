package akka.osgi.xtechsample.activation

import akka.osgi.ActorSystemActivator
import org.osgi.framework.BundleContext
import akka.actor.{Props, ActorSystem}
import akka.cluster.routing.{ClusterRouterSettings, ClusterRouterConfig}
import akka.routing.BroadcastRouter
import akka.osgi.xtechsample.internal.{ClusteredRoutee, ClusteredRouter}

class Activator extends ActorSystemActivator{
   def configure(context: BundleContext, system: ActorSystem) {
     val bundleId = context.getBundle().getBundleId
     val router = system.actorOf(Props[ClusteredRouter].withRouter(
       ClusterRouterConfig(BroadcastRouter(), ClusterRouterSettings(
         totalInstances = 100, routeesPath = "/user/routee",
         allowLocalRoutees = true))),
       name = "router")
     system.actorOf(Props[ClusteredRoutee], "routee")
     router ! s"hello, it's me OSGi Bundle($bundleId)"
   }
  override  def getActorSystemName(context: BundleContext): String = "xtechsample"
 }
