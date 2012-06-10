import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "SNMP Monitor"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "org.hibernate" % "hibernate-entitymanager" % "4.1.3.Final",
      "org.snmp4j" % "snmp4j" % "1.11.3"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here      
// resolvers += "snmp4j repository" at "https://server.oosnmp.net/dist/release/"
    )

}
