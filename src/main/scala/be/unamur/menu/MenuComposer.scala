package be.unamur.menu

import be.unamur.composition.Product

import scala.util.Random

/**
 *
 * @param products : the list of products available
 */
class MenuComposer(products: List[Product]) {

  def compose: String = {
    /** *
     * MISSION 2
     * Le procédé de be.unamur.composition est trop simpliste : sélectionner 3 produits au hasard pour les composer peut prendre longtemps avant de trouver un bon menu...
     * En plus, une bonne partie de cette méthode est hardcodée et non paramétrable.
     * Bref, qu'avez-vous en cuisine pour améliorer cet algorithme ?
     * **/
    val meal = new Meal()
    while (!meal.readyToCompute()) {
      val prod = products((new Random).nextInt.abs % products.length)
      meal.addProduct(prod)
    }

    meal.toString
  }
}