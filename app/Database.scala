package models

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema

object Database extends Schema {
	val eventsTable = table[Event]("events")
	val itemsTable = table[Item]("items")

	on(this.eventsTable) { i =>
		declare (
      i.id is(autoIncremented),
      i.description is(unique)
    )
	}

	on(this.itemsTable) { i =>
		declare (
      i.id is(autoIncremented),
      i.name is(unique)
    )
	}

  {
    drop
    create
  }
}
