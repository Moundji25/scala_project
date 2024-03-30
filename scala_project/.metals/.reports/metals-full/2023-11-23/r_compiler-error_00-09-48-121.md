file:///C:/Users/Poundji/Downloads/scala_project/scala_project/src/main/scala/TestSalaire.scala
### file%3A%2F%2F%2FC%3A%2FUsers%2FPoundji%2FDownloads%2Fscala_project%2Fscala_project%2Fsrc%2Fmain%2Fscala%2FTestSalaire.scala:110: error: identifier expected but : found
   def toJson(:List[Personnel]): JValue = {
              ^

occurred in the presentation compiler.

action parameters:
uri: file:///C:/Users/Poundji/Downloads/scala_project/scala_project/src/main/scala/TestSalaire.scala
text:
```scala
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

   println("\n >>>>>>>>>>>>>> BONUS ")

   def toJson(:List[Personnel]): JValue = {
    Extraction.decompose(list)
  }

  def saveToFile(filename: String): Unit = {
    implicit val formats: Formats = DefaultFormats
    val writer = new FileWriter(filename)
    writer.write(pretty(render(toJson(Personnels))))
    writer.close()
  }

}
}


```



#### Error stacktrace:

```
scala.meta.internal.parsers.Reporter.syntaxError(Reporter.scala:16)
	scala.meta.internal.parsers.Reporter.syntaxError$(Reporter.scala:16)
	scala.meta.internal.parsers.Reporter$$anon$1.syntaxError(Reporter.scala:22)
	scala.meta.internal.parsers.Reporter.syntaxError(Reporter.scala:17)
	scala.meta.internal.parsers.Reporter.syntaxError$(Reporter.scala:17)
	scala.meta.internal.parsers.Reporter$$anon$1.syntaxError(Reporter.scala:22)
	scala.meta.internal.parsers.ScalametaParser.syntaxErrorExpected(ScalametaParser.scala:421)
	scala.meta.internal.parsers.ScalametaParser.name(ScalametaParser.scala:1219)
	scala.meta.internal.parsers.ScalametaParser.termName(ScalametaParser.scala:1222)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$termParam$11(ScalametaParser.scala:3302)
	scala.Option.getOrElse(Option.scala:189)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$termParam$1(ScalametaParser.scala:3300)
	scala.meta.internal.parsers.ScalametaParser.atPos(ScalametaParser.scala:319)
	scala.meta.internal.parsers.ScalametaParser.autoPos(ScalametaParser.scala:365)
	scala.meta.internal.parsers.ScalametaParser.termParam(ScalametaParser.scala:3241)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$termParamClausesOnParen$4(ScalametaParser.scala:3192)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$termParamClausesOnParen$4$adapted(ScalametaParser.scala:3192)
	scala.meta.internal.parsers.ScalametaParser.iter$1(ScalametaParser.scala:646)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$tokenSeparated$1(ScalametaParser.scala:652)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$tokenSeparated$1$adapted(ScalametaParser.scala:639)
	scala.meta.internal.parsers.ScalametaParser.scala$meta$internal$parsers$ScalametaParser$$listBy(ScalametaParser.scala:568)
	scala.meta.internal.parsers.ScalametaParser.tokenSeparated(ScalametaParser.scala:639)
	scala.meta.internal.parsers.ScalametaParser.commaSeparatedWithIndex(ScalametaParser.scala:659)
	scala.meta.internal.parsers.ScalametaParser.parseParams$1(ScalametaParser.scala:3192)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$termParamClausesOnParen$2(ScalametaParser.scala:3203)
	scala.meta.internal.parsers.ScalametaParser.scala$meta$internal$parsers$ScalametaParser$$inParensAfterOpenOr(ScalametaParser.scala:253)
	scala.meta.internal.parsers.ScalametaParser.scala$meta$internal$parsers$ScalametaParser$$inParensOnOpenOr(ScalametaParser.scala:244)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$termParamClausesOnParen$1(ScalametaParser.scala:3205)
	scala.meta.internal.parsers.ScalametaParser.atPos(ScalametaParser.scala:319)
	scala.meta.internal.parsers.ScalametaParser.autoPos(ScalametaParser.scala:365)
	scala.meta.internal.parsers.ScalametaParser.paramClause$1(ScalametaParser.scala:3205)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$termParamClausesOnParen$8(ScalametaParser.scala:3208)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$termParamClausesOnParen$8$adapted(ScalametaParser.scala:3207)
	scala.meta.internal.parsers.ScalametaParser.scala$meta$internal$parsers$ScalametaParser$$listBy(ScalametaParser.scala:568)
	scala.meta.internal.parsers.ScalametaParser.termParamClausesOnParen(ScalametaParser.scala:3207)
	scala.meta.internal.parsers.ScalametaParser.termParamClauses(ScalametaParser.scala:3175)
	scala.meta.internal.parsers.ScalametaParser.nonInterleavedParamClauses$1(ScalametaParser.scala:3806)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$funDefRest$1(ScalametaParser.scala:3812)
	scala.meta.internal.parsers.ScalametaParser.autoEndPos(ScalametaParser.scala:368)
	scala.meta.internal.parsers.ScalametaParser.autoEndPos(ScalametaParser.scala:373)
	scala.meta.internal.parsers.ScalametaParser.funDefRest(ScalametaParser.scala:3789)
	scala.meta.internal.parsers.ScalametaParser.funDefOrDclOrExtensionOrSecondaryCtor(ScalametaParser.scala:3734)
	scala.meta.internal.parsers.ScalametaParser.defOrDclOrSecondaryCtor(ScalametaParser.scala:3564)
	scala.meta.internal.parsers.ScalametaParser.localDef(ScalametaParser.scala:4560)
	scala.meta.internal.parsers.ScalametaParser.iter$6(ScalametaParser.scala:4597)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$blockStatSeq$1(ScalametaParser.scala:4620)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$blockStatSeq$1$adapted(ScalametaParser.scala:4570)
	scala.meta.internal.parsers.ScalametaParser.scala$meta$internal$parsers$ScalametaParser$$listBy(ScalametaParser.scala:568)
	scala.meta.internal.parsers.ScalametaParser.blockStatSeq(ScalametaParser.scala:4570)
	scala.meta.internal.parsers.ScalametaParser.blockWithinDelims(ScalametaParser.scala:2507)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$blockInDelims$2(ScalametaParser.scala:2510)
	scala.meta.internal.parsers.ScalametaParser.inBracesOnOpen(ScalametaParser.scala:264)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$blockOnBrace$1(ScalametaParser.scala:2516)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$blockInDelims$1(ScalametaParser.scala:2510)
	scala.meta.internal.parsers.ScalametaParser.atPos(ScalametaParser.scala:319)
	scala.meta.internal.parsers.ScalametaParser.autoPos(ScalametaParser.scala:365)
	scala.meta.internal.parsers.ScalametaParser.blockInDelims(ScalametaParser.scala:2510)
	scala.meta.internal.parsers.ScalametaParser.blockOnBrace(ScalametaParser.scala:2516)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$blockExprOnBrace$2(ScalametaParser.scala:2519)
	scala.meta.internal.parsers.ScalametaParser.blockExprPartial(ScalametaParser.scala:2503)
	scala.meta.internal.parsers.ScalametaParser.blockExprOnBrace(ScalametaParser.scala:2519)
	scala.meta.internal.parsers.ScalametaParser.simpleExpr0(ScalametaParser.scala:2272)
	scala.meta.internal.parsers.ScalametaParser.simpleExpr(ScalametaParser.scala:2243)
	scala.meta.internal.parsers.ScalametaParser.prefixExpr(ScalametaParser.scala:2226)
	scala.meta.internal.parsers.ScalametaParser.postfixExpr(ScalametaParser.scala:2100)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$expr$2(ScalametaParser.scala:1682)
	scala.meta.internal.parsers.ScalametaParser.atPosOpt(ScalametaParser.scala:322)
	scala.meta.internal.parsers.ScalametaParser.autoPosOpt(ScalametaParser.scala:366)
	scala.meta.internal.parsers.ScalametaParser.expr(ScalametaParser.scala:1587)
	scala.meta.internal.parsers.ScalametaParser.expr(ScalametaParser.scala:1486)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$funDefRest$1(ScalametaParser.scala:3827)
	scala.meta.internal.parsers.ScalametaParser.autoEndPos(ScalametaParser.scala:368)
	scala.meta.internal.parsers.ScalametaParser.autoEndPos(ScalametaParser.scala:373)
	scala.meta.internal.parsers.ScalametaParser.funDefRest(ScalametaParser.scala:3789)
	scala.meta.internal.parsers.ScalametaParser.funDefOrDclOrExtensionOrSecondaryCtor(ScalametaParser.scala:3734)
	scala.meta.internal.parsers.ScalametaParser.defOrDclOrSecondaryCtor(ScalametaParser.scala:3564)
	scala.meta.internal.parsers.ScalametaParser.nonLocalDefOrDcl(ScalametaParser.scala:3543)
	scala.meta.internal.parsers.ScalametaParser$$anonfun$templateStat$1.applyOrElse(ScalametaParser.scala:4517)
	scala.meta.internal.parsers.ScalametaParser$$anonfun$templateStat$1.applyOrElse(ScalametaParser.scala:4511)
	scala.PartialFunction.$anonfun$runWith$1$adapted(PartialFunction.scala:145)
	scala.meta.internal.parsers.ScalametaParser.statSeqBuf(ScalametaParser.scala:4462)
	scala.meta.internal.parsers.ScalametaParser.getStats$2(ScalametaParser.scala:4501)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$scala$meta$internal$parsers$ScalametaParser$$templateStatSeq$3(ScalametaParser.scala:4502)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$scala$meta$internal$parsers$ScalametaParser$$templateStatSeq$3$adapted(ScalametaParser.scala:4499)
	scala.meta.internal.parsers.ScalametaParser.scala$meta$internal$parsers$ScalametaParser$$listBy(ScalametaParser.scala:568)
	scala.meta.internal.parsers.ScalametaParser.scala$meta$internal$parsers$ScalametaParser$$templateStatSeq(ScalametaParser.scala:4499)
	scala.meta.internal.parsers.ScalametaParser.scala$meta$internal$parsers$ScalametaParser$$templateStatSeq(ScalametaParser.scala:4491)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$templateBody$1(ScalametaParser.scala:4342)
	scala.meta.internal.parsers.ScalametaParser.inBracesOr(ScalametaParser.scala:260)
	scala.meta.internal.parsers.ScalametaParser.inBraces(ScalametaParser.scala:256)
	scala.meta.internal.parsers.ScalametaParser.templateBody(ScalametaParser.scala:4342)
	scala.meta.internal.parsers.ScalametaParser.templateBodyOpt(ScalametaParser.scala:4346)
	scala.meta.internal.parsers.ScalametaParser.templateAfterExtends(ScalametaParser.scala:4289)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$templateOpt$1(ScalametaParser.scala:4337)
	scala.meta.internal.parsers.ScalametaParser.atPos(ScalametaParser.scala:319)
	scala.meta.internal.parsers.ScalametaParser.autoPos(ScalametaParser.scala:365)
	scala.meta.internal.parsers.ScalametaParser.templateOpt(ScalametaParser.scala:4327)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$objectDef$1(ScalametaParser.scala:4027)
	scala.meta.internal.parsers.ScalametaParser.autoEndPos(ScalametaParser.scala:368)
	scala.meta.internal.parsers.ScalametaParser.autoEndPos(ScalametaParser.scala:373)
	scala.meta.internal.parsers.ScalametaParser.objectDef(ScalametaParser.scala:4019)
	scala.meta.internal.parsers.ScalametaParser.tmplDef(ScalametaParser.scala:3896)
	scala.meta.internal.parsers.ScalametaParser.topLevelTmplDef(ScalametaParser.scala:3877)
	scala.meta.internal.parsers.ScalametaParser$$anonfun$2.applyOrElse(ScalametaParser.scala:4483)
	scala.meta.internal.parsers.ScalametaParser$$anonfun$2.applyOrElse(ScalametaParser.scala:4471)
	scala.PartialFunction.$anonfun$runWith$1$adapted(PartialFunction.scala:145)
	scala.meta.internal.parsers.ScalametaParser.statSeqBuf(ScalametaParser.scala:4462)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$batchSource$13(ScalametaParser.scala:4696)
	scala.Option.getOrElse(Option.scala:189)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$batchSource$1(ScalametaParser.scala:4696)
	scala.meta.internal.parsers.ScalametaParser.atPos(ScalametaParser.scala:319)
	scala.meta.internal.parsers.ScalametaParser.autoPos(ScalametaParser.scala:365)
	scala.meta.internal.parsers.ScalametaParser.batchSource(ScalametaParser.scala:4652)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$source$1(ScalametaParser.scala:4645)
	scala.meta.internal.parsers.ScalametaParser.atPos(ScalametaParser.scala:319)
	scala.meta.internal.parsers.ScalametaParser.autoPos(ScalametaParser.scala:365)
	scala.meta.internal.parsers.ScalametaParser.source(ScalametaParser.scala:4645)
	scala.meta.internal.parsers.ScalametaParser.entrypointSource(ScalametaParser.scala:4650)
	scala.meta.internal.parsers.ScalametaParser.parseSourceImpl(ScalametaParser.scala:135)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$parseSource$1(ScalametaParser.scala:132)
	scala.meta.internal.parsers.ScalametaParser.parseRuleAfterBOF(ScalametaParser.scala:59)
	scala.meta.internal.parsers.ScalametaParser.parseRule(ScalametaParser.scala:54)
	scala.meta.internal.parsers.ScalametaParser.parseSource(ScalametaParser.scala:132)
	scala.meta.parsers.Parse$.$anonfun$parseSource$1(Parse.scala:29)
	scala.meta.parsers.Parse$$anon$1.apply(Parse.scala:36)
	scala.meta.parsers.Api$XtensionParseDialectInput.parse(Api.scala:25)
	scala.meta.internal.semanticdb.scalac.ParseOps$XtensionCompilationUnitSource.toSource(ParseOps.scala:17)
	scala.meta.internal.semanticdb.scalac.TextDocumentOps$XtensionCompilationUnitDocument.toTextDocument(TextDocumentOps.scala:206)
	scala.meta.internal.pc.SemanticdbTextDocumentProvider.textDocument(SemanticdbTextDocumentProvider.scala:54)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$semanticdbTextDocument$1(ScalaPresentationCompiler.scala:356)
```
#### Short summary: 

file%3A%2F%2F%2FC%3A%2FUsers%2FPoundji%2FDownloads%2Fscala_project%2Fscala_project%2Fsrc%2Fmain%2Fscala%2FTestSalaire.scala:110: error: identifier expected but : found
   def toJson(:List[Personnel]): JValue = {
              ^