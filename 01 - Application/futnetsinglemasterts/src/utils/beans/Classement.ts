export default class Classement {
  idUser: Number;
  nom: string;
  prenom: string;
  nomEquipe: string;
  score: Number;


  constructor(idUser:Number, nom:string, prenom:string, nomEquipe:string, score:Number){
    this.idUser = idUser;
    this.nom = nom;
    this.prenom = prenom;
    this.nomEquipe = nomEquipe;
    this.score = score;
  }
}