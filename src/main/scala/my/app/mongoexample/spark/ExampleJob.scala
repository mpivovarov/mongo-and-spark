package my.app.mongoexample.spark
import cats.effect.IO
import com.typesafe.scalalogging.LazyLogging
import javax.inject.{Inject, Singleton}
import my.app.mongoexample.data.CovidStatus
import my.app.mongoexample.service.PatientService
import org.apache.spark.sql.functions.{col, _}
import org.apache.spark.sql.{Column, DataFrame, SparkSession}

@Singleton
class ExampleJob @Inject()(patientService: PatientService, spark: SparkSession) extends SparkJob with LazyLogging {
  override def run(args: Any*): IO[Unit] = {
    import spark.implicits._

    patientService.getAllSpark.flatMap { list: List[CovidPatientSparkDTO] =>
      val df = list.toDF("id","name","age","address","chronic","status")
      df.printSchema()
      df.show(10)
      val ndf = df.transform(transform())
      ndf.printSchema()
      ndf.show(10)
      IO.unit
    }
  }

  private def transform()(df: DataFrame): DataFrame = {
    df
      .filter(col("status") === CovidStatus.Died.entryName)
      .withColumn("killedBy", concat(
        lit("Killed by "),
        concat_ws(", ", col("chronic")))
      )
  }
}
