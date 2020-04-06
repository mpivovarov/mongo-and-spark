package my.app.mongoexample.data

import enumeratum.EnumEntry.Snakecase
import enumeratum._

sealed trait CovidStatus extends EnumEntry with Snakecase
object CovidStatus extends Enum[CovidStatus] with ReactiveMongoBsonEnum[CovidStatus] {
  case object NotInfectedYet extends CovidStatus
  case object Infected extends CovidStatus
  case object Cured extends CovidStatus
  case object Died extends CovidStatus
  override def values = findValues
}
