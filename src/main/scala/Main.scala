import akka.actor.{Actor, ActorSystem, Props}
import gridcell.SurfaceManager
import messages.Start
import meteorology.MeteorologyManager

object Main extends App {
  val system = ActorSystem("SimFarm")

  val simulationManager = system.actorOf(Props[SimulationManager], "SimulationManager")

  simulationManager ! Start

  system.terminate()


}
