name := "curlyshuffle"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  "org.squeryl" %% "squeryl" % "0.9.5-6"
)     

play.Project.playScalaSettings
