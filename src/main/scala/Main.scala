import akka.actor.{ActorSystem, Props}
import messages.Start


object Main extends App {
  val system = ActorSystem("SimFarm")
  val programManager = system.actorOf(Props[ProgramManager], name = "ProgramManager")
  val simulationManager = system.actorOf(Props[SimulationManager], "SimulationManager")

  simulationManager ! Start

  system.terminate()


}
