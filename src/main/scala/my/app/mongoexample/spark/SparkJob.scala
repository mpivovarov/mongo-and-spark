package my.app.mongoexample.spark

import cats.effect.IO

trait SparkJob {
  def run(args: Any*): IO[Unit]
}
