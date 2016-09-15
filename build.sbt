name := "city_taxi_scala"

version := "1.0.0-SNAPSHOT"
scalaVersion := "2.11.8"
lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  jdbc,
  "org.postgresql"    %   "postgresql" % "9.4.1209.jre7",
  "org.flywaydb"      %%  "flyway-play" % "3.0.1",
  "com.typesafe.play" %%  "play-slick" % "2.0.0",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.2.0",
  "com.github.tototoshi" %% "scala-csv" % "1.3.3",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % "test"
)