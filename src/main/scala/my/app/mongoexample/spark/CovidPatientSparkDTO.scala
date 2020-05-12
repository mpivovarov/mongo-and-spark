package my.app.mongoexample.spark
import java.util.UUID

import my.app.mongoexample.data.{ChronicIllness, CovidPatient, CovidStatus}
import org.apache.spark.sql.Encoders

case class CovidPatientSparkDTO(
                                 id: String
                               , name: String
                               , age: Int
                               , address: String
                               , chronic: Set[String]
                               , status: String
                               ) {
  def toPatient: CovidPatient = CovidPatient(
    UUID.fromString(this.id)
  , name
  , age
  , address
  , chronic.map(ChronicIllness.withName)
  , CovidStatus.withName(status)
  )
}

object CovidPatientSparkDTO {
  def apply(c: CovidPatient): CovidPatientSparkDTO = CovidPatientSparkDTO(
    c.id.toString
  , c.name
  , c.age
  , c.address
  , c.chronic.map(_.entryName)
  , c.status.entryName)
}



