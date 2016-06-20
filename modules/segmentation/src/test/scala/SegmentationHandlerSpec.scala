import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import org.scalatest._

class SegmentationHandlerSpec extends FreeSpec with Matchers {

  val handler = new SegmentationHandler()

  "#handler should write a JSON of a result of word segmentation to output stream" in {
    val input = new ByteArrayInputStream(
      """
        |{"sentence": "焼肉おいしい"}
      """.stripMargin.getBytes("UTF-8")
    )
    val output = new ByteArrayOutputStream()

    handler.handler(input, output)

    output.toString("UTF-8") shouldBe """{"tokens":[{"surface":"焼肉","isKnown":true,"isUser":false,"position":0,"pos1":"名詞","pos2":"一般","baseForm":"焼肉","reading":"ヤキニク","pronunciation":"ヤキニク"},{"surface":"おいしい","isKnown":true,"isUser":false,"position":2,"pos1":"形容詞","pos2":"自立","conjugationForm":"基本形","conjugationType":"形容詞・イ段","baseForm":"おいしい","reading":"オイシイ","pronunciation":"オイシイ"}]}"""
  }

}
