package meteorology

import akka.actor.Actor
import data.{PrecipitationDepth, TimeTick}
import messages.PrecipitationTimestep

class MeteorologyManager extends Actor {

  private val constantRate: Double = 2.0 //inches per hour
  private final val conversion: Double = 60 * 60 * 25.4

  def receive = {
    case TimeTick(time) => {
      println("Got to meteorology manager")
      sender ! computeTotalDepth(time)
    }
    case _ => {
      println("wtf")
      sender ! computeTotalDepth(0)
    }
  }

  private def computeTotalDepth(time: Double): PrecipitationTimestep = {
    println("into this function")
    PrecipitationTimestep(TimeTick(time), PrecipitationDepth(constantRate / conversion * time))
  }


}
