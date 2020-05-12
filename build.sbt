import Dependencies._

name := "mongo-and-spark"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += scalaTest % Test
libraryDependencies += scalaMock % Test
libraryDependencies ++= Enumeratum.all
libraryDependencies ++= Http4s.all
libraryDependencies ++= Fuuid.all
libraryDependencies ++= Logging.all
libraryDependencies ++= Circe.all
libraryDependencies ++= Guice.all
libraryDependencies ++= Mongo.all
libraryDependencies ++= Streams.all
libraryDependencies ++= Spark.all

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Ypartial-unification",
  "-Xfatal-warnings"
//  "-Ylog-classpath",
)

//sparkVersion := "2.4.5"
//
//sparkComponents ++= Seq("sql")

