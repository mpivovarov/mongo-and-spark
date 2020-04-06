package my.app.mongoexample.mongo

import cats.effect.{ContextShift, IO}
import com.mongodb.reactivestreams.client.MongoClient
import javax.inject.{Inject, Singleton}
import my.app.mongoexample.mongo.codec.PatientCodec
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries, fromCodecs}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY

@Singleton
class PatientDAO @Inject()(val pers: MongoClient)(implicit val cs: ContextShift[IO]) extends IDAO {

  override protected def database: String = "patient"
  override protected def collection: String = "patients"

  override protected def codec: CodecRegistry =
    fromRegistries(
      fromProviders(PatientCodec.patientProvider)
//    , fromProviders(PatientCodec.covidStatusProvider)
    , fromProviders(PatientCodec.uuidProvider)
    , DEFAULT_CODEC_REGISTRY
    )
}
