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
    val items = Range(0, count).map { _ =>
      val cp = CovidPatient.generate
      logger.info(s"generated $cp")
      cp
    }.toList
    dao.insertMany(items.map(i => new Document(i.id.toString(), Nil)))
  }

  def getAll: IO[List[Document]] = {
    dao.selectAll.map { d =>
      logger.info(s"$d")
      d
    }
  }
}
