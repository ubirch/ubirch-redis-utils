
/*
 * BASIC INFORMATION
 ********************************************************/

name := "ubirch-redis-utils"
version := "0.6.1"
description := "Redis related utils"
organization := "com.ubirch.util"
homepage := Some(url("http://ubirch.com"))
scalaVersion := "2.11.12"
scalacOptions ++= Seq(
  "-feature"
)
scmInfo := Some(ScmInfo(
  url("https://github.com/ubirch/ubirch-redis-utils"),
  "https://github.com/ubirch/ubirch-redis-utils.git"
))

/*
 * CREDENTIALS
 ********************************************************/

(sys.env.get("CLOUDREPO_USER"), sys.env.get("CLOUDREPO_PW")) match {
  case (Some(username), Some(password)) =>
    println("USERNAME and/or PASSWORD found.")
    credentials += Credentials("ubirch.mycloudrepo.io", "ubirch.mycloudrepo.io", username, password)
  case _ =>
    println("USERNAME and/or PASSWORD is taken from /.sbt/.credentials")
    credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
}


/*
 * RESOLVER
 ********************************************************/

val resolverUbirchUtils = "cloudrepo.io" at "https://ubirch.mycloudrepo.io/repositories/ubirch-utils-mvn"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  resolverUbirchUtils)


/*
 * PUBLISHING
 ********************************************************/


publishTo := Some(resolverUbirchUtils)
publishMavenStyle := true


/*
 * DEPENDENCIES
 ********************************************************/
// Versions
val akkaV = "2.5.11"

// Groups
val akkaG = "com.typesafe.akka"
val ubirchUtilGroup = "com.ubirch.util"

//Ubirch
lazy val ubirchUtilConfig = ubirchUtilGroup %% "ubirch-config-utils" % "0.2.4"
lazy val ubirchUtilDeepCheckModel = ubirchUtilGroup %% "ubirch-deep-check-utils" % "0.4.1"

//External
lazy val akkaActor = akkaG %% "akka-actor" % akkaV
lazy val akkaSlf4j = akkaG %% "akka-slf4j" % akkaV
lazy val rediscala = "com.github.etaty" %% "rediscala" % "1.8.0" excludeAll ExclusionRule(organization = s"${akkaActor.organization}", name = s"${akkaActor.name}")
lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"


libraryDependencies ++= Seq(
  akkaActor,
  akkaSlf4j,
  rediscala,
  scalaLogging,
  ubirchUtilConfig,
  ubirchUtilDeepCheckModel
)




