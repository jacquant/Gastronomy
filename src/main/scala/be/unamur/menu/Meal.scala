package be.unamur.menu

import be.unamur.composition.{Nutrient, Product}

class Meal {

  var products: (Product, Product, Product) = (null, null, null)
  private var offset = 0

  def readyToCompute(): Boolean = offset == 3

  def addProduct(product: Product): Unit = {
    products = copyProducts(product)
    offset match {
      case 0 =>
        offset += 1
      case 1 | 2 =>
        val mapNutrients = this.determineMenuQuality()
        if (determineIfOk(mapNutrients)) {
          offset += 1
        }
    }

  }

  private def determineIfOk(nutrientMap: Map[String, (Float, (Float, String))]): Boolean = {
    for (o <- nutrientMap) {
      if (o._2._1 > o._2._2._1 / 2.5)
        false
    }
    true
  }

  private def copyProducts(product: Product): (Product, Product, Product) = {
    println("%de produit: %s".format(offset, product))
    offset match {
      case 0 =>
        products.copy(_1 = product)
      case 1 =>
        products.copy(_2 = product)
      case 2 =>
        products.copy(_3 = product)
    }
  }

  /**
   * Utilisation d'une structure de donnée non mutable
   */
  private def determineMenuQuality(): Map[String, (Float, (Float, String))] = {
    val product_1: List[Nutrient] = products._1 match {
      case null => List[Nutrient]()
      case _ => products._1.getNutrients
    }
    val product_2: List[Nutrient] = products._2 match {
      case null => List[Nutrient]()
      case _ => products._2.getNutrients
    }
    val product_3: List[Nutrient] = products._3 match {
      case null => List[Nutrient]()
      case _ => products._3.getNutrients
    }

    val nutrientList: List[Nutrient] = product_1 ++ product_2 ++ product_3

    /**
     * Fonction de haut niveau sur une map
     */
    nutrientList.foldLeft(Map[String, (Float, (Float, String))]()) {
      (acc: Map[String, (Float, (Float, String))], nutrient) => {

        val intermediate: (Float, (Float, String)) = acc.getOrElse(nutrient.getName, (0, (0, "g")))
        val newValue: (Float, (Float, String)) = (intermediate._1 + nutrient.getValue._1, nutrient.daily_values)
        acc.+(nutrient.getName -> newValue)
      }
    }


  }

  override def toString: String = determineMenuQuality().foldLeft("") {
    (acc, element) => "%s%s -> %f\n".format(acc, element._1, element._2._1)
  }
}

