name := "lambda-kuromoji"

version := "1.0"

scalaVersion := "2.11.8"

lazy val root = (project in file("."))
  .aggregate(core, segmentation)

lazy val core = project in file("modules/core")

lazy val segmentation = (project in file("modules/segmentation"))
  .dependsOn(core)
