package my.app.mongoexample.spark

import cats.effect.{ContextShift, IO, Resource, Timer}
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object Spark {
  def getInstance(implicit c: ContextShift[IO], t: Timer[IO]): Resource[IO, SparkSession] = {
    val acquire: IO[SparkSession] = {
      IO.delay {
        SparkSession
          .builder()
          .master("local[*]")
          .appName("MongoAndSpark")
          .getOrCreate()
      }
    }

    val release: SparkSession => IO[Unit] = { sp: SparkSession =>
      IO.delay(sp.sparkContext.stop())
    }

    Resource.make[IO, SparkSession](acquire)(release)
  }
}
