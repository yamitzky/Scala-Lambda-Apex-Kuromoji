import java.io.{ByteArrayOutputStream, ByteArrayInputStream}

import org.scalatest._

class HandlerSpec extends FreeSpec with Matchers {

  "derived class should delegate and write result to output stream" - {
    val input = new ByteArrayInputStream("""{"input": 3}""".getBytes("UTF-8"))
    val output = new ByteArrayOutputStream()
    new MockHandler().handler(input, output)
    output.toString("UTF-8") shouldBe """{"output":6}"""
  }

}

class MockHandler extends Handler(MockHandler.double)

object MockHandler {
  case class TestInput(input: Int)
  case class TestOutput(output: Int)
  def double(input: TestInput): TestOutput = TestOutput(input.input * 2)
}
