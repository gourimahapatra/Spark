package gouri.test.dataframe

import org.apache.spark.sql.SparkSession

object dataframeExample {

  def df(ss: SparkSession): Unit = {

    val dfTags = ss
      .read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("src/main/resources/question_tags_10K.csv")
      .toDF("id", "tag")

    //dfTags.show(10)
    dfTags.printSchema()
    dfTags.select("id", "tag").show(10)
    dfTags.filter("tag=='Temp'").show(10)
    println(s"Number of Temp tags = ${dfTags.filter("tag == 'Temp'").count()}")

    println(s"Tags Like S are : ")
    dfTags.filter("tag like '%s%'").show(10)
    println(s"Filter Tags Like S are  and id == 17 or id == 65 : ")
    dfTags.filter("tag like '%s%'").filter("id == 17 or id == 65").show(10)
    println(s"In clause : ")
    dfTags.filter("id in (25,100)").show(5)
    println("SQl Group By")
    dfTags.groupBy("tag")
    println("SQl Group By and count")
    dfTags.groupBy("tag").count().show(20)
    //DataFrame Query: SQL Group By with filter
    dfTags.groupBy("tag").count().filter("count > 5").show(10)
  }
}