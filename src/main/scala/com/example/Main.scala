package com.example

import caliban.{CalibanError, GraphQL, Http4sAdapter}
import caliban.interop.cats.implicits._
import caliban.interop.tapir.HttpInterpreter
import cats.effect._
import cats.effect.syntax.all._
import cats.syntax.all._
import com.comcast.ip4s._
import sttp.tapir.json.circe._
import hello._
import org.http4s._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._
import org.http4s.server.Router
import org.polyvariant.smithy4scaliban._

import scala.concurrent.duration._

object HelloWorldImpl extends HelloService[IO] {
  def getHello(name: String): IO[GetHelloOutput] =
    IO.println("hello, " + name).as(GetHelloOutput("hello, " + name))
}

object Main extends IOApp.Simple {
  implicit val rt: zio.Runtime[Any] = zio.Runtime.default

  private val resourceRoutes: Resource[IO, HttpRoutes[IO]] =
    CalibanGraphQLInterpreter.server(HelloWorldImpl).evalMap { serverApi =>
      serverApi
        .interpreterAsync[IO]
        .map { interp =>
          Router[IO](
            "/api/graphql" ->
              Http4sAdapter.makeHttpServiceF(HttpInterpreter(interp))
          )
        }
    }

  val run: IO[Unit] = resourceRoutes
    .flatMap { routes =>
      val thePort: Port = port"9000"
      val theHost: Hostname = host"localhost"
      val message: String =
        s"Server started on: $theHost:$thePort, press enter to stop"

      EmberServerBuilder
        .default[IO]
        .withPort(thePort)
        .withHost(theHost)
        .withHttpApp(routes.orNotFound)
        .withShutdownTimeout(1.second)
        .build
        .productL(IO.println(message).toResource)
    }
    .surround(IO.readLine)
    .void
    .guarantee(IO.println("Goodbye!"))

}
