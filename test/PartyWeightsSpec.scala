import models.{PartyWeight, PartyWeights}
import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class PartyWeightsSpec extends Specification {

  val list = PartyWeights.findAll()

  val ap = list.map(x => if (x.party == "Det Norske Arbeiderparti") PartyWeight("Det Norske Arbeiderparti", 1) else x)


  "Randomizing" should {
    "return Ap" in {
      PartyWeights.randomize(ap) must startWith("Det Norske Arbeiderparti")
    }
  }
}