package my.app.mongoexample.data

import java.util.UUID

import my.app.mongoexample.data.gen.RandomValueGenerator
import org.bson.Document

case class CovidPatient(
  id: Long
, name: String
, age: Int
, address: String
//, chronic: Set[ChronicIllness]
//, status: CovidStatus
) {
  lazy val _id: String = id.toString()

  def asDocument: Document = {
    new Document(_id, this.asInstanceOf[AnyRef])
  }

}


object CovidPatient extends RandomValueGenerator {
  def generate: CovidPatient = {
    CovidPatient(
      randomLong
    , randomString
    , randomInt(100)
    , randomString
//    , randomOf(ChronicIllness.values).toSet
//    , randomOneOf(CovidStatus.values)
    )
  }

  def apply(d: Document): CovidPatient = ???

}