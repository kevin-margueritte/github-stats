package models

import io.circe.{Decoder, Encoder}

case class Author(name : String, email : String)

object Author {
  implicit val decodeAuthor: Decoder[Author] = Decoder.forProduct2("name", "email")(Author.apply)
  implicit val encodeAuthor: Encoder[Author] = Encoder.forProduct2("name", "email")(a => (a.name, a.email))
}
