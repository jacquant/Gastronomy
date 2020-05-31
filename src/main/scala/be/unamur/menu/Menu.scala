package be.unamur.menu

import be.unamur.composition.Product

class Menu {
  private var menu: (Product, Product, Product) = (null, null, null)

  override def toString: String =
    "(%s, %s, %s)".format(menu._1.getName, menu._2.getName, menu._3.getName)


  def addProduct(product: Product): Boolean = {
    if (available) {
      menu match {
        case (null, null, null) => menu = menu.copy(_1 = product)
        case (_: Product, null, null) => menu = menu.copy(_2 = product)
        case (_: Product, _: Product, null) => menu = menu.copy(_3 = product)
        case _ => return false
      }
      true
    } else {
      false
    }
  }

  private def available: Boolean = {
    menu match {
      case (_: Product, _: Product, _: Product) => false
      case _ => true
    }
  }

  def getMenu: Option[(Product, Product, Product)] = {
    menu match {
      case (_: Product, _: Product, _: Product) => Some(menu)
      case _ => None
    }
  }
}
