import mill._, scalalib._

object foo extends RootModule with ScalaModule {
  def scalaVersion = "2.13.11"
  def ivyDeps = Agg(
    ivy"com.lihaoyi::scalatags:0.8.2",
    ivy"com.lihaoyi::mainargs:0.4.0"
  )

  object test extends ScalaTests {
    def ivyDeps = Agg(ivy"com.lihaoyi::utest:0.7.11")
    def testFramework = "utest.runner.Framework"
  }

  def randomCheck() = T.command {
    val specs = os.pwd / "specs"
    val allSpecsCombined = os.walk(specs).map(os.read).mkString
    val digits = allSpecsCombined.filter(_.isDigit)
    if (digits.toSet.size != digits.size) {
      sys.error("DUPLICATES FOUND")
    } else {
      ()
    }
  }
}
