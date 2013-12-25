package models

import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import Database.{ itemsTable, eventsTable }

case class Item(
  id: Long,
  name: String,
  size: String,
  price: Double,
  sold: Boolean,
  eventId: Long
) extends KeyedEntity[Long] {

  def halfPrice = price / 2

  override def toString = "%s, %s, %s, %d, %s, %s".format(id, name, size, price, sold, eventId)
}

object Item {

  def apply() = new Item(-1, "Test", "13T", 3.00, false, 1)

  def findAll = inTransaction {
    join(itemsTable, eventsTable)((item, event) =>
      select(item, event)
      orderBy(event.description, item.name)
      on(
        event.id === item.eventId
      )
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

  def saleSummary(items: List[Item]) = {
    def totals(ls: List[Item]) = (ls.size, ls.map(_.price).foldLeft(0.00)(_ + _))

    val (soldItems, unsoldItems) = items.partition(_.sold)
    Map(
      "Sold" -> totals(soldItems),
      "Unsold" -> totals(unsoldItems)
    )
  }
}

object ItemData extends Schema {

  def apply() = inTransaction {
    val staticItems = Set(
      Item(1, "brown dress", "2T", 6.00, false, 1),
      Item(2, "gap blue jeans", "3T", 8.00, false, 1),
      Item(3, "brown shoes", "13", 10.00, false, 2),
      Item(4, "kitty hello ribbon", "", 5.00, false, 2)
    )

    itemsTable.deleteWhere((_) => 1 === 1)
    staticItems.map(itemsTable.insert)
  }
}
