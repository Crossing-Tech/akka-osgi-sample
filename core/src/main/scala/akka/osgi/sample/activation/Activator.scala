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
