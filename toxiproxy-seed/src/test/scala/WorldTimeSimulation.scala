import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class WorldTimeSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:9000")

  val scn = scenario("What's the Time?")
    .exec(
      http("Get Time")
        .get("/api/timezone/Europe/London")
    )

  setUp(
    scn.inject(
      constantUsersPerSec(1).during(60)
    )
  ).protocols(httpProtocol)
}