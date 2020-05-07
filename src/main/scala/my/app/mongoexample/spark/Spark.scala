package my.app.mongoexample.spark

import cats.effect.{ContextShift, IO, Resource, Timer}
import org.apache.spark.{SparkConf, SparkContext}

object Spark {
  def getInstance(implicit c: ContextShift[IO], t: Timer[IO]): Resource[IO, SparkContext] = {
    val acquire: IO[SparkContext] = {
      IO.delay {
        val conf = new SparkConf().setAppName("MongoAndSpark").setMaster("local[*]")
        new SparkContext(conf)
      }
    }

    val release: SparkContext => IO[Unit] = { sp: SparkContext =>
      IO.delay(sp.cancelAllJobs())
    }

    Resource.make[IO, SparkContext](acquire)(release)
  }
}
