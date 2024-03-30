case class Salarie(
    val num_ :Int,
    var nom_ :String,
    var prenom_ :String,
    var Adresse_ :String,
    var Ville_ :String,
    var fonction_ :String,
    var Salaire_ :Int
) 
extends Personnel(num_,nom_,prenom_,Adresse_,Ville_,fonction_,Salaire_)
{
override def afficher_Cordonnees(): String = {
    super.afficher_Cordonnees()+"\nSalaire à payer : "+Salaire+" DT\n\n"
}
}