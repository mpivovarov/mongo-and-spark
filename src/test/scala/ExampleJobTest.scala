import cats.effect.{ContextShift, IO, Timer}
import my.app.mongoexample.mongo.MongoPersistence
import my.app.mongoexample.mongo.dao.PatientDAO
import my.app.mongoexample.service.PatientService
import my.app.mongoexample.spark.{ExampleJob, Spark}
import org.scalatest.{BeforeAndAfterAll, WordSpec}

import scala.concurrent.ExecutionContext.global

class ExampleJobTest extends WordSpec with BeforeAndAfterAll {
  implicit val t: Timer[IO]         = IO.timer(global)
  implicit val cs: ContextShift[IO] = IO.contextShift(global)

  val (sparkSession, sparkShutdown) = Spark.getInstance.allocated.unsafeRunSync()
  val (mongoPers, mongoShutdown) = MongoPersistence.getInstance.allocated.unsafeRunSync()
  val patientDAO = new PatientDAO(mongoPers)
  val patientService = new PatientService(patientDAO)

  override def beforeAll(): Unit = {
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    (for {
      _ <- sparkShutdown
      _ <- mongoShutdown
    } yield ()).unsafeRunSync()
    super.afterAll()
  }

  "example job" should {
    "get data and transform to ds" in {
      val job = new ExampleJob(patientService, sparkSession)
      job.run().unsafeRunSync()
    }
  }
}
