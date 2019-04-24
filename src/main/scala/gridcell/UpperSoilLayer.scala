package gridcell

import akka.actor.Actor
import data.StorageLevel
import messages.IsSaturated
import messages.errors.BadParameter

class UpperSoilLayer(initialStorage: Double, maximumStorage: Double) extends Actor {

  var storageLevel: StorageLevel = StorageLevel(0d)

  if(initialStorage > maximumStorage) {
    //this should throw an exception, will get to that next
    context.parent ! BadParameter("Initial storage exceeds maximum storage.")
  } else if(initialStorage < 0) {
    context.parent ! BadParameter("Initial storage is less than zero.")
  } else {
    storageLevel = StorageLevel(initialStorage)
  }

  if(initialStorage == maximumStorage) {
    println(context.parent)
    context.parent ! IsSaturated
  }


  def receive = {
    case _ =>
      println("upper soil layer")
  }
}
