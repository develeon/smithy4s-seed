import sbt.*

object Dependency {

  private object Version {
    val smity4s = "0.17.19" // smithy4sVersion.value
    val caliban = "2.2.1"
    val `http4s-ember-version` = "0.23.18"
    val specs2 = "4.19.2"
    val catsVersion = "2.9.0"
    val catsEffectVersion = "3.4.8"
    val fs2 = "3.7.0"
    val scalaLogging = "3.9.5"
  }

  object Library {
    val smithy4s = List(
      "com.disneystreaming.smithy4s" %% "smithy4s-core" % Version.smity4s,
      "com.disneystreaming.smithy4s" %% "smithy4s-http4s" % Version.smity4s,
      "com.disneystreaming.smithy4s" %% "smithy4s-http4s-swagger" % Version.smity4s,
      "org.http4s" %% "http4s-ember-server" % Version.`http4s-ember-version`
    )
    val caliban = List(
      "com.github.ghostdogpr" %% "caliban" % Version.caliban,
      "com.github.ghostdogpr" %% "caliban-http4s" % Version.caliban
    )

    val `smity4s-caliban` = List(
      "org.polyvariant" %% "smithy4s-caliban" % "0.1.0",
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % "1.2.10"
    )

    val cats = List(
      "org.typelevel" %% "cats-core" % Version.catsVersion,
      "org.typelevel" %% "cats-effect" % Version.catsEffectVersion
    )

    val logging = List(
      "com.typesafe.scala-logging" %% "scala-logging" % Version.scalaLogging,
      "ch.qos.logback" % "logback-classic" % "1.4.7"
    )

    val fs2 = List(
      "co.fs2" %% "fs2-core" % Version.fs2
      //  "co.fs2" %% "fs2-io" % versions.fs2,
      //  "co.fs2" %% "fs2-reactive-streams" % versions.fs2,
      //  "co.fs2" %% "fs2-scodec" % versions.fs2,
    )

    val Config = List(
      "com.typesafe" % "config" % "1.4.2"
    )

  } // Library

}
