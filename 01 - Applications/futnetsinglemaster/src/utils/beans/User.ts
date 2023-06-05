export default class User {
  id: Number
  nom: string
  prenom: string
  role: string
  nomEquipe: string
  mail: string
  username: string

  constructor(
    id: Number,
    nom: string,
    prenom: string,
    role: string,
    nomEquipe: string,
    mail: string,
    username: string
  ) {
    this.id = id
    this.nom = nom
    this.prenom = prenom
    this.role = role
    this.nomEquipe = nomEquipe
    this.mail = mail
    this.username = username
  }
}
