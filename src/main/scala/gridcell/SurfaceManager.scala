package gridcell

import akka.actor.{Actor, Props}
import messages.errors.BadParameter
import messages.{IsSaturated, IsUnsaturated, PrecipitationTimestep, TimestepResult}


class SurfaceManager(infiltrationRate: Double) extends Actor {
  private val surface = context.actorOf(Props(new Surface(infiltrationRate)), name = "Surface")
  surface ! IsUnsaturated
  private val upperSoilLayer = context.actorOf(Props(new UpperSoilLayer(10d, 10d)),
    name = "UpperSoilLayer")

  def receive = {
    case pts: PrecipitationTimestep =>
      transmitPrecipitationTimestepToSurface(pts)
    case tr: TimestepResult =>
      sendTimestepResultUpwards(tr)
    case sat: IsSaturated =>
      println("inside case block")
      surface ! sat
    case unsat: IsUnsaturated =>
      surface ! unsat
    case bp: BadParameter =>
      println("we shit the bed")
    case _ =>
      context.parent ! TimestepResult("Not OK!")
  }

  def transmitPrecipitationTimestepToSurface(pts: PrecipitationTimestep) = {
    surface ! pts
  }

  def sendTimestepResultUpwards(tr: TimestepResult) = {
    context.parent ! tr
  }


}
