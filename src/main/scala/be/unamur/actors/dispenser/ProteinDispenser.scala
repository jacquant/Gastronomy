package be.unamur.actors.dispenser

import be.unamur.actors.{ProductSuppRequest, ProteinDispenserResponse}
import be.unamur.composition.{Nutrient, Product, Protein}

class ProteinDispenser extends Dispenser {

  override def extract(product: Product): Option[Nutrient] = {
    product.getNutrients.filter(nutrient => {
      nutrient match {
        case Protein(_, _) => true
        case _ => false
      }
    }) match {
      case Nil => None
      case ::(head, _) => Some(head)
    }
  }

  override def receive: Receive = {
    case ProductSuppRequest(requestId, product) =>
      sender() ! ProteinDispenserResponse(chooseGoodProduct(product), requestId)

  }
}
