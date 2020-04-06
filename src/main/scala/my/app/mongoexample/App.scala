package my.app.mongoexample

import cats.effect.{ExitCode, IO, IOApp}
import com.google.inject.Guice
import my.app.mongoexample.http.Server

object App extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val module = new AppModule
    val injector  = Guice.createInjector(module)
    val server    = injector.getInstance(classOf[Server])

    server.runForever.map(_ => ExitCode.Success)
  }
}
