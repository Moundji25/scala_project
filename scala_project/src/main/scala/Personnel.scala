import scala.io.StdIn
class Personnel(
    protected val num :Int,
    protected var nom :String,
    protected var prenom :String,
    protected var Adresse :String,
    protected var Ville :String,
    protected var fonction :String,
    protected var Salaire :Int,
)
{
  def afficher_Cordonnees():String={
    return "\n\nN° : "+this.num+"\nNom du salarie : "+this.nom+" "+ this
        .prenom+"\nAdresse : "+this.Adresse+"\nVille : "+this.Ville+"\nFonction : "+this.fonction
  }
        
  def setAdresse(adresse : String,Ville : String = "" ):Unit={
        // FAIRE UNE BOUCLE POUR DEMANDER UNE NOUVELLE SAISIE JUSQU'A AVOIR QUELQUE CHOSE DE COHERENT 
		println("Si vous avez rentrer la ville avec l'adresse veuillez retirer cette derniére et la rajouter entant que deuxiéme paramétre de cette fonction ou d'utiliser la fonction setVile()\n" +
        "Afin de sortir appuyer sur Q sinon si vous souhaiter changer l'adresse rentrer Oui")
        val reponse = StdIn.readLine()
        if (!(reponse == "Q" || reponse=="q")) {
            this.Adresse = adresse;
            this.setVille(Ville);
            }
	}

  def getAdresse():String={
		this.Adresse
	}

  def augmenterSalaire(x :Int):Unit={
    if (x>0)
      {
        setSalaire(this.Salaire + ((this.Salaire * x) /100).toInt )
      }
    else {
            println ("L'augmentation ne peut pas être négatif")
    }
  }

    def setnom(x : String):Unit={
		this.nom = x;
	}

    def getnom():String={
		this.nom
	}

    def setprenom(x : String):Unit={
		this.prenom = x;
	}

    def getprenom():String={
		this.prenom
	}

    def setVille(x : String):Unit={
		this.Ville = x;
	}

    def getVille():String={
		this.Ville 
	}

    def setfonction(x : String):Unit={
		this.fonction = x;
	}

    def getfonction():String={
		this.fonction
	}

    def setSalaire(x : Int):Unit={
		this.Salaire = x;
	}

    def getSalaire():Int={
		this.Salaire
	}

    def getNum():Int={
		this.num
	}


}