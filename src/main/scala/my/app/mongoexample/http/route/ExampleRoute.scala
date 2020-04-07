package my.app.mongoexample.http.route

import cats.effect.IO
import javax.inject.Inject
import my.app.mongoexample.data.CovidPatient
import my.app.mongoexample.http.Routing
import my.app.mongoexample.service.PatientService
import org.http4s.{EntityEncoder, HttpRoutes}
import org.http4s.circe.jsonEncoderOf
import io.circe.generic.auto._

class ExampleRoute @Inject()(service: PatientService) extends Routing {

  implicit val patientEncoder: EntityEncoder[IO, List[CovidPatient]] = jsonEncoderOf[IO, List[CovidPatient]]

  override def routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "api" / "patient" / "generate" / IntVar(count) =>
      service.generate(count).flatMap(_ => Ok())

    case GET -> Root / "api" / "patient" =>
      service.getAll.flatMap(Ok(_))
  }
}
