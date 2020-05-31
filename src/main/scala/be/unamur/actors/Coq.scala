package be.unamur.actors

import akka.actor.{Actor, ActorRef, Props}
import be.unamur.Main
import be.unamur.actors.dispenser._
import be.unamur.composition.Product
import be.unamur.menu.Menu

class Coq extends Actor {

  object Coq {
    var menu: Map[Int, Menu] = Map[Int, Menu]()
  }

  private val coqManipulator: ActorRef = context.actorOf(Props[CoqManipulator], "CoqManipulator")
  private val intendant: ActorRef = context.actorOf(Intendant.props(Main.products, Main.servingSize), "Intendant")
  private val proteinDispenser: ActorRef = context.actorOf(Props[ProteinDispenser], "ProteinDispenser")
  private val sodiumDispenser: ActorRef = context.actorOf(Props[SodiumDispenser], "SodiumDispenser")
  private val sugarDispenser: ActorRef = context.actorOf(Props[SugarDispenser], "SugarDispenser")

  private val requestId: Int = 0

  override def receive: Receive = onMessage(requestId)

  private def onMessage(requestId: Int): Receive = {
    case "faim" =>
      println("Coq a reçu une demande de menu ID = %d".format(requestId))
      intendant ! ProductRequest(requestId)
      Coq.menu = Coq.menu.+((requestId, new Menu))
      context.become(onMessage(requestId + 1))
    case IntendantQuantityResponse(requestId, quantities) =>
      Coq.menu.get(requestId) match {
        case Some(value) =>
          value.getMenu match {
            case Some(dishes) =>
              val response: StringBuilder = new StringBuilder
              response.append("%sRequestID %d: \n\t%s".format(Console.YELLOW, requestId, Console.RESET))
              response.append("%s: %s%s%s\n\t".format(dishes._1.getName, Console.RED, quantities._1, Console.RESET))
              response.append("%s: %s%s%s\n\t".format(dishes._2.getName, Console.RED, quantities._2, Console.RESET))
              response.append("%s: %s%s%s".format(dishes._3.getName, Console.RED, quantities._3, Console.RESET))
              coqManipulator ! Some(response.toString())
            case None => coqManipulator ! None
          }
        case None => coqManipulator ! None
      }
    case IntendantResponse(product, requestId) =>
      val menu = new Menu()
      menu.addProduct(product)
      Coq.menu = Coq.menu.+((requestId, menu))
      proteinDispenser ! ProductSuppRequest(requestId, product)
      sodiumDispenser ! ProductSuppRequest(requestId, product)
      sugarDispenser ! ProductSuppRequest(requestId, product)
    case ProteinDispenserResponse(product, requestId) =>
      addInMenu(product, requestId)
    case SodiumDispenserResponse(product, requestId) =>
      addInMenu(product, requestId)
    case SugarDispenserResponse(product, requestId) =>
      addInMenu(product, requestId)
  }

  private def addInMenu(product: Product, id: Int): Unit = {
    val menu = Coq.menu.get(id)
    menu match {
      case Some(value) =>
        if (value.addProduct(product)) {
          Coq.menu = Coq.menu.updated(id, value)
        } else {
          intendant ! QuantityRequest(id, value)
        }
      case None =>
    }

  }

}

