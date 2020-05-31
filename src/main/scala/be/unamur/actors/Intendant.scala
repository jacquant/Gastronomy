package be.unamur.actors

import akka.actor.{Actor, Props}
import be.unamur.Main
import be.unamur.composition.{Product, Unity}
import be.unamur.menu.Menu

import scala.util.{Failure, Success, Try}


object Intendant {
  def props(products: List[Product], quantities: Try[List[(Int, Float, Unity)]]): Props =
    Props(new Intendant(products, quantities))
}

class Intendant(val products: List[Product], val quantities: Try[List[(Int, Float, Unity)]]) extends Actor {
  override def receive: Receive = {
    case ProductRequest(requestId) =>
      sender() ! IntendantResponse(Helper.provideRandomNonEmptyProduct(Main.products), requestId)
    case QuantityRequest(requestId, menu: Menu) =>
      quantities match {
        case Failure(exception) =>
          sender() ! exception.getMessage
        case Success(value) =>
          menu.getMenu match {
            case Some(productTuple) =>
              val v1 = value.filter(elem => elem._1 == productTuple._1.getId).head
              val v2 = value.filter(elem => elem._1 == productTuple._2.getId).head
              val v3 = value.filter(elem => elem._1 == productTuple._3.getId).head
              sender() ! IntendantQuantityResponse(requestId, ("%s %s".format(v1._2, v1._3),
                "%s %s".format(v2._2, v2._3), "%s %s".format(v3._2, v3._3)))
            case None => sender() ! "Il y a eu un problème"
          }
      }
  }
}

