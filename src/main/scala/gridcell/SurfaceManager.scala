package gridcell

import akka.actor.{Actor, Props}
import messages.{IsUnsaturated, PrecipitationTimestep, TimestepResult}

class SurfaceManager extends Actor {
  private val surface = context.actorOf(Props(new Surface(25.4)), name = "Surface")
  private val upperSoilLayer = context.actorOf(Props[UpperSoilLayer], name = "UpperSoilLayer")
  surface ! IsUnsaturated

  def receive = {
    case PrecipitationTimestep(tick, depth) =>
      sender ! TimestepResult("OK")
    case _ =>
      sender ! TimestepResult("Not OK!")
  }
}
