package gouri.test.combinerex

import org.apache.spark.sql.SparkSession

object combinerex {
  def combine(ss: SparkSession): Unit = {

    val inputrdd = ss.sparkContext.parallelize(Seq(
      ("maths", 50), ("maths", 60),
      ("english", 65),
      ("physics", 66), ("physics", 61), ("physics", 87)),
      1)
    println("Number of partition :", inputrdd.getNumPartitions)

    val reduced = inputrdd.combineByKey(
      (mark) => {
        println(s"Create combiner -> ${mark}")
        (mark, 1)
      },
      (acc: (Int, Int), v) => {
        println(s"""Merge value : (${acc._1} + ${v}, ${acc._2} + 1)""")
        (acc._1 + v, acc._2 + 1)
      },
      (acc1: (Int, Int), acc2: (Int, Int)) => {
        println(s"""Merge Combiner : (${acc1._1} + ${acc2._1}, ${acc1._2} + ${acc2._2})""")
        (acc1._1 + acc2._1, acc1._2 + acc2._2)
      }
    )

    reduced.collect()
    println("Reduced collected")
    val result = reduced.mapValues(x => x._1 / x._2.toFloat)
    result.collect()
    println("Result collected")
  }
}
