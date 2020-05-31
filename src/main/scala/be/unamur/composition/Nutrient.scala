package be.unamur.composition

/** The sealed Abstract class for Nutrient objects.
 *
 * @param id    : the id of the nutrient
 * @param name  : the name of the nutrient
 * @param value : the amount of nutrient
 */
sealed abstract class Nutrient(id: Int, name: String = "", value: (Float, Unity)) {

  val daily_values: (Float, String)

  def getDailyValues: (Float, String) = daily_values

  def getValue: (Float, Unity) = value

  def getName: String = name
}

case class Biotin(id: Int, value: (Float, Unity)) extends Nutrient(id, "Biotin", value) {
  override val daily_values: (Float, String) = (30, "mcg")
}

case class Carbohydrates(id: Int, value: (Float, Unity)) extends Nutrient(id, "Carbohydrates", value) {
  override val daily_values: (Float, String) = (275, "g")
}

case class Cholesterol(id: Int, value: (Float, Unity)) extends Nutrient(id, "Cholesterol", value) {
  override val daily_values: (Float, String) = (300, "mg")
}

case class Copper(id: Int, value: (Float, Unity)) extends Nutrient(id, "Copper", value) {
  override val daily_values: (Float, String) = (900, "mcg")
}

case class FattyAcid(id: Int, value: (Float, Unity)) extends Nutrient(id, "FattyAcid", value) {
  override val daily_values: (Float, String) = (20, "g")
}

case class Fiber(id: Int, value: (Float, Unity)) extends Nutrient(id, "Fiber", value) {
  override val daily_values: (Float, String) = (28, "g")
}

case class Folate(id: Int, value: (Float, Unity)) extends Nutrient(id, "Folate", value) {
  override val daily_values: (Float, String) = (400, "mcg")
}

case class Iodine(id: Int, value: (Float, Unity)) extends Nutrient(id, "Iodine", value) {
  override val daily_values: (Float, String) = (150, "mcg")
}

case class Iron(id: Int, value: (Float, Unity)) extends Nutrient(id, "Iron", value) {
  override val daily_values: (Float, String) = (18, "mg")
}

case class Magnesium(id: Int, value: (Float, Unity)) extends Nutrient(id, "Magnesium", value) {
  override val daily_values: (Float, String) = (400, "mg")
}

case class Manganese(id: Int, value: (Float, Unity)) extends Nutrient(id, "Manganese", value) {
  override val daily_values: (Float, String) = (2.3f, "mg")
}

case class Niacin(id: Int, value: (Float, Unity)) extends Nutrient(id, "Niacin", value) {
  override val daily_values: (Float, String) = (16, "mg")
}

case class Other(id: Int, name: String, value: (Float, Unity)) extends Nutrient(id, name, value) {
  override val daily_values: (Float, String) = (0, "g")
}

case class PantothenicAcid(id: Int, value: (Float, Unity)) extends Nutrient(id, "PantothenicAcid", value) {
  override val daily_values: (Float, String) = (5, "mg")
}

case class Phosphorus(id: Int, value: (Float, Unity)) extends Nutrient(id, "Phosphorus", value) {
  override val daily_values: (Float, String) = (700, "mcg")
}

case class Potassium(id: Int, value: (Float, Unity)) extends Nutrient(id, "Potassium", value) {
  override val daily_values: (Float, String) = (4.7f, "g")
}

case class Protein(id: Int, value: (Float, Unity)) extends Nutrient(id, "Protein", value) {
  override val daily_values: (Float, String) = (50, "g")
}

case class Riboflavin(id: Int, value: (Float, Unity)) extends Nutrient(id, "Riboflavin", value) {
  override val daily_values: (Float, String) = (1.3f, "mg")
}

case class Selenium(id: Int, value: (Float, Unity)) extends Nutrient(id, "Selenium", value) {
  override val daily_values: (Float, String) = (55, "mcg")
}

case class Sodium(id: Int, value: (Float, Unity)) extends Nutrient(id, "Sodium", value) {
  override val daily_values: (Float, String) = (1.5f, "g")
}

case class Sugar(id: Int, value: (Float, Unity)) extends Nutrient(id, "Sugar", value) {
  override val daily_values: (Float, String) = (50, "g")
}

case class Thiamin(id: Int, value: (Float, Unity)) extends Nutrient(id, "Thiamin", value) {
  override val daily_values: (Float, String) = (1.2f, "mg")
}

case class TotalLipid(id: Int, value: (Float, Unity)) extends Nutrient(id, "TotalLipid", value) {
  override val daily_values: (Float, String) = (78, "g")
}

case class VitaminA(id: Int, value: (Float, Unity)) extends Nutrient(id, "VitaminA", value) {
  override val daily_values: (Float, String) = (900, "mcg")
}

case class VitaminB6(id: Int, value: (Float, Unity)) extends Nutrient(id, "VitaminB6", value) {
  override val daily_values: (Float, String) = (1.3f, "mg")
}

case class VitaminC(id: Int, value: (Float, Unity)) extends Nutrient(id, "VitaminC", value) {
  override val daily_values: (Float, String) = (90, "mg")
}

case class VitaminD(id: Int, value: (Float, Unity)) extends Nutrient(id, "VitaminD", value) {
  override val daily_values: (Float, String) = (20, "mcg")
}

case class VitaminE(id: Int, value: (Float, Unity)) extends Nutrient(id, "VitaminE", value) {
  override val daily_values: (Float, String) = (15, "mg")
}

case class VitaminK(id: Int, value: (Float, Unity)) extends Nutrient(id, "VitaminK", value) {
  override val daily_values: (Float, String) = (120, "mcg")
}

case class Zinc(id: Int, value: (Float, Unity)) extends Nutrient(id, "Zinc", value) {
  override val daily_values: (Float, String) = (11, "mg")
}
