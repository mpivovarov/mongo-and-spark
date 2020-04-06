package my.app.mongoexample

import my.app.mongoexample.http.route.{ExampleRoute, HealthCheckRoute}
import my.app.mongoexample.http.{Routing, Server}
import net.codingwell.scalaguice.{ScalaModule, ScalaMultibinder}

class AppModule extends ScalaModule {

  override def configure(): Unit = {
    bind[Server]
    val multiBinding = ScalaMultibinder.newSetBinder[Routing](binder)
    multiBinding.addBinding.to[HealthCheckRoute]
    multiBinding.addBinding.to[ExampleRoute]
  }
}
