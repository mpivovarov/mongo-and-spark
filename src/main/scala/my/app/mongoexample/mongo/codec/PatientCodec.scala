package my.app.mongoexample.mongo.codec


import java.util.UUID

import enumeratum.{Enum, EnumEntry}
import my.app.mongoexample.data.{ChronicIllness, CovidPatient, CovidStatus}
import org.bson.{BsonReader, BsonWriter, UuidRepresentation}
import org.bson.codecs._
import org.bson.codecs.configuration.{CodecProvider, CodecRegistry}
import org.bson.codecs.pojo.PojoCodecProvider
import org.mongodb.scala.bson.codecs.Macros

import scala.collection.JavaConverters._

object PatientCodec {
  val uuidProvider = new UuidCodecProvider(UuidRepresentation.JAVA_LEGACY)
  val patientProvider = Macros.createCodecProvider[CovidPatient]()
//  val covidStatusProvider = new CovidStatusCodecProvider()
//  val chronicIllnessProvider = new CodecProvider {
//    override def get[T](clazz: Class[T], registry: CodecRegistry): Codec[T] = ???
//  }

//  class CovidStatusCodec extends Codec[CovidStatus] {
//    override def decode(reader: BsonReader, decoderContext: DecoderContext): CovidStatus = {
//      CovidStatus.withName(reader.readString())
//    }
//
//    override def encode(writer: BsonWriter, value: CovidStatus, encoderContext: EncoderContext): Unit = {
//      writer.writeString(value.entryName)
//    }
//
//    override def getEncoderClass: Class[CovidStatus] = Class[CovidStatus]
//  }
//
//  class CovidStatusCodecProvider extends CodecProvider {
//    override def get[T](clazz: Class[T], registry: CodecRegistry): Codec[T] = {
//      new CovidStatusCodec().asInstanceOf[Codec[T]]
//    }
//  }
}
