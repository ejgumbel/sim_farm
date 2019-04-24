package meteorology

import akka.actor.Actor
import data.{PrecipitationDepth, TimeTick}
import messages.PrecipitationTimestep

class MeteorologyManager extends Actor {

  private val constantRate: Double = 2.0 //inches per hour
  private final val conversion: Double = 60 * 60 * 25.4

  def receive = {
    case TimeTick(time) => {
      context.parent ! computeTotalDepth(time)
    }
    case _ => {
      context.parent ! computeTotalDepth(0)
    }
  }

  private def computeTotalDepth(time: Double): PrecipitationTimestep = {
    PrecipitationTimestep(TimeTick(time), PrecipitationDepth(constantRate / conversion * time))
  }


}
