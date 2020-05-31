package be.unamur.actors

import be.unamur.Main
import be.unamur.composition.Product

import scala.util.Random

object Helper {
  @scala.annotation.tailrec
  def provideRandomNonEmptyProduct(products: List[Product]): Product = {
    val product = products((new Random).nextInt.abs % products.length)
    if (product.getNutrients.isEmpty) {
      Helper.provideRandomNonEmptyProduct(Main.products)
    } else {
      product
    }
  }
}
