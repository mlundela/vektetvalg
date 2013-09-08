package models

case class PartyWeight(party: String, weight: Int = 0)

case class PartyWeights(weights: Seq[PartyWeight]) {
   def sum = weights.foldLeft(0)((sum, x) => sum + x.weight)
}

object PartyWeights {

  def findAll(): List[PartyWeight] = List(
    PartyWeight("Fremskrittspartiet"),
    PartyWeight("Det Norske Arbeiderparti"),
    PartyWeight("Høyre"),
    PartyWeight("Miljøpartiet De Grønne"),
    PartyWeight("Sosialistisk Venstreparti"),
    PartyWeight("Venstre"),
    PartyWeight("Rødt"),
    PartyWeight("Senterpartiet"),
    PartyWeight("Kristelig Folkeparti"),
    PartyWeight("Piratpartiet"),
    PartyWeight("Norgespartiet"),
    PartyWeight("Det Liberale Folkepartiet"),
    PartyWeight("De Kristne"),
    PartyWeight("Folkemakten"),
    PartyWeight("PensjonistPartiet"),
    PartyWeight("Norges Kommunistiske Parti"),
    PartyWeight("Demokratene i Norge")
  )

  def randomize(partyWeights: List[PartyWeight]): String = {
    val sum = partyWeights.foldLeft(0)((sum, x) => sum + x.weight)
    val rand: Int = (Math.random() * sum).floor.toInt
    val pot = for {
      pw <- partyWeights
      i <- 1 to pw.weight
    } yield pw.party

    pot.apply(rand)
  }
}
