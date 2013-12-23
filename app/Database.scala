package models

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema

object Database extends Schema {
	val itemsTable = table[Item]("items")

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
