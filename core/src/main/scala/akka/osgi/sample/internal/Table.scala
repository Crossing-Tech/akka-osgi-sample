package akka.osgi.sample.internal

import akka.actor.{Props, Actor}

/*
 *   ___                      _   _     _ _          ___        _               
 *  / __|___ _ _  _ _  ___ __| |_(_)_ _(_) |_ _  _  | __|_ _ __| |_ ___ _ _ _  _
 * | (__/ _ \ ' \| ' \/ -_) _|  _| \ V / |  _| || | | _/ _` / _|  _/ _ \ '_| || |
 *  \___\___/_||_|_||_\___\__|\__|_|\_/|_|\__|\_, | |_|\__,_\__|\__\___/_|  \_, |
 *                                            |__/                          |__/
 * Copyright (c) 2011-2013 Crossing-Tech TM Switzerland. All right reserved.
 */

class Table extends Actor {
  val chopsticks = for (i ← 1 to 5) yield context.actorOf(Props[Chopstick], "Chopstick" + i)

  def receive = {
    case x: Int => sender !(chopsticks(x), chopsticks(x + 1 % 5))
  }
}
