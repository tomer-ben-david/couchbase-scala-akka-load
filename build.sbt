name := "couchbase-scala"

version := "1.0"

scalaVersion := "2.10.2"

resolvers ++= Seq(
    "Typesafe Repository" at  "http://repo.typesafe.com/typesafe/releases/",
    "Couchbase Maven Repository" at "http://files.couchbase.com/maven2"
)

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.2-M3",
    "com.typesafe.akka" %% "akka-slf4j" % "2.2-M3",
    "com.typesafe.akka" %% "akka-testkit" % "2.2-M3",
    "couchbase" % "couchbase-client" % "1.1-dp"
)