package example.wc

import org.apache.spark.sql.SparkSession

object GetWordCount {
  def wordCount(ss: SparkSession): Unit ={

    val textFile = ss.sparkContext.textFile("gouri.txt")
    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
    counts.saveAsTextFile("gouri")
  }
}