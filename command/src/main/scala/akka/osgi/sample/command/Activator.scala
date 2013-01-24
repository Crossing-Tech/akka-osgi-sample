/*
Copyright 2013 Crossing-Tech

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
   limitations under the License.
 */
package akka.osgi.sample.command

import org.osgi.framework.{ServiceEvent, BundleContext, BundleActivator}
import akka.osgi.sample.api.DinningHakkersService
import akka.actor.{ActorRef, PoisonPill}
import org.osgi.util.tracker.ServiceTracker

class Activator extends BundleActivator {
  println("Command Activator created")
  var hakker : Option[ActorRef] = None

  def start(context: BundleContext) {
    val logServiceTracker = new ServiceTracker(context, classOf[DinningHakkersService].getName, null)
    logServiceTracker.open()
   val service = Option(logServiceTracker.getService.asInstanceOf[DinningHakkersService])
    service.foreach(startHakker(_,  context.getBundle.getSymbolicName + ":" + context.getBundle.getBundleId))
 }

  def startHakker(service: DinningHakkersService, name: String){
    hakker = Some(service.getHacker(name, (math.floor(math.random * 5)).toInt))
  }

  def stop(context: BundleContext) {
    hakker.foreach(_ ! PoisonPill)
  }
}
