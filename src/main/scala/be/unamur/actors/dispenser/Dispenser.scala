package be.unamur.actors.dispenser

import akka.actor.Actor
import be.unamur.Main
import be.unamur.actors.Helper
import be.unamur.composition.{Nutrient, Product}

abstract class Dispenser extends Actor {

  def extract(product: Product): Option[Nutrient]

  def chooseGoodProduct(currentProduct: Product): Product = {
    val elementValue = extract_matching(currentProduct)
    val chosenProduct = Helper.provideRandomNonEmptyProduct(Main.products)
    val chosenElement = extract_matching(chosenProduct)
    if (chosenElement._1 + elementValue._1 > elementValue._2._1) {
      chooseGoodProduct(currentProduct)
    } else {
      chosenProduct
    }
  }

  private def extract_matching(product: Product): (Float, (Float, String)) = {
    extract(product) match {
      case Some(value) => (value.getValue._1, value.getDailyValues)
      case None => (0f, (0f, ""))
    }
  }
}
