package models

import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import Database.itemsTable

case class Item(
  id: Long,
  name: String,
  size: String,
  price: Double
) extends KeyedEntity[Long] {

  def halfPrice = price / 2

  override def toString = "%s, %s, %s, %d".format(id, name, size, price)
}

object Item {

  def findAll = inTransaction {
    from(itemsTable)(item =>
      select(item)
      orderBy(item.name)
    ).toList
  }

  def find(id: Long) = inTransaction {
    itemsTable.lookup(id)
  }

  def insert(item: Item) = transaction {
    itemsTable.insert(item)
  }

  def update(item: Item) = transaction {
    itemsTable.update(item)
  }

  def delete(id: Long) = transaction {
    itemsTable.delete(id)
  }

}

object ItemData extends Schema {
  def apply() = inTransaction {
    val staticItems = Set(
      Item(1, "brown dress", "2T", 6.00),
      Item(2, "gap blue jeans", "3T", 8.00),
      Item(3, "brown shoes", "13", 10.00),
      Item(4, "kitty hello ribbon", "", 5.00)
    )

    itemsTable.deleteWhere((_) => 1 === 1)
    staticItems.map(itemsTable.insert)
  }
}
