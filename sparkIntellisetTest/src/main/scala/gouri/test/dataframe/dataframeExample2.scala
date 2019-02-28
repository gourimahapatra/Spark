package gouri.test.dataframe

import org.apache.spark.sql.SparkSession

object  dataframeExample2 {

  def example(ss: SparkSession): Unit = {

    val dfTags = ss
      .read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("src/main/resources/question_tags_10K.csv")
      .toDF("id", "tag")

    // DataFrame Query: Cast columns to specific data type
    val dfQuestionsCSV = ss
      .read
      .option("header", "true")
      .option("inferSchema", "true")
      .option("dateFormat", "yyyy-MM-dd HH:mm:ss")
      .csv("src/main/resources/questions_10K.csv")
      .toDF("id", "creation_date", "closed_date", "deletion_date", "score", "owner_userid", "answer_count")

    dfQuestionsCSV.printSchema()

    val dfQuestions = dfQuestionsCSV.select(
      dfQuestionsCSV.col("id").cast("integer"),
      dfQuestionsCSV.col("creation_date").cast("timestamp"),
      dfQuestionsCSV.col("closed_date").cast("timestamp"),
      dfQuestionsCSV.col("deletion_date").cast("date"),
      dfQuestionsCSV.col("score").cast("integer"),
      dfQuestionsCSV.col("owner_userid").cast("integer"),
      dfQuestionsCSV.col("answer_count").cast("integer")
    )

    dfQuestions.printSchema()
    dfQuestions.show(5)
    val dfQuestionsSubset = dfQuestions.filter("score > 40 and score < 110").toDF()
    dfQuestionsSubset.show(5)
    dfQuestionsSubset.join(dfTags, "id").show(5)
    dfQuestionsSubset
      .join(dfTags, "id")
      .select("owner_userid", "tag", "creation_date", "score")
      .show(5)
    dfQuestionsSubset
      .join(dfTags, dfTags("id") === dfQuestionsSubset("id"))
      .show(5)
    dfQuestionsSubset
      .join(dfTags, Seq("id"), "inner")
      .show(5)
    dfQuestionsSubset.join(dfTags, Seq("id"), "left_outer").show(5)
    dfQuestionsSubset.join(dfTags, Seq("id"), "right_outer").show(5)
    dfTags.select("tag").distinct().show(5)

    dfTags.createOrReplaceTempView("tag_view")
    ss.catalog.listTables().show(5)
    ss.sql("show tables").show(5)
    ss.sql("select * from tag_view limit 10").show(5)
    ss.sql("select * from tag_view where tag = 'Temp'").show(5)
    ss.sql("select * from tag_view where tag = 'Temp'".stripMargin).show(5)

    ss.sql("select * from tag_view where tag like '%s%'").show(3)
    println("select tag, count(1) as count from tag_view group by tag having count > 5")
    ss.sql("select tag, count(1) as count from tag_view group by tag having count > 5").show(5)
  }
}
