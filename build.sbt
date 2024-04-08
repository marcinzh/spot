ThisBuild / organization := "io.github.marcinzh"
ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := "3.3.3"
ThisBuild / crossScalaVersions := Seq(scalaVersion.value)

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Wnonunit-statement",
  "-Xfatal-warnings",
  "-Ykind-projector:underscores",
)

val Deps = {
  object deps {
  }
  deps
}

lazy val root = project
  .in(file("."))
  .settings(sourcesInBase := false)
  .settings(publish / skip := true)
  .aggregate(core, devel)

lazy val core = project
  .in(file("modules/core"))
  .settings(name := "spot-core")
  .settings(libraryDependencies ++= Seq(
  ))

lazy val devel = project
  .in(file("modules/devel"))
  .settings(name := "spot-devel")
  .settings(publish / skip := true)
  .dependsOn(core)

//-------------------------------------------------

ThisBuild / watchBeforeCommand := Watch.clearScreen
ThisBuild / watchTriggeredMessage := Watch.clearScreenOnTrigger
ThisBuild / watchForceTriggerOnAnyChange := true

Test / parallelExecution := false

val cls = taskKey[Unit]("Cls")

cls := {
  print("\u001b[0m\u001b[2J\u001bc")
}

//-------------------------------------------------

ThisBuild / description := "Spot" //@#@TODO
ThisBuild / organizationName := "marcinzh"
ThisBuild / homepage := Some(url("https://github.com/marcinzh/spot"))
ThisBuild / scmInfo := Some(ScmInfo(url("https://github.com/marcinzh/spot"), "scm:git@github.com:marcinzh/spot.git"))
ThisBuild / licenses := List("MIT" -> new URL("http://www.opensource.org/licenses/MIT"))
ThisBuild / versionScheme := Some("semver-spec")
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishMavenStyle := true
ThisBuild / publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  isSnapshot.value match {
    case true => Some("snapshots" at nexus + "content/repositories/snapshots")
    case false => Some("releases" at nexus + "service/local/staging/deploy/maven2")
  }
}
ThisBuild / pomExtra := (
  <developers>
    <developer>
      <id>marcinzh</id>
      <name>Marcin Żebrowski</name>
      <url>https://github.com/marcinzh</url>
    </developer>
  </developers>
)