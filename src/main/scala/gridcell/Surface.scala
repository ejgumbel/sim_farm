package gridcell

import akka.actor._
import messages.{IsSaturated, IsUnsaturated, PrecipitationTimestep}

class Surface(infiltrationRate: Double) extends Actor {
  import context._

  def saturatedState: Receive = {
    case PrecipitationTimestep =>
      println("I am saturated.")
  }

  def unsaturatedState: Receive = {
    case PrecipitationTimestep =>
      println("I am unsaturated.")
  }

  def receive = {
    case IsSaturated => become(saturatedState)
    case IsUnsaturated => become(unsaturatedState)
  }
}
