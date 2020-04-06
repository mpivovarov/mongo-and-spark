package my.app.mongoexample.http

import cats.effect.IO
import com.typesafe.scalalogging.LazyLogging
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

trait Routing extends Http4sDsl[IO] with LazyLogging {

  def routes: HttpRoutes[IO]
}
