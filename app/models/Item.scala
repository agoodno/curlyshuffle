package models

import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import Database.itemsTable

case class Item(
  name: String,
  size: String,
  price: Double
) extends KeyedEntity[Long] {

  override val id = 0L

  def halfPrice = price / 2

  override def toString = "%s: %s : %s : %s".format(id, name, size, price)
}

object Item {

  ItemTestData.initData

  def findAll = inTransaction {
    from(itemsTable)(item =>
      select(item)
      orderBy(item.name)
    ).toList
  }

}

object ItemTestData {

  val staticItems = Set(
    Item("brown dress", "2T", 6.00),
    Item("gap blue jeans", "3T", 8.00),
    Item("brown shoes", "13", 10.00),
    Item("kitty hello ribbon", "", 5.00)
  )

  def initData = inTransaction(staticItems.map(itemsTable.insert))
}
