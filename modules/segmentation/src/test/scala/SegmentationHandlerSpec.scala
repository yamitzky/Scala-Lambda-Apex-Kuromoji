import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import SegmentationHandler.Input
import org.scalatest._

class SegmentationHandlerSpec extends FreeSpec with Matchers {

  "#handler should write a JSON of a result of word segmentation to output stream" in {
    val input = new ByteArrayInputStream(
      """{"sentence": "焼肉おいしい"}""".getBytes("UTF-8")
    )
    val output = new ByteArrayOutputStream()

    new SegmentationHandler().handler(input, output)

    output.toString("UTF-8") shouldBe """{"tokens":[{"surface":"焼肉","isKnown":true,"isUser":false,"position":0,"pos1":"名詞","pos2":"一般","baseForm":"焼肉","reading":"ヤキニク","pronunciation":"ヤキニク"},{"surface":"おいしい","isKnown":true,"isUser":false,"position":2,"pos1":"形容詞","pos2":"自立","conjugationForm":"基本形","conjugationType":"形容詞・イ段","baseForm":"おいしい","reading":"オイシイ","pronunciation":"オイシイ"}]}"""
  }

  "#handler should parse sentence and return tokens" in {
    val input = Input("焼肉おいしい")
    val output = SegmentationHandler.segmentationHandler(input)

    output.tokens.head.surface shouldBe "焼肉"
    output.tokens.head.pos1 shouldBe Some("名詞")

    output.tokens(1).surface shouldBe "おいしい"
    output.tokens(1).pos1 shouldBe Some("形容詞")
  }

}
