export default class Rencontre {
  idRencontre: Number
  date: Date
  joueur1: Number
  joueur2: Number
  score1: Number
  score2: Number
  valide: boolean
  constructor(
    idRencontre: Number,
    date: Date,
    joueur1: Number,
    joueur2: Number,
    score1: Number,
    score2: Number,
    valide: boolean
  ) {
    this.idRencontre = idRencontre
    this.date = date
    this.joueur1 = joueur1
    this.joueur2 = joueur2
    this.score1 = score1
    this.score2 = score2
    this.valide = valide
  }
}
