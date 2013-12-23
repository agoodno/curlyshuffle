//import play.Logger
import play.api.db.DB
import play.api.{Application, GlobalSettings}

import org.squeryl.adapters.H2Adapter
import org.squeryl.{Session, SessionFactory}

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    //Logger.debug("application starting up")
    SessionFactory.concreteFactory = Some(() => {
      Session.create(DB.getConnection()(app), new H2Adapter)
    })
  }
}
