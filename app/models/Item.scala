package models

case class Item(
  name: String,
  size: String,
  price: BigDecimal
) {
  def halfPrice = price / 2

  override def toString = "%s : %s : %s".format(name, size, price)
}

object Item {

  val staticItems = Set(
    Item("brown dress", "2T", 6.00),
    Item("gap blue jeans", "3T", 8.00),
    Item("brown shoes", "13", 10.00)
  )

  def findAll = staticItems.toList.sortBy(_.name)
}
