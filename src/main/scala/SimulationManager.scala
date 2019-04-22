import Main.system
import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import data.TimeTick
import gridcell.SurfaceManager
import messages.{PrecipitationTimestep, Start, TimestepResult}
import meteorology.MeteorologyManager

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}


class SimulationManager extends Actor {

  val surfaceManager = context.actorOf(Props[SurfaceManager], name = "SurfaceManager")
  val meteorologyManager = context.actorOf(Props[MeteorologyManager], name = "MeteorologyManager")

  def receive = {
    case Start =>
      println("Started")
      val currentTimestep = transmitOneTick()
      println(currentTimestep.depth.depth)
      val stepResult = transmitPrecipitationTimestep(currentTimestep)
      println(stepResult.result)
  }

  def transmitOneTick(): PrecipitationTimestep = {
    implicit val timeout = Timeout(12 seconds)
    println("past timeout")
    val future: Future[PrecipitationTimestep] = ask(meteorologyManager, TimeTick(3600.0)).mapTo[PrecipitationTimestep]
    Await.result(future, timeout.duration)
  }

  def transmitPrecipitationTimestep(timestep: PrecipitationTimestep): TimestepResult = {
    implicit val timeout = Timeout(5 seconds)
    val future: Future[TimestepResult] = ask(surfaceManager, timestep).mapTo[TimestepResult]
    Await.result(future, timeout.duration)
  }

}
