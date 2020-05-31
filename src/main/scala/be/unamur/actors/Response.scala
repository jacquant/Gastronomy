package be.unamur.actors

import be.unamur.composition.Product

sealed abstract class Response(val requestId: Int)

case class IntendantResponse(product: Product, override val requestId: Int) extends Response(requestId)

case class SodiumDispenserResponse(product: Product, override val requestId: Int) extends Response(requestId)

case class SugarDispenserResponse(product: Product, override val requestId: Int) extends Response(requestId)

case class ProteinDispenserResponse(product: Product, override val requestId: Int) extends Response(requestId)

case class IntendantQuantityResponse(override val requestId: Int, quantities: (String, String, String)) extends Response(requestId)
