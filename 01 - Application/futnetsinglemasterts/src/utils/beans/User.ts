export default class User {
  idUser: Number;
  nom: string;
  prenom: string;
  role: string;
  nomEquipe: string;
  mail: string;


  constructor(idUser:Number, nom:string, prenom:string, role:string, nomEquipe:string, mail:string){
    this.idUser = idUser;
    this.nom = nom;
    this.prenom = prenom;
    this.role = role;
    this.nomEquipe = nomEquipe;
    this.mail = mail;
  }
}