package my.app.mongoexample.mongo

import cats.effect.{ContextShift, IO, Resource, Timer}
import com.mongodb.reactivestreams.client.{MongoClient, MongoClients}


object MongoPersistence {
  def getInstance(implicit c: ContextShift[IO], t: Timer[IO]): Resource[IO, MongoClient] = {
    val acquire: IO[MongoClient] = {
      IO.delay(MongoClients.create("mongodb://root:rootpassword@localhost/?authSource=admin"))
    }

    val release: MongoClient => IO[Unit] = { client: MongoClient =>
      IO.delay(client.close())
    }

    Resource.make[IO, MongoClient](acquire)(release)
  }
}
