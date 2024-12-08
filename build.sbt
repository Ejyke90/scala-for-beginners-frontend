libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.2.10",
  "com.typesafe.akka" %% "akka-stream" % "2.7.1",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.7.1",
  "com.typesafe.akka" %% "akka-serialization-jackson" % "2.7.1",
  "io.spray" %% "spray-json" % "1.3.6",
  "ch.qos.logback" % "logback-classic" % "1.2.10",
  "com.typesafe.play" %% "play" % "2.8.8",
  "com.typesafe.play" %% "play-json" % "2.9.2"
)
libraryDependencies += "com.google.inject" % "guice" % "5.1.0"