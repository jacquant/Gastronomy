package be.unamur.actors.dispenser

import be.unamur.actors.{ProductSuppRequest, SodiumDispenserResponse}
import be.unamur.composition.{Nutrient, Product, Sodium}

class SodiumDispenser extends Dispenser {

  override def extract(product: Product): Option[Nutrient] = {
    product.getNutrients.filter(nutrient => {
      nutrient match {
        case Sodium(_, _) => true
        case _ => false
      }
    }) match {
      case Nil => None
      case ::(head, _) => Some(head)
    }
  }

  override def receive: Receive = {
    case ProductSuppRequest(requestId, product) =>
      sender() ! SodiumDispenserResponse(chooseGoodProduct(product), requestId)

  }
}

