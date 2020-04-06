package my.app.mongoexample.mongo

import cats.effect.{ContextShift, IO}
import com.mongodb.reactivestreams.client.MongoClient
import org.bson.Document


trait IDAO {
  protected def pers: MongoClient

  protected def database: String

  protected def collection: String

  implicit val cs: ContextShift[IO]

  import fs2.interop.reactivestreams._
  import org.bson.Document
  import scala.collection.JavaConverters._

  def insertOne(item: Document): IO[Unit] = {
    pers
      .getDatabase(database)
      .getCollection(collection)
      .insertOne(item)
      .toStream[IO]
      .compile
      .drain
  }

  def insertMany(items: List[Document]): IO[Unit] = {
    pers
      .getDatabase(database)
      .getCollection(collection)
      .insertMany(items.asJava)
      .toStream[IO]
      .compile
      .drain
  }

  def selectAll: IO[List[Document]] = {
    pers
      .getDatabase(database)
      .getCollection(collection)
      .find()
      .toStream[IO]
      .compile
      .toList
  }
}
