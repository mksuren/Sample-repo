package com.cts.spark

import org.apache.log4j.{LogManager, Logger}
import org.apache.spark.sql.SparkSession
import com.cts.spark.util.SparkUtil

object SparkHiveExample {
  val logger: Logger = LogManager.getLogger(getClass)
  
  def main(args: Array[String]) {
    
    val warehouseLocation = "spark-warehouse"
    
//val spark = SparkSession.builder().appName("Spark Hive Example").master("master").config("hive.metastore.warehouse.dir", "hdfs://user/hive/warehouse").config("spark.sql.warehouse.dir", "hdfs://user/hive/warehouse").enableHiveSupport().getOrCreate()
/*    val spark = SparkSession.builder().appName("CSV file upload to hive").master("yarn-client")
    .config("hive.metastore.warehouse.dir","hdfs://user/hive/warehouse").config("spark.sql.warehouse.dir","hdfs://user/hive/warehouse")
    .config("hive.metastore.uris", "thrift://localhost:9083")
      .config("hive.exec.dynamic.partition.mode", "nonstrict").enableHiveSupport().getOrCreate()*/
   
   val spark = SparkUtil.getSparkSession()
   val dbList = spark.sql("select * from spark_db.books limit 5")
   dbList.show(4, false)
  }

    
}