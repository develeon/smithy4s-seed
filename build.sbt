ThisBuild / scalaVersion := "2.13.9"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .enablePlugins(Smithy4sCodegenPlugin)
  .settings(
    name := "smithy4s-seed",
    libraryDependencies ++= Dependency.Library.smithy4s ++
      Dependency.Library.`smity4s-caliban` ++
      Dependency.Library.caliban,
    Compile / run / fork := true,
    Compile / run / connectInput := true
//    Compile / smithy4sInputDirs := Seq ((ThisBuild / baseDirectory).value / "smithy_input"),
//    Compile / smithy4sOutputDir := (ThisBuild / baseDirectory).value / "smithy_output",
  )
