package my.app.mongoexample.http

import cats.effect.IO
import io.circe.{Encoder, Json}
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf
import io.circe.generic.auto._
import io.circe.syntax._

object RouteResponse {
  case class RouteResponse(success: Boolean, result: Json = Json.Null)
  case class RouteError(message: String =  "")
  implicit val responseEncoder: EntityEncoder[IO, RouteResponse] = jsonEncoderOf[IO, RouteResponse]

  def successResponse[A](body: A)(implicit e: Encoder[A]) = RouteResponse(success = true, body.asJson)
  def errorResponse(error: RouteError) = RouteResponse(success = false, error.asJson)
}
