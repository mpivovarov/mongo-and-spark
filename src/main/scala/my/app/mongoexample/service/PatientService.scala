package my.app.mongoexample.service

import cats.effect.{ContextShift, IO}
import com.typesafe.scalalogging.LazyLogging
import javax.inject.{Inject, Singleton}
import my.app.mongoexample.data.CovidPatient
import my.app.mongoexample.mongo.codec.PatientFormatter
import cats.implicits._
import my.app.mongoexample.mongo.dao.PatientDAO
import my.app.mongoexample.spark.CovidPatientSparkDTO
import org.bson.Document

@Singleton
class PatientService @Inject()(dao: PatientDAO)(implicit cs: ContextShift[IO]) extends LazyLogging {

  def generate(count: Int): IO[Unit] = {
    val items = Range(0, count).toList.map { _ =>
      val cp = CovidPatient.generate
      PatientFormatter.write(cp)
    }
    dao.insertMany(items)
  }

  private def selectAll[T](transform: List[Document] => IO[List[T]]) : IO[List[T]] = {
     for {
      documentList <- dao.selectAll
      patientList  <- transform(documentList)
    } yield patientList
  }

  def getAll: IO[List[CovidPatient]] = {

    def transform(list: List[Document]): IO[List[CovidPatient]] = {
      val eiths: List[Either[Throwable, CovidPatient]] = list.map(PatientFormatter.read)
      eiths.map {
        case Left(ex)     => IO.raiseError(ex)
        case Right(value) => IO.pure(value)
      }.parSequence
    }

    selectAll(transform)
  }

  def getAllSpark: IO[List[CovidPatientSparkDTO]] = {

    def transform(list: List[Document]): IO[List[CovidPatientSparkDTO]] = {
      val eiths: List[Either[Throwable, CovidPatient]] = list.map(PatientFormatter.read)
      eiths.map {
        case Left(ex)     => IO.raiseError(ex)
        case Right(value) => IO.pure(CovidPatientSparkDTO(value))
      }.parSequence
    }

    selectAll(transform)
  }
}
