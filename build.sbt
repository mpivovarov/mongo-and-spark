import Dependencies._

name := "mongo-and-spark"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies += scalaTest % Test
libraryDependencies += scalaMock % Test
libraryDependencies += enumeratum
libraryDependencies ++= Http4s.all
libraryDependencies ++= Fuuid.all
libraryDependencies ++= Logging.all
libraryDependencies ++= Circe.all
libraryDependencies ++= Guice.all
libraryDependencies ++= Mongo.all

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Ypartial-unification",
  "-Xfatal-warnings",
)