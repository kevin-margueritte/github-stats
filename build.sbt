name := """Mediathque Epic 1"""
organization := "com.mrgueritte.mediatheque"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"
val circeVersion = "0.8.0"

libraryDependencies ++= Seq(
  ws,
  guice,
  "io.circe" %% "circe-optics" % circeVersion,
  "com.dripower" %% "play-circe" % "2608.5",
  "com.github.nscala-time" %% "nscala-time" % "2.16.0"
)

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full
)
