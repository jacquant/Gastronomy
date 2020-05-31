package be.unamur.composition

/** The Product Class.
 *
 * @param id          : the id of the product
 * @param name        : the name of the product
 * @param ingredients : the ingredients that compose the product
 */
class Product(id: Int, name: String, ingredients: String) {
  private var nutrients: List[Nutrient] = Nil

  def getId: Int =
    id

  def getIngredients: String =
    ingredients

  def getName: String =
    name

  /** *
   * MISSION 1/
   * Complétez cette méthode
   * **/
  override def toString: String =
    "%s (%s)%f".format(name, expressNutrients, quality_indicator)

  /** Returns all the nutrients separate by a comma.
   *
   * @return join the elements of nutrients, separated by commas
   */
  private def expressNutrients: String =
    this.nutrients.mkString(", ")

  /** *
   * MISSION 2
   * La méthode suivante considère que la qualité d'un produit est 0.3 si on n'a pas d'infos sur ses nutriments ; sinon, c'est la moyenne des qualités des nutriments du produit.
   * Or dans les faits, avoir 0.1 mg de sucre ne devrait pas pénaliser la qualité du produit autant que, par exemple, avoir 10g de graisses.
   * Réécrivez la méthode d'une façon ou d'une autre pour qu'elle donne une meilleure approximation de la qualité du produit.
   * En fonction de votre algorithme, vous pouvez décider d'implémenter (et utiliser) ou non la méthode auxiliaire getNutrientQuality.
   * **/
  /** Determine the quality of a product by compute the average of nutrient quality.
   *
   * @return the average of nutrients's quality
   */
  private def quality_indicator: Float =
    if (nutrients.isEmpty) 0.3f
    else {
      nutrients
        .map(nutrient => nutrient.getValue)
        .foldLeft(0.0f)((value, _) => value / nutrients.length)
    }

  /** Update the nutrients attribute
   *
   * @param nutrientsMap : List[(Int, [[Nutrient]])]
   */
  def populateNutrients(nutrientsMap: List[(Int, Nutrient)]): Unit = {
    this.nutrients = nutrientsMap
      .filter(elem => elem._1 == id)
      .map(tuple => tuple._2)
  }

  /** Returns the nutrients attribute.
   *
   * @return list of [[Nutrient]]
   */
  def getNutrients: List[Nutrient] =
    this.nutrients
}