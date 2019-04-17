package com.cts.spark.util

import org.apache.spark.sql.SparkSession

object SparkUtil {
  
  def getSparkSession() : SparkSession = {
    //val spark = SparkSession.builder().appName("Spark Hive Example").master("master").config("hive.metastore.warehouse.dir", "hdfs://user/hive/warehouse").config("spark.sql.warehouse.dir", "hdfs://user/hive/warehouse").enableHiveSupport().getOrCreate()
    val spark = SparkSession.builder().appName("CSV file upload to hive").master("yarn-client")
    .config("hive.metastore.warehouse.dir","hdfs://user/hive/warehouse").config("spark.sql.warehouse.dir","hdfs://user/hive/warehouse")
    .config("hive.metastore.uris", "thrift://localhost:9083")
      .config("hive.exec.dynamic.partition.mode", "nonstrict").enableHiveSupport().getOrCreate()
      
      return spark
  }
  
}