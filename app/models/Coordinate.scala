package models

import io.circe.{Decoder, Encoder}


case class Coordinate (x : String, y :Int)

object Coordinate {
  implicit val decodeCoord: Decoder[Coordinate] = Decoder.forProduct2("x", "y")(Coordinate.apply)
  implicit val encodeRL: Encoder[Coordinate] = Encoder.forProduct2("x",
    "y")(c => (c.x, c.y))
}
