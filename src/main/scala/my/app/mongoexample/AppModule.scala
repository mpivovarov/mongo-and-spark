package my.app.mongoexample

import cats.effect.{ContextShift, IO}
import com.google.inject.TypeLiteral
import com.mongodb.reactivestreams.client.MongoClient
import my.app.mongoexample.http.route.{ExampleRoute, HealthCheckRoute}
import my.app.mongoexample.http.{Routing, Server}
import net.codingwell.scalaguice.{ScalaModule, ScalaMultibinder}

class AppModule(pers: MongoClient, cs: ContextShift[IO]) extends ScalaModule {

  override def configure(): Unit = {
    bind[Server]
    bind[MongoClient].toInstance(pers)
    bind(new TypeLiteral[ContextShift[IO]](){}).toInstance(cs)
    val multiBinding = ScalaMultibinder.newSetBinder[Routing](binder)
    multiBinding.addBinding.to[HealthCheckRoute]
    multiBinding.addBinding.to[ExampleRoute]
  }
}
