package example.session

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}


object GetSession {
  def GetSparkSession(): SparkSession ={
    Logger.getLogger("org").setLevel(Level.ERROR)
    Logger.getLogger("akka").setLevel(Level.ERROR)

    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("TEstByGouri")
      .getOrCreate()
    spark
  }
}