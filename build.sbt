import com.typesafe.sbt.SbtGit.GitKeys._

name := "random-data-generator-magnolia"
version := "2.3-SNAPSHOT"

scalaVersion := "2.12.4"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= {
  if (scalaVersion.value.startsWith("2.10."))
    Seq(compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full))
  else Seq()
}

libraryDependencies ++= {
  val Magnolia = "0.6.1"
  val Scalacheck = "1.13.4"
  val Spec2 = "3.8.6"

  Seq(
    "com.propensive" %% "magnolia" % Magnolia,
    "org.scalacheck" %% "scalacheck" % Scalacheck,
    "org.specs2" %% "specs2-core" % Spec2 % "test",
    "org.specs2" %% "specs2-mock" % Spec2 % "test"
  )
}

lazy val standardSettings = Seq(
  crossScalaVersions := Seq("2.12.4", "2.11.8", "2.10.6"),
  organization := "com.danielasfregola",
  licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html")),
  homepage := Some(url("https://github.com/DanielaSfregola/random-data-generator-magnolia")),
  scmInfo := Some(
    ScmInfo(url("https://github.com/DanielaSfregola/random-data-generator-magnolia"),
            "scm:git:git@github.com:DanielaSfregola/random-data-generator-magnolia.git")),
  apiURL := None, // TBD scaladoc needed?
  pomExtra := (
    <developers>
    <developer>
      <id>DanielaSfregola</id>
      <name>Daniela Sfregola</name>
      <url>http://danielasfregola.com/</url>
    </developer>
  </developers>
  ),
  publishMavenStyle := true,
  gitRemoteRepo := "git@github.com:DanielaSfregola/random-data-generator-magnolia.git",
  scalacOptions ++= Seq("-encoding",
                        "UTF-8",
                        "-deprecation",
                        "-Xfatal-warnings",
                        "-feature",
                        "-unchecked",
                        "-language:postfixOps",
                        "-language:experimental.macros"),
  scalacOptions in (Compile, doc) ++= Seq("-sourcepath", baseDirectory.value.getAbsolutePath),
  autoAPIMappings := true,
  apiURL := None,
  scalacOptions in (Compile, doc) ++= {
    val branch = if (version.value.trim.endsWith("SNAPSHOT")) "master" else version.value
    Seq(
      "-doc-source-url",
      "https://github.com/DanielaSfregola/random-data-generator-magnolia/tree/" + branch + "€{FILE_PATH}.scala"
    )
  }
)

lazy val root = (project in file("."))
  .settings(standardSettings)
  .settings(
    name := "random-data-generator-magnolia"
  )
