package com.cts.spark

import scala.io.Source
import java.io.InputStream
import java.io.ByteArrayInputStream
import java.io.FileInputStream
import java.io.File
import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Construct
import org.codehaus.janino.Java.Instanceof
import org.yaml.snakeyaml.constructor.Constructor
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import com.cts.spark.util.SparkUtil


object fileCntRead {
    def main(args: Array[String]) = {
      //Initialize logger factory.
      val LOG = LoggerFactory.getLogger(getClass);
      
      val yamlFile = "dbInfo.yaml"
      val config = getConfigs(yamlFile)
      val sqlFile = config.sqlFilePath
      val dbName = config.dbName
      val sqlQuery1 = getSQLQuery(sqlFile).replace("${dbname}", dbName)
      
      println(sqlQuery1)
      
          //Start the Spark context
    /*val conf = new SparkConf()
      .setAppName("FetchDataFromHive")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().appName("FetchDataFromHive").config(conf).enableHiveSupport().getOrCreate()
    */  
      val spark = SparkUtil.getSparkSession()
      val dbList = spark.sql(sqlQuery1)
      dbList.show()
  }
    
  /*
   * Read settings from configuration(Jar) file.
   */
  def getConfigs(yamlFile: String): DBConfigs = {
    val yaml = new Yaml(new Constructor(classOf[DBConfigs]))
    var dbConfigs = yaml.load(getContentFromURL(yamlFile)).asInstanceOf[DBConfigs]
    return dbConfigs
  }
    
  /*
   * Read settings from Local configuration file.
   */
  def getContentFromFile(path : String) : String = {
    return scala.io.Source.fromFile(path).mkString;
  }
  
  /*
   * Read file contents and return as string.
   */
  def getContentFromURL(path: String): String = {
    return scala.io.Source.fromURL(getClass.getClassLoader.getResource(path)).mkString;
  }
  
  /*
   * Read SQL query from file.
   */
  def getSQLQuery(path: String): String = {
      val is = new FileInputStream(new File(path))
      val cnt = Source.fromInputStream(is).getLines()
      return cnt.next()
  }
  
}