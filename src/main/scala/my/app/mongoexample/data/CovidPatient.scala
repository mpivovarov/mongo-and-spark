package my.app.mongoexample.data

import io.chrisdavenport.fuuid.FUUID
import my.app.mongoexample.data.gen.{Generator, RandomValueGenerator}

case class CovidPatient(
  id: FUUID
, name: String
, age: Int
, address: String
, chronic: Set[ChronicIllness]
, status: CovidStatus
)


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
}