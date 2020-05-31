package be.unamur.actors

import be.unamur.composition.Product
import be.unamur.menu.Menu

sealed abstract class Request(val requestId: Int)

case class ProductRequest(override val requestId: Int) extends Request(requestId)

case class ProductSuppRequest(override val requestId: Int, product: Product) extends Request(requestId)

case class QuantityRequest(override val requestId: Int, menu: Menu) extends Request(requestId)
