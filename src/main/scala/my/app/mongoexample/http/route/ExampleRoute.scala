package my.app.mongoexample.http.route

import cats.effect.IO
import javax.inject.Inject
import my.app.mongoexample.http.Routing
import my.app.mongoexample.service.PatientService
import org.http4s.HttpRoutes

class ExampleRoute @Inject()(service: PatientService) extends Routing {

  override def routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "api" / "patient" / "generate" / IntVar(count) =>
      service.generate(count).flatMap(_ => Ok())

    case GET -> Root / "api" / "patient" =>
      service.getAll.flatMap(_ => Ok())
  }
}
