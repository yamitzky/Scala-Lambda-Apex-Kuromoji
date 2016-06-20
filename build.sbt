name := "lambda-kuromoji"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.11.8"
)

lazy val root = (project in file("."))
  .aggregate(core, segmentation)

lazy val core = (project in file("modules/core"))
  .settings(commonSettings: _*)
  .settings(
    // you can find the newest version here: https://github.com/aws/aws-lambda-java-libs
    libraryDependencies ++= Seq(
      "org.json4s" %% "json4s-jackson" % "3.4.0",
      "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",

      // To do logging, aws-lambda-java-log4j is useful. See https://docs.aws.amazon.com/lambda/latest/dg/java-logging.html
      "com.amazonaws" % "aws-lambda-java-log4j" % "1.0.0",

      // If you would handle S3, Kinesis, DynamoDB, SNS events, import this
      // "com.amazonaws" % "aws-lambda-java-events" % "1.3.0",

      "org.scalatest" %% "scalatest" % "2.2.6" % "test"
    )
  )

lazy val segmentation = (project in file("modules/segmentation"))
  .settings(commonSettings: _*)
  .settings(
    assemblyOutputPath in assembly := file("functions/segmentation/target/apex.jar")
  )
  .dependsOn(
    core % "test->test;compile->compile",
    kuromoji4s % "test->test;compile->compile"
  )

lazy val kuromoji4s = uri("https://github.com/jxpress/kuromoji4s.git#master")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
