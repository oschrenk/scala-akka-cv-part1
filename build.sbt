lazy val root = (project in file("."))
  .settings(
    name := "scala-cv-part1",
    version := "0.0.1",
    scalaVersion := "2.12.6",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % "2.4.20",
      "org.scala-lang.modules" %% "scala-swing" % "2.0.3"
    ),

  javaCppPresetLibs ++= Seq(
    "ffmpeg" -> "3.4.1"
  ),
fork in run := true
)
