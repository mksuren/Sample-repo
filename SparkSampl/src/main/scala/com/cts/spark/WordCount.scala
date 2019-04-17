package com.cts.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import org.slf4j.LoggerFactory

object WordCount {
  def main(args: Array[String]) = {
//Initialize logger factory.
  val LOG = LoggerFactory.getLogger(getClass);
  val yamlFile = "/home/surender/dbInfo.yaml"
  val dbConfig = getConfigs(yamlFile)
  val input = getClass.getResource(yamlFile).openStream()
  val txt = scala.io.Source.fromInputStream(input).getLines().mkString;
  println(txt)
  println(dbConfig.colNames)
  
      val text = """
        accountName: Ymail Account
        username: USERNAME
        password: PASSWORD123
        mailbox: INBOX
        imapServerUrl: imap.mail.yahoo.com
        protocol: imaps
        minutesBetweenChecks: 1
        usersOfInterest: [barney, betty, wilma]
        """
      
      val yaml = new Yaml(new Constructor(classOf[EmailAccount]))
      val e = yaml.load(text).asInstanceOf[EmailAccount]
      println(e)
      
    //Start the Spark context
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local")
    val sc = new SparkContext(conf)

    //Read some example file to a test RDD
    val test = sc.textFile("/home/surender/abc.txt")

    test.flatMap { line => //for each line
      line.split(" ") //split the line in word by word.
    }
      .map { word => //for each word
        (word, 1) //Return a key/value tuple, with the word as key and 1 as value
      }
      .reduceByKey(_ + _) //Sum all of the value with same key
      .saveAsTextFile("/home/surender/output.txt") //Save to a text file

    //Stop the Spark context
    sc.stop
  }

    /*
   * Read settings from configuration file.
   */
  def getConfigs(yamlFile: String): DBConfigs = {
    val yaml = new Yaml(new Constructor(classOf[DBConfigs]))
    var dbConfigs = yaml.load(getContentFromFile(yamlFile)).asInstanceOf[DBConfigs]
    return dbConfigs
  }
  
    /*
   * Read file contents and return as string.
   */
  def getContentFromFile(path: String): String = {
    return scala.io.Source.fromFile(path).mkString;
  }
  
  def getSQLFileFromInputStream() : String = {
    return ""
  }
}