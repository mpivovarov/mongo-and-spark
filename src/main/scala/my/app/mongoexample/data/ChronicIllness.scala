package my.app.mongoexample.data

import enumeratum.EnumEntry.Snakecase
import enumeratum._

sealed trait ChronicIllness extends EnumEntry with Snakecase
object ChronicIllness extends Enum[ChronicIllness] {
  case object Hypertension extends ChronicIllness
  case object Diabetes extends ChronicIllness
  case object HeartFailure extends ChronicIllness
  case object Hepatitis extends ChronicIllness
  override def values = findValues
}
