package my.app.mongoexample.service

import cats.effect.IO
import com.typesafe.scalalogging.LazyLogging
import javax.inject.{Inject, Singleton}
import my.app.mongoexample.data.CovidPatient
import my.app.mongoexample.mongo.PatientDAO
import org.bson.Document

@Singleton
class PatientService @Inject()(dao: PatientDAO) extends LazyLogging {

  def generate(count: Int): IO[Unit] = {
    val items = Range(0, count).toList.map { _ =>
      val cp = CovidPatient.generate
      logger.info(s"generated $cp")
      cp.asDocument
    }

    dao.insertMany(items)
  }

  def getAll: IO[List[Document]] = {
    dao.selectAll.map { d =>
      logger.info(s"$d")
      d
    }
  }
}
