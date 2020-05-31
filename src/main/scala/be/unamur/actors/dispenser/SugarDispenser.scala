package be.unamur.actors.dispenser

import be.unamur.actors.{ProductSuppRequest, SugarDispenserResponse}
import be.unamur.composition.{Nutrient, Product, Sugar}

class SugarDispenser extends Dispenser {


  override def extract(product: Product): Option[Nutrient] = {
    product.getNutrients.filter(nutrient => {
      nutrient match {
        case Sugar(_, _) => true
        case _ => false
      }
    }) match {
      case Nil => None
      case ::(head, _) => Some(head)
    }
  }

  override def receive: Receive = {
    case ProductSuppRequest(requestId, product) =>
      sender() ! SugarDispenserResponse(chooseGoodProduct(product), requestId)

  }
}

