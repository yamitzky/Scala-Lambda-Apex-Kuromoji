import net.jxpress.kuromoji4s._

class SegmentationHandler extends Handler(SegmentationHandler.segmentationHandler)

object SegmentationHandler {
  case class Input(sentence: String)
  case class Output[T](tokens: Seq[T])

  val tokenizer = Tokenizer()

  /**
    * Execute word segmentation.
    *
    * This functions is passed to companion class's `delegate` parameter.
    *
    * @param input Input payload to be deserialized from JSON.
    * @return Output payload to be serialized to JSON
    */
  def segmentationHandler(input: Input): Output[ipadic.Token] = {
    val tokens = tokenizer.apply(input.sentence)
    Output(tokens)
  }
}
