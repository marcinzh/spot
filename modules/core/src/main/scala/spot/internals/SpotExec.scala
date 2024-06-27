package spot.internals
import scala.concurrent.ExecutionContext
import turbolift.!!
import turbolift.effects.IO
import turbolift.internals.executor.Executor



object SpotExec:
  def current: ExecutionContext !! IO = IO.executor

  def evalOn[A, U <: IO](comp: A !! U, ec: ExecutionContext): A !! U =
    val exec = ec match
      case exec: Executor => exec
      case _ => Executor.fromScala(ec)
    comp.executeOn(exec)
