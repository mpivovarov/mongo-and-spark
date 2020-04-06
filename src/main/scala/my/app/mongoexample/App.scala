package my.app.mongoexample

import cats.effect.{ExitCode, IO, IOApp, Resource}
import com.google.inject.Guice
import my.app.mongoexample.http.Server
import my.app.mongoexample.mongo.MongoPersistence
import shapeless.{::, HNil}
import cats.implicits._
import com.mongodb.reactivestreams.client.MongoClient

object App extends IOApp {

  type Resources = MongoClient :: HNil

  override def run(args: List[String]): IO[ExitCode] = {
    createResources.use { runServices } map (_ => ExitCode.Success)
  }

  private def createResources: Resource[IO, Resources] = {
    for {
      pers <- MongoPersistence.getInstance
    } yield pers :: HNil
  }

  private def runServices(resources: Resources): IO[_] = {
    createServices(resources).parSequence
  }

  private def createServices(resources: Resources): List[IO[_]] = {
    val (pers :: HNil) = resources

    val module = new AppModule(pers, this.contextShift)
    val injector  = Guice.createInjector(module)
    val server    = injector.getInstance(classOf[Server])

    List(
      server.runForever
    )
  }
}
