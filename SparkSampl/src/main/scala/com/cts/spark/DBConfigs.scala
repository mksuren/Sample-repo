package com.cts.spark

import scala.beans.BeanProperty



class DBConfigs {
  
  @BeanProperty var dbName = new String();
  @BeanProperty var tableName = new String();
  @BeanProperty var colNames = new java.util.ArrayList[String]()
  @BeanProperty var sqlFilePath = new String();
  @BeanProperty var outputFilePath = new String();
  
    override def toString: String = {
    return String.format("DataBaseName (%s), columns (%s)", dbName)
  }
}