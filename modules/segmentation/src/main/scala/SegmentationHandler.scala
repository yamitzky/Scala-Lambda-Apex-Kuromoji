import java.io.{ByteArrayOutputStream, ByteArrayInputStream, InputStream, OutputStream}
import SegmentationHandler.{Output, Input}
import org.json4s._
import org.json4s.jackson.Serialization
import net.jxpress.kuromoji4s._


class SegmentationHandler {

  implicit val formats = org.json4s.DefaultFormats
  val tokenizer = Tokenizer()

  /**
    * Lambda handler for the word segmentation function.
    * This is a vanilla implementation.
    *
    * @param inputStream Stream to read request body. Specification by Lambda
    * @param outputStream Stream to write response. Specification by Lambda
    */
  def handler(inputStream: InputStream, outputStream: OutputStream): Unit = {
    // InputStream -> SomeInputClass
    val input = Serialization.read[Input](inputStream)

    val tokens = tokenizer.apply(input.sentence)

    // SomeOutputClass -> OutputStream
    val outputJson = Serialization.write(Output(tokens))
    outputStream.write(outputJson.getBytes("UTF-8"))
  }

}

object SegmentationHandler {
  case class Input(sentence: String)
  case class Output[T](tokens: Seq[T])
}
