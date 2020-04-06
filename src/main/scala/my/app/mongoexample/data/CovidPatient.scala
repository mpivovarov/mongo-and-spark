package my.app.mongoexample.data

import io.chrisdavenport.fuuid.FUUID
import my.app.mongoexample.data.gen.RandomValueGenerator
import io.circe.syntax._
import io.circe.generic.auto._
import org.bson.Document

case class CovidPatient(
  id: FUUID
, name: String
, age: Int
, address: String
, chronic: Set[ChronicIllness]
, status: CovidStatus
) {
  def asDocument: Document = ???
}


object CovidPatient extends RandomValueGenerator {
  def generate: CovidPatient = {
    CovidPatient(
      randomUUID
    , randomString
    , randomInt(100)
    , randomString
    , randomOf(ChronicIllness.values).toSet
    , randomOneOf(CovidStatus.values)
    )
  }

  def apply(d: Document): CovidPatient = ???

}