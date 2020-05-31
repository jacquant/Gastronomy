package be.unamur.actors

import akka.actor.{Actor, ActorRef, Props}

class CoqManipulator extends Actor {
  val coq: ActorRef = context.actorOf(Props[Coq], "Coq")


  def receive: PartialFunction[Any, Unit] = {
    case "faim" => coq ! "faim"
    case Some(v: String) => println(v)
    case None => println("There were an error")
    case _ => println(" ")
  }
}
