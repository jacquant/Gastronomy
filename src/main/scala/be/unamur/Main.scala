package be.unamur

import akka.actor.{ActorRef, ActorSystem, Props}
import be.unamur.actors.CoqManipulator
import be.unamur.composition.{Nutrient, Product, Unity}

import scala.io.StdIn
import scala.util.Try

object Main {
  val base_path: String = "src/main/scala/be/unamur/data/"
  // Get all the serving_size_file content
  val servingSize: Try[List[(Int, Float, Unity)]] =
    GastronomyDataExtractor.extractServingSize(base_path + "serving_size.csv")
  // Get all the products in the file as Products
  val products: List[Product] =
    GastronomyDataExtractor.extract_products(base_path + "products.csv")
  // Get all the nutrients from the file as tuples (product_id, data.Nutrient)
  val nutrients: List[(Int, Nutrient)] =
    GastronomyDataExtractor.extract_nutrients(base_path + "nutrients.csv")

  // Associates with each product its nutrients
  products foreach (_.populateNutrients(nutrients))

  /**
   *
   * @param args : Arguments given on the run
   */
  def main(args: Array[String]) {
    val system = ActorSystem("System")
    val master = system.actorOf(Props[CoqManipulator], "CoqManipulator")
    val key = convertMatch(StdIn.readLine("Entrez 'faim' pour avoir une proposition de menu\n"))
    println(menuAsk(key, master))
  }

  @scala.annotation.tailrec
  def menuAsk(key: (Boolean, String), master: ActorRef): String = {
    master ! key._2
    val new_key = convertMatch(StdIn.readLine("Pas satisfait ? Entrez 'faim' pour avoir un menu\n"))
    if (new_key._1) {
      menuAsk(new_key, master)
    } else {
      "Merci de votre commande!"
    }
  }

  def convertMatch(text: String): (Boolean, String) = {
    if (text.matches(".*(?i)faim.*")) {
      (true, "faim")
    } else if (text.matches(".*(?i)end.*")) {
      (false, "")
    }
    else {
      (true, text)
    }
  }
}
