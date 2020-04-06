package my.app.mongoexample.mongo

import cats.effect.{ContextShift, IO}
import com.mongodb.reactivestreams.client.{MongoClient, MongoCollection}
import fs2.interop.reactivestreams._
import org.bson.Document
import org.bson.codecs.configuration.CodecRegistry

import scala.collection.JavaConverters._

trait IDAO {
  protected def pers: MongoClient

  protected def database: String

  protected def collection: String

  protected def codec: CodecRegistry

  implicit val cs: ContextShift[IO]

  private lazy val db: MongoCollection[Document] = pers
    .getDatabase(database)
    .getCollection(collection)
    .withCodecRegistry(codec)

  def insertOne(item: Document): IO[Unit] = {
    db
      .insertOne(item)
      .toStream[IO]
      .compile
      .drain
  }

  def insertMany(items: List[Document]): IO[Unit] = {
    db
      .insertMany(items.asJava)
      .toStream[IO]
      .compile
      .drain
  }

  def selectAll: IO[List[Document]] = {
    db
      .find()
      .toStream[IO]
      .compile
      .toList
  }
}
