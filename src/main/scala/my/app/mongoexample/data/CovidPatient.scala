package my.app.mongoexample.data

import java.util.UUID

import my.app.mongoexample.data.gen.RandomValueGenerator

case class CovidPatient(
  id: UUID
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