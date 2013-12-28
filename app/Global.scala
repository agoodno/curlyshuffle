import play.api.db.DB
import play.api.{Application, GlobalSettings}

import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.{Session, SessionFactory}

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    SessionFactory.concreteFactory = Some(() => {
      Session.create(DB.getConnection()(app), new PostgreSqlAdapter)
    })
  }
}
