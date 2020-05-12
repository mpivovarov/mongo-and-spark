package my.app.mongoexample

import cats.effect.{ContextShift, IO, Timer}
import com.google.inject.TypeLiteral
import com.mongodb.reactivestreams.client.MongoClient
import my.app.mongoexample.http.route.{ExampleRoute, HealthCheckRoute}
import my.app.mongoexample.http.{Routing, Server}
import net.codingwell.scalaguice.{ScalaModule, ScalaMultibinder}
import org.apache.spark.sql.SparkSession

class AppModule(pers: MongoClient, sc: SparkSession, cs: ContextShift[IO], timer: Timer[IO]) extends ScalaModule {

  override def configure(): Unit = {
    bind[Server]
    bind[MongoClient].toInstance(pers)
    bind[SparkSession].toInstance(sc)
    bind(new TypeLiteral[ContextShift[IO]](){}).toInstance(cs)
    bind(new TypeLiteral[Timer[IO]](){}).toInstance(timer)
    val multiBinding = ScalaMultibinder.newSetBinder[Routing](binder)
    multiBinding.addBinding.to[HealthCheckRoute]
    multiBinding.addBinding.to[ExampleRoute]
  }
}
