package gridcell

import akka.actor._
import messages.{IsSaturated, IsUnsaturated, PrecipitationTimestep, TimestepResult}

class Surface(infiltrationRate: Double) extends Actor {
  import context._

  def saturatedState: Receive = {
    case pts: PrecipitationTimestep =>
      context.parent ! TimestepResult("Saturated Result")
  }

  def unsaturatedState: Receive = {
    case pts: PrecipitationTimestep =>
      context.parent ! TimestepResult("Unsaturated Result")
  }

  def receive = {
    case IsSaturated => {
      become(saturatedState)
      println("became saturated")
    }
    case IsUnsaturated => {
      become(unsaturatedState)
      println("became unsaturated")
    }
  }
}
