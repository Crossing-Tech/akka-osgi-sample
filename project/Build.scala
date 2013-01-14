import sbt._
import Keys._
import com.typesafe.sbtosgi.OsgiPlugin.{ OsgiKeys, osgiSettings }


object OsgiSampleBuild extends Build {

    lazy val root = Project(id ="osgi-sample",
                            base = file("."),
                            settings = Project.defaultSettings ++ exports(Seq("akka.osgi.xtechsample.activaton"), privates = Seq("akka.osgi.xtechsample.internal")) ++ Seq(
                              scalaVersion :="2.10.0",
                              resolvers += "oss-sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases",
                              libraryDependencies ++= Seq(
                               "com.typesafe.akka" % "akka-actor_2.10" % "2.1.0",
                               "com.typesafe.akka" % "akka-osgi_2.10" % "2.1.0",
                               "com.typesafe.akka" % "akka-remote_2.10" % "2.1.0",
                               "com.typesafe.akka" % "akka-cluster-experimental_2.10" % "2.1.0",
                               "com.typesafe" % "config" % "1.0.0"
                                )
                              )
                            )
    def exports(packages: Seq[String] = Seq(), imports: Seq[String] = Nil, privates: Seq[String] = Nil) = osgiSettings ++ Seq(
      OsgiKeys.importPackage := imports ++ defaultImports,
      OsgiKeys.privatePackage := privates,
      OsgiKeys.exportPackage := packages
    )

    def defaultImports = Seq("!sun.misc", akkaImport(), configImport(), scalaImport(),"*")
    def akkaImport(packageName: String ="akka.*") ="%s;version=\"[2.1,2.2)\"".format(packageName)
    def configImport(packageName: String ="com.typesafe.config.*") ="%s;version=\"[0.4.1,1.1.0)\"".format(packageName)
    def protobufImport(packageName: String ="com.google.protobuf.*") ="%s;version=\"[2.4.0,2.5.0)\"".format(packageName)
    def scalaImport(packageName: String ="scala.*") ="%s;version=\"[2.10,2.11)\"".format(packageName)

}