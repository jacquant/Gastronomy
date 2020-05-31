package be.unamur.composition

/** Sealed Abstract Class to Unity Class.
 *
 * @param name : the name of the Unity
 */
sealed abstract class Unity(name: String) {
  override def toString: String = name
}

case class MicroGram() extends Unity("mcg")

case class MilliGram() extends Unity("mg")

case class Gram() extends Unity("g")

case class KiloCalorie() extends Unity("kcal")

case class MilliLiter() extends Unity("ml")

case class InternationalUnit() extends Unity("IU")
