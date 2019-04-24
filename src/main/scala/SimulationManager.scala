
import akka.actor._

import data.TimeTick
import gridcell.SurfaceManager
import messages.{PrecipitationTimestep, Start, TimestepResult}
import meteorology.MeteorologyManager


class SimulationManager extends Actor {
  private val initialRate = 25.4
  private val surfaceManager = context.actorOf(Props(new SurfaceManager(25.4)), name = "SurfaceManager")
  private val meteorologyManager = context.actorOf(Props[MeteorologyManager], name = "MeteorologyManager")

  def receive = {
    case Start =>
      transmitOneTick()
    case pts: PrecipitationTimestep =>
      transmitPrecipitationTimestep(pts)
    case tr: TimestepResult =>
      println(tr.result)
  }

  def transmitOneTick() = {
    meteorologyManager ! TimeTick(3600.0)
  }

  def transmitPrecipitationTimestep(timestep: PrecipitationTimestep) = {
    surfaceManager ! timestep
  }

}
