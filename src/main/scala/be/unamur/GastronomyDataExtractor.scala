package be.unamur

import be.unamur.composition._

import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.{Failure, Success, Try}


object GastronomyDataExtractor {
  /**
   *
   * @param path : the file that contents products's data
   * @return the build list of [[be.unamur.composition.Product]] objects created from the data of the file
   */
  def extract_products(path: String): List[Product] = {
    val source = Source.fromFile(path)
    var listProducts = new ListBuffer[Product]
    for (line <- source.getLines) {
      val cols = line.split(";").map(_.trim)
      val ingredients = if (cols.length > 7) cols(7) else ""
      listProducts += new Product(cols(0).toInt, cols(1), ingredients)
    }
    source.close()
    listProducts.toList
  }

  /** *
   * MISSION 1
   * Complétez cette méthode
   * In : la variable cols correspond à une ligne du fichier nutrients.csv : cols(0) est la première colonne, cols(1) la deuxième, etc.
   * Out : La méthode doit renvoyer un Nutrient du bon sous-type, en fonction des valeurs de nutrients.csv reçues en entrée (variable cols)
   * **/

  /** Extract nutrients from the file given.
   *
   * @param path : the file path
   * @return a List of productId and [[be.unamur.composition.Nutrient]] build
   */
  def extract_nutrients(path: String): List[(Int, Nutrient)] = {
    val source = Source.fromFile(path)
    val list = (for {
      row <- source.getLines()
      columns = row.split(";").map(_.trim)
      nutrient = createNutrientFromRow(columns)
      productId = columns(0).toInt
    } yield
      (productId, nutrient)).toList
    source.close()
    list
  }

  /** Take a row (Array of columns) and create the good Nutrient object.
   *
   * @param columns : the columns of the row to parse
   * @return the [[be.unamur.composition.Nutrient]] object created
   */
  private def createNutrientFromRow(columns: Array[String]): Nutrient = {
    val nutrient_value: (Float, Unity) = columns(5) match {
      case "mg_ATE" => (columns(4).toFloat, MilliGram())
      case "IU" => (columns(4).toFloat, InternationalUnit())
      case "mg" => (columns(4).toFloat, MilliGram())
      case "kcal" => (columns(4).toFloat, KiloCalorie())
      case "mcg" => (columns(4).toFloat, MicroGram())
      case "g" => (columns(4).toFloat, Gram())
    }
    val idProd = columns(0).toInt
    idProd match {
      case 203 => Protein(idProd, nutrient_value)
      case 204 => TotalLipid(idProd, nutrient_value)
      case 205 | 284 => Carbohydrates(idProd, nutrient_value)
      case 269 | 299 | 539 => Sugar(idProd, nutrient_value)
      case 291 | 295 | 297 => Fiber(idProd, nutrient_value)
      case 303 => Iron(idProd, nutrient_value)
      case 304 => Magnesium(idProd, nutrient_value)
      case 305 => Phosphorus(idProd, nutrient_value)
      case 306 => Potassium(idProd, nutrient_value)
      case 307 => Sodium(idProd, nutrient_value)
      case 309 => Zinc(idProd, nutrient_value)
      case 312 => Copper(idProd, nutrient_value)
      case 314 => Iodine(idProd, nutrient_value)
      case 315 => Manganese(idProd, nutrient_value)
      case 317 => Selenium(idProd, nutrient_value)
      case 318 => VitaminA(idProd, nutrient_value)
      case 324 => VitaminD(idProd, nutrient_value)
      case 394 => VitaminE(idProd, nutrient_value)
      case 401 => VitaminC(idProd, nutrient_value)
      case 404 => Thiamin(idProd, nutrient_value)
      case 405 => Riboflavin(idProd, nutrient_value)
      case 406 => Niacin(idProd, nutrient_value)
      case 410 => PantothenicAcid(idProd, nutrient_value)
      case 415 => VitaminB6(idProd, nutrient_value)
      case 416 => Biotin(idProd, nutrient_value)
      case 417 => Folate(idProd, nutrient_value)
      case 430 => VitaminK(idProd, nutrient_value)
      case 601 => Cholesterol(idProd, nutrient_value)
      case 605 | 606 | 645 | 646 => FattyAcid(idProd, nutrient_value)
      case _ => Other(idProd, columns(2), nutrient_value)
    }
  }

  /** Method to extract data from the serving_size like file.
   *
   * @param path : the path of the file to extract
   * @return a list of product with the weight and the [[be.unamur.composition.Unity]]
   */
  def extractServingSize(path: String): Try[List[(Int, Float, Unity)]] = {
    val source = Source.fromFile(path)
    val file = Try(source.getLines().drop(0).toList)
    source.close()
    file.flatMap(itr => {
      Try(itr.tail.map(line => {
        val row = line.split(";").map(_.trim)
        val productId = Try(row(0).toInt) match {
          case Failure(_) => 0
          case Success(value) => value
        }
        val weight = Try(row(1).toFloat) match {
          case Failure(_) => 0
          case Success(value) => value
        }
        val unity: Unity = row(2) match {
          case "g" => Gram()
          case "ml" => MilliLiter()
          case _ => InternationalUnit()
        }
        (productId, weight, unity)
      }))
    })
  }
}