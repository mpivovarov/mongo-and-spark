import sbt.Keys.libraryDependencies
import sbt._

object Dependencies {

  lazy val scalaTest   = "org.scalatest"     %% "scalatest"   % "3.0.8"
  lazy val scalaMock   = "org.scalamock"     %% "scalamock"   % "4.1.0"
  lazy val enumeratum  = "com.beachape"      %% "enumeratum"  % "1.5.13"
  lazy val flyway      = "org.flywaydb"       % "flyway-core" % "5.0.7"

  object Fuuid {
    lazy val fuuid       = "io.chrisdavenport" %% "fuuid"        % "0.2.0"
    lazy val fuuidoobie  = "io.chrisdavenport" %% "fuuid-doobie" % "0.2.0"
    lazy val all = fuuid :: fuuidoobie :: Nil
  }
  
  object Mongo {
    lazy val fs2Mongo = "org.lyranthe" %% "fs2-mongodb" % "0.5.0"
    lazy val all = fs2Mongo :: Nil

  }

  object Logging {
    lazy val scalaLogging   = "com.typesafe.scala-logging" %% "scala-logging"  % "3.9.2"
    lazy val logbackClassic = "ch.qos.logback"             % "logback-classic" % "1.2.3"
    lazy val all = scalaLogging :: logbackClassic :: Nil
  }
  
  object Http4s {
    val Version = "0.21.0-M5"
    lazy val http4sBlazeServer = "org.http4s"      %% "http4s-blaze-server" % Version
    lazy val http4sBlazeClient = "org.http4s"      %% "http4s-blaze-client" % Version
    lazy val http4sCirce       = "org.http4s"      %% "http4s-circe"        % Version
    lazy val http4sDsl         = "org.http4s"      %% "http4s-dsl"          % Version
    lazy val http4sMetrics     = "org.http4s"      %% "http4s-prometheus-metrics" % Version
    lazy val rho               = "org.http4s"      %% "rho-swagger"         % "0.20.0-M1"
    lazy val all = Seq(http4sBlazeClient, http4sBlazeServer, http4sCirce, http4sDsl, http4sMetrics, rho)
  }

  object Guice {
    lazy val scalaGuice = "net.codingwell" %% "scala-guice" % "4.2.2"
    lazy val all = Seq(scalaGuice)
  }
  
  object Circe {
    lazy val Version = "0.12.1"
    lazy val core    = "io.circe" %% "circe-core"            % Version
    lazy val generic = "io.circe" %% "circe-generic"         % Version
    lazy val extras  = "io.circe" %% "circe-generic-extras"  % Version
    lazy val parser  = "io.circe" %% "circe-parser"          % Version
    lazy val config  = "io.circe" %% "circe-config"          % "0.6.1"
    lazy val all = Seq(core, generic, extras, parser, config)
  }
  
}
