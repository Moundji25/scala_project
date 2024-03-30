case class Representant(
    val num_ :Int,
    var nom_ :String,
    var prenom_ :String,
    var Adresse_ :String,
    var Ville_ :String,
    var fonction_ :String,
    var Salaire_ :Int,
    protected var frais:Int,
    protected var CA:Int,
    protected var comission:Int = -1,
) 
extends Personnel(num_,nom_,prenom_,Adresse_,Ville_,fonction_,Salaire_)
{
    var FraisRemboursable : Int = -1;
    
    private def calculerCommission(CA: Int): Int = {
    var tmp: Double = this.CA.toDouble    
    if (tmp < 100000) {
        (tmp * 0.02).toInt
    } 
    else if (tmp < 200000) {
        (2000 + (tmp - 100000) * 0.04).toInt
    } 
    else {
        (6000 + (tmp - 200000) * 0.06).toInt
    }
    }

    def calcFraisRemboursables(plafond: Int): Unit = {
    if (this.frais > plafond)
        this.FraisRemboursable = plafond
    else
        this.FraisRemboursable = this.frais
    }
    
    def CumulerVente_CA(montant : Int):Unit = {
        this.CA += montant;
        this.setcomission(calculerCommission(montant))
    }
    def mettreAZero_frais_CA(): Unit = {
        this.frais=0;
        this.CA=0;
    }

    override def afficher_Cordonnees(): String = {
        if (this.FraisRemboursable != -1)
            return super.afficher_Cordonnees()+s"\nSalaire FIXE : "+this.Salaire +s" DT\nCommission : $comission DT\nCA : $CA DT\nRemboursement des notes de frais : $FraisRemboursable DT\nTotal à payer : "+(this.Salaire + this.comission + FraisRemboursable);
        else
            return super.afficher_Cordonnees()+s"\nSalaire FIXE : "+this.Salaire +s" DT\nCommission : $comission DT\nCA : $CA DT\nRemboursement des notes de frais : Non calculé\n Total à payer : "+(this.Salaire + this.comission+"\n\n Remarque : pour calculer les frais remboursable veuillez utiliser la fonction calcFraisRemboursables() ");

    }


    def setfrais(x : Int):Unit= {
		this.frais = x;
	}

    def getfrais():Int={
		this.frais
	}

    def setCA(x : Int):Unit={
		this.CA = x;
	}

    def getCA():Int={
		this.CA
	}

    def setcomission(x : Int):Unit={
		this.comission = x;
	}

    def getcomission():Int={

        if (this.comission == -1)
            calculerCommission(this.CA)

        return this.comission
    }

}