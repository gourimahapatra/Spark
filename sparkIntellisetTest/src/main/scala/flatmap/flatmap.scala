package gouri.test.dataframe

import org.apache.spark.sql.SparkSession


object flatmap{
  def flatmap(sc: SparkSession): Unit ={

    val x = sc.sparkContext.parallelize(List("spark rdd example",  "sample example", "Gouri Prasad"), 2)
    print(x)
    // map operation will return Array of Arrays in following case : check type of res0
    val yrdd1 = x.map(x => x.split(" ")) // split(" ") returns an array of words

    for(myString <- yrdd1) {
      for (myString1 <- myString) {
        println(myString1)
      }
    }

    //yrdd1.collect().foreach(x=>println(x))

//    val yourRdd = yrdd1.flatMap( arr => {
//      val title = arr( 0 )
//      val text = arr( 1 )
//      val words = text.split( " " )
//      words.map( word => ( word, title ) )
//    } )

    //yrdd1.foreach(x=>println(x(0)))

    // res0: Array[Array[String]] =
    //  Array(Array(spark, rdd, example), Array(sample, example))

    // flatMap operation will return Array of words in following case : Check type of res1
    val yrdd2 = x.flatMap(x => x.split(" "))
    yrdd2.collect().foreach(println)
    //res1: Array[String] =
    //  Array(spark, rdd, example, sample, example)

    // RDD y can be re written with shorter syntax in scala as
    val y = x.flatMap(_.split(" "))
    y.collect().foreach(println)
    //res2: Array[String] =
    //  Array(spark, rdd, example, sample, example)
  }
}