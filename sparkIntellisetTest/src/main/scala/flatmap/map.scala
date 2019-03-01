package gouri.test.dataframe

import org.apache.spark.sql.SparkSession


object mape{
  def mape(sc: SparkSession): Unit ={
    // Basic map example in scala
    val x = sc.sparkContext.parallelize(List("spark", "rdd", "example",  "sample", "example"), 3)
    val y = x.map(x => (x, 1))
    y.foreach(print)
    // res0: Array[(String, Int)] =
    //    Array((spark,1), (rdd,1), (example,1), (sample,1), (example,1))

    // rdd y can be re writen with shorter syntax in scala as
    val y1 = x.map((_, 1))
    y1.foreach(print)
    // res1: Array[(String, Int)] =
    //    Array((spark,1), (rdd,1), (example,1), (sample,1), (example,1))

    // Another example of making tuple with string and it's length
    val y2 = x.map(x => (x, x.length))
    y2.foreach(print)
    // res3: Array[(String, Int)] =
    //    Array((spark,5), (rdd,3), (example,7), (sample,6), (example,7))

  }
}