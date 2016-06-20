# Scala x AWS Lambda x Apex

An example project to use **Scala on AWS Lambda**.

Lambda functions are deployed via [Apex](http://apex.run).

This project is an example project. However, the function is very practical and you can do word segmenting in Japanese.
You can try the [working example](https://osgknbex0e.execute-api.us-east-1.amazonaws.com/dev?sentence=%E7%84%BC%E8%82%89%E9%A3%9F%E3%81%B9%E3%81%9F%E3%81%84).

If you have any question, please ask to [@yamitzky](https://twitter.com/yamitzky) on Twitter or Github's issue.

(翻訳)

このプロジェクトは、AWS Lambda上でScalaを使うサンプルプロジェクトです。

Lambda関数は[Apex](http://apex.run)を使ってデプロイしています。

このプロジェクトはサンプルプロジェクトですが、実用的で、Kuromojiを使った日本語の形態素解析をすることができます。
[実際に動くAPI](https://osgknbex0e.execute-api.us-east-1.amazonaws.com/dev?sentence=%E7%84%BC%E8%82%89%E9%A3%9F%E3%81%B9%E3%81%9F%E3%81%84)があるので、試してみてください。
Kuromojiの利用は、[弊社](http://jxpress.net/)の[kuromoji4s](https://github.com/jxpress/kuromoji4s)を使っています。

もし何か質問があれば、Twitter上で[@yamitzky](https://twitter.com/yamitzky)にリプライを飛ばすか、Githubなどで聞いていただければ。
