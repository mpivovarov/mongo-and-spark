package my.app.mongoexample.mongo.codec

import java.util.UUID

import my.app.mongoexample.data.{ChronicIllness, CovidPatient, CovidStatus}
import org.bson.Document

import scala.util.Try
import scala.collection.JavaConverters._

object PatientFormatter {
  def read(d: Document): Either[Throwable, CovidPatient] = {
    Try {
      val chrons = d.getList("chronic", classOf[String]).asScala.toSet
      CovidPatient(
        UUID.fromString(d.getString("id"))
      , d.getString("name")
      , d.getInteger("age")
      , d.getString("address")
      , chrons.map(ChronicIllness.withName)
      , CovidStatus.withName(d.getString("status"))
      )
    }.toEither
  }

  def write(c: CovidPatient): Document = {
    val map: Map[String, AnyRef] = Map(
      "id"      -> c.id.toString
    , "name"    -> c.name
    , "age"     -> c.age.asInstanceOf[AnyRef]
    , "address" -> c.address
    , "chronic" -> c.chronic.map(_.entryName)
    , "status"  -> c.status.entryName
    )

    new Document(map.asJava)
  }
}
