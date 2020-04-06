package my.app.mongoexample.service

import cats.effect.IO
import com.typesafe.scalalogging.LazyLogging
import javax.inject.{Inject, Singleton}
import my.app.mongoexample.data.CovidPatient

@Singleton
class PatientService @Inject()() extends LazyLogging {

  def generate(count: Int): IO[Unit] = {
    Range(0, count).map { _ =>
      val cp = CovidPatient.generate
      logger.info(s"generated $cp")
      cp
    }.toList
    // todo PatientDAO.insert
    IO.unit
  }
}
