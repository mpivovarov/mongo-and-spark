package my.app.mongoexample.mongo

import cats.effect.{ContextShift, IO}
import com.mongodb.reactivestreams.client.MongoClient
import javax.inject.{Inject, Singleton}

@Singleton
class PatientDAO @Inject()(val pers: MongoClient)(implicit val cs: ContextShift[IO]) extends IDAO {

  override protected val database: String = "patient"
  override protected val collection: String = "patients"
}
