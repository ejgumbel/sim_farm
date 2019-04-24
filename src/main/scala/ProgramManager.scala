import akka.actor.Actor

class ProgramManager extends Actor {

  def receive = {
    case _ =>
      println("Program manager received.")
  }

}
