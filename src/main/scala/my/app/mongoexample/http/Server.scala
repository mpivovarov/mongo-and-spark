package my.app.mongoexample.http


import cats.effect.{ContextShift, IO, Timer}
import javax.inject.Inject
import org.http4s.HttpRoutes
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import cats.implicits._

import scala.concurrent.ExecutionContext.global

class Server @Inject()(
 routeSet: Set[Routing]
) {
  implicit val cs: ContextShift[IO] = IO.contextShift(global)
  implicit val timer: Timer[IO] = IO.timer(global)

  def runForever: IO[Unit] = {

    val routes: HttpRoutes[IO] = routeSet.map(_.routes).toList.fold(HttpRoutes.empty[IO])(_ <+> _)

    def externalApi = {
      BlazeServerBuilder[IO].bindHttp(8000, "0.0.0.0")
        .withHttpApp(Router("" -> routes).orNotFound)
        .withNio2(true)
        .withBufferSize(16535)
        .withDefaultSocketKeepAlive
        .withTcpNoDelay(true)
        .resource
    }

    externalApi.use(_ => IO.never)
  }
}