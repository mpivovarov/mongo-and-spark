package my.app.mongoexample.http.route

import cats.effect.IO
import javax.inject.{Inject, Named, Singleton}
import my.app.mongoexample.http.RouteResponse.successResponse
import my.app.mongoexample.http.Routing
import org.http4s.HttpRoutes

@Singleton
class HealthCheckRoute @Inject()() extends Routing {

  override def routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "api" / "health-check" =>
      Ok(successResponse(()))
  }

}
