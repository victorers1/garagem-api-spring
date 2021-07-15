package computerdatabase

import io.gatling.core.Predef._ // required for Gatling core structure DSL
import io.gatling.http.Predef._ // required for Gatling HTTP DSL
import scala.concurrent.duration._ // used for specifying duration unit, eg "5 second"
import scala.util.Random

class UsersAndVehiclesSignup extends Simulation {
  // Defining protocol
  val httpProtocol = http
    .baseUrl("http://localhost:8080") // Here is the root for all relative URLs
    .acceptHeader(
      "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
    ) // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader(
      "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
    )
    .contentTypeHeader("application/json")

  // Defining custom headers (not used)
  val headerAccept =
    Map("Accept" -> "*/*", "Origin" -> "http://www.demoblaze.com")
  val headerContentType = Map("Content-Type" -> "application/json")

  // Defining random numbers (not used)
  val randomIntFeeder = Iterator.continually(
    Map("nextInt" -> (Random.nextInt(999)))
  )

  // Defining random String
  var randomString = Iterator.continually(
    Map("randstring" -> (Random.alphanumeric.take(11).mkString))
  ) // length of the random string is 11 chars here

  // Defining scene
  val garageTestScn = scenario("Users and Vehicles signup")
    .feed(randomIntFeeder)
    .feed(randomString)
    .exec(
      http("POST /usuarios")
        .post("/usuarios")
        .body(
          StringBody(
            """{
              "nome": "Maria",
              "email": "${randstring}@gmail.com",
              "cpf": "${randstring}",
              "dataNascimento": "01-01-1970"
            }"""
          )
        )
        .check(status.is(201))
    )
    .pause(1)
    .exec(
      http("GET /usuarios")
        .get("/usuarios")
        .check(status.is(200))
    )
    .pause(1)
    .exec(
      http("POST /veiculos")
        .post("/veiculos")
        .body(
          StringBody(
            """{
	            "marcaId": "59",
	            "modeloId": "5586",
	            "anoModelo": "2018-3",
	            "usuarioId": 1
            }"""
          )
        )
        .check(status.is(201))
    )
    .exec(
      http("GET /veiculos")
        .get("/veiculos")
        .check(status.is(200))
    )

  setUp(
    garageTestScn
      .inject(rampUsers(100) during (5 seconds))
      .protocols(httpProtocol)
  )
}
