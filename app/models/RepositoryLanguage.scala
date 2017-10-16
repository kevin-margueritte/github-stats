package models

import io.circe.{Decoder, Encoder}

case class RepositoryLanguage(repository : String, language : String)

object RepositoryLanguage {
  implicit val decodeRL: Decoder[RepositoryLanguage] = Decoder.forProduct2("name", "language")(RepositoryLanguage.apply)
  implicit val encodeRL: Encoder[RepositoryLanguage] = Encoder.forProduct2("id",
    "language")(rl => (rl.repository, rl.language))
}
