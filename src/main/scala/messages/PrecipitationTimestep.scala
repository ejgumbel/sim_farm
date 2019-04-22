package messages

import data.{PrecipitationDepth, TimeTick}

case class PrecipitationTimestep(tick: TimeTick, depth: PrecipitationDepth)
