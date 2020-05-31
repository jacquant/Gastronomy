name := "Gastronomy"

version := "0.1"

scalaVersion := "2.13.2"

lazy val gastronomy = (project in file("."))
  .settings(
    name := "Gastronomy",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.6.5"
  )
