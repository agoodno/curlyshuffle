package models

import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import Database.eventsTable

case class Event(
  id: Long,
  description: String,
  year: Int
) extends KeyedEntity[Long] {

  override def toString = "%s, %d, %s".format(id, description, year)
}

object Event {

  def apply() = new Event(-1, "New Sale", 2014)

  def findAll = inTransaction {
    from(eventsTable)(event =>
      select(event)
      orderBy(event.description)
    ).toList
  }

  def find(id: Long) = inTransaction {
    eventsTable.lookup(id)
  }

  def insert(event: Event) = transaction {
    eventsTable.insert(event)
  }

  def update(event: Event) = transaction {
    eventsTable.update(event)
  }

  def delete(id: Long) = transaction {
    eventsTable.delete(id)
  }
}

object EventData extends Schema {

  def apply() = inTransaction {
    val staticEvents = Set(
      Event(1, "Winter sale", 2013),
      Event(2, "Spring sale", 2014),
      Event(3, "Garage sale", 2014)
    )

    eventsTable.deleteWhere((_) => 1 === 1)
    staticEvents.map(eventsTable.insert)
  }
}
