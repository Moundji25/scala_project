import scala.collection.mutable.Map
import java.io.{BufferedReader, FileReader}

import java.io.FileWriter
import org.json4s.{DefaultFormats, Extraction, Formats, JObject, JValue}
import org.json4s.native.JsonMethods.{pretty, render}


object TestSalaire 
{
  def readCSV(filePath: String): Map[String, String] = 
  {
    val mapData = Map[String, String]()
    val br = new BufferedReader(new FileReader(filePath))
    
    var line: String = br.readLine() // Skip header if needed
    
    while ({ line = br.readLine(); line != null }) {
      val Array(key, value) = line.split(",") // Assuming columns are separated by a comma
      
      mapData += (key.trim -> value.trim)
    }

    br.close()
    mapData
  }

  def getValueByKey(map: Map[String, String], key: Int): Option[String] = 
  {
  map.get(key.toString())
  }

  def chercherPlafondEtCalculerRemboursable(rep1 :Representant,valeur :Option[String]):Unit =
  {
    valeur match {
    case Some(value) => {
          //println(s"La valeur associée à la clé "+rep1.getNum()+s" est : $value")
          rep1.calcFraisRemboursables(value.toInt) }
    case None => println(s"Aucune valeur trouvée pour la clé "+rep1.getNum())
  }
  }

  def main(args: Array[String]): Unit = {


  println("HEY PROJECT ")
  val dataMap = readCSV("src/main/ressources/mapping_plafond.csv")

  // Affichage du contenu de la map de plafonds
  dataMap.foreach { case (key, value) =>
      println(s"Key: $key, Value: $value")
    }

  // QUESTION 1
  println("\n >>>>>>>>>>>>>> QUESTION 1 ")
  val rep1 = new Representant(1, "ABIDI", "Besma", "3, Rue el Hayet", "JENDOUBA", "REPRESENTANT", 7500, 750, 186000, 5440)
  val sal1 = new Salarie(9, "AYADI", "Salah", "87, Rue la liberté", "TUNIS", "MAGASINIER", 8690)
  
  val res_plafonds = getValueByKey(dataMap, rep1.getNum())

  if(res_plafonds.isDefined){
    chercherPlafondEtCalculerRemboursable(rep1,
    res_plafonds)
      }
  
  println(rep1.afficher_Cordonnees()+"\n");
  println(sal1.afficher_Cordonnees());

  // QUESTION 2
  println("\n >>>>>>>>>>>>>> QUESTION 2 ")
  sal1.setAdresse("52 rue de Grèce","Soliman");
  sal1.augmenterSalaire(5);

  println(sal1.afficher_Cordonnees());

  // QUESTION 3
  println("\n >>>>>>>>>>>>>> QUESTION 3 ")
  rep1.CumulerVente_CA(100000)
  rep1.setfrais(rep1.getfrais() + 700)

  val res_plafonds_2 = getValueByKey(dataMap, rep1.getNum())

  if(res_plafonds.isDefined){
    chercherPlafondEtCalculerRemboursable(rep1,
    res_plafonds)
      }
  
  println(rep1.afficher_Cordonnees());

  // QUESTION 4
  println("\n >>>>>>>>>>>>>> QUESTION 4 ")
  val Personnels : List[Personnel] = List(sal1,rep1)
  println("\n")

  Personnels.foreach { x => 
    x match {
    case Representant(num_,nom_,prenom_,adresse_,ville_,fonction_,salaire_,frais,ca,comission)  =>    
        println("LE REPRESENTANT : "+ x.afficher_Cordonnees());
    case Salarie(num_,nom_,prenom_,adresse_,ville_,fonction_,salaire_)  =>    
        println("LE SALARIE : "+ x.afficher_Cordonnees());
    case _ =>
        println("L'EMPLOYE : "+ x.afficher_Cordonnees());
    }
   }

   // BONUS 

   println("\n >>>>>>>>>>>>>> BONUS executé (les fichiers generer sont dans le dossier target)")

   // Filtrer les Representant
  val representants: List[Representant] = Personnels.collect({ case r: Representant => r })

  // Filtrer les Salarie
  val salaries: List[Salarie] = Personnels.collect({ case s: Salarie => s })
  
  implicit val formats: Formats = DefaultFormats

  //def toJson[T <: Personnel](liste:List[T]): JValue =
  def toJson(liste:List[Personnel]): JValue = 
  {  
    /*var tmp : JValue;
    liste match {
      case a: List[Representant] => {tmp = Extraction.decompose(a)}
      case b: List[Salarie] => {tmp = Extraction.decompose(b)}
      case c: List[Personnel] => {tmp = Extraction.decompose(c)}
    }
    return tmp;*/

    Extraction.decompose(liste)
  }

  def saveToFile(filename: String,liste:List[Personnel]): Unit = 
  {
    implicit val formats: Formats = DefaultFormats
    val writer = new FileWriter(filename)
    writer.write(pretty(render(toJson(liste))))
    writer.close()
  }

  saveToFile("target/salaries.json",salaries);
  saveToFile("target/representants.json",representants);

}
}

