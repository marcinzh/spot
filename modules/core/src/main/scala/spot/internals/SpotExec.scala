package spot.internals
import scala.concurrent.ExecutionContext
import turbolift.!!
import turbolift.effects.IO



object SpotExec:
  def current: ExecutionContext !! IO = ???

  def evalOn[A, U <: IO](comp: A !! U, ec: ExecutionContext): A !! U = ???
