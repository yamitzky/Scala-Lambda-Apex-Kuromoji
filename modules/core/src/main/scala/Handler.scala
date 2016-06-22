import java.io.{InputStream, OutputStream}

import org.json4s.jackson.Serialization

abstract class Handler[I, O <: AnyRef](delegate: I => O)(implicit im: Manifest[I], om: Manifest[O]) {

  implicit val formats = org.json4s.DefaultFormats

  /**
    * Base Lambda handler class to avoid boilerplate.
    *
    * By using this handler, conversions from/to case class can be unnecessary.
    * To use, define a simple class and extend. The function to be passed as
    * `delegate` parameter is typically companion object's function.
    *
    * To use from Lambda, specify derived class's `handler` function, e.g.
    * "DerivedClass::Handler".
    *
    * For further usages, see HandlerSpec.
    *
    * @param inputStream Stream to read request body. Specification by Lambda
    * @param outputStream Stream to write response. Specification by Lambda
    */

  def handler(inputStream: InputStream, outputStream: OutputStream): Unit = {
    val input = Serialization.read[I](inputStream)
    val output = delegate(input)
    val json = Serialization.write[O](output)
    outputStream.write(json.getBytes("UTF-8"))
  }
}
