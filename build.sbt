ThisBuild / organization := "io.github.marcinzh"
ThisBuild / version := "0.12.0"
ThisBuild / scalaVersion := "3.3.6"
ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Wnonunit-statement",
  "-Xfatal-warnings",
)
ThisBuild / scalacOptions += {
  if (VersionNumber(scalaVersion.value).matchesSemVer(SemanticSelector(">=3.4.0")))
    "-Xkind-projector:underscores"
  else
    "-Ykind-projector:underscores"
}

val Deps = {
  object deps {
    val turbolift = "io.github.marcinzh" %% "turbolift-core" % "0.118.0"
    val cats_core = "org.typelevel" %% "cats-core" % "2.13.0"
    val cats_effect = "org.typelevel" %% "cats-effect" % "3.6.3"
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
    Deps.turbolift,
    Deps.cats_core,
    Deps.cats_effect,
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

ThisBuild / description := "Spot - Cats Effect instances for Turbolift"
ThisBuild / organizationName := "marcinzh"
ThisBuild / homepage := Some(url("https://github.com/marcinzh/spot"))
ThisBuild / scmInfo := Some(ScmInfo(url("https://github.com/marcinzh/spot"), "scm:git@github.com:marcinzh/spot.git"))
ThisBuild / licenses := List("MIT" -> url("http://www.opensource.org/licenses/MIT"))
ThisBuild / versionScheme := Some("semver-spec")
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishMavenStyle := true
ThisBuild / publishTo := {
  val centralSnapshots = "https://central.sonatype.com/repository/maven-snapshots/"
  if (isSnapshot.value) Some("central-snapshots" at centralSnapshots)
  else localStaging.value
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
