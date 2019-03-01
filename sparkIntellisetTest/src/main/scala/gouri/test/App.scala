package gouri.test

import java.io.File
import scala.math.random
import example.wc.GetWordCount.wordCount
import gouri.test.streaming.streaming
import org.apache.commons.io.FileUtils
import test.dataframe.WorkWithDataframe

import gouri.test.dataframe.dataframeExample
import gouri.test.dataframe.dataframeExample2
import gouri.test.dataframe.pi
import gouri.test.combinerex
import gouri.test.dataframe.mape

import example.session.GetSession

/**
 * Hello world!
 *
 */
object App extends App {
  val ss = GetSession.GetSparkSession()
  mape.mape(ss)
  //flatmap.flatmap(ss)

  //combinerex.combinerex.combine(ss)
  //pi.pi(ss)
  //wordCount(ss)
  //dataframeExample.df(ss);

  //dataframeExample2.example(ss)

  //  FileUtils.deleteQuietly(new File("gouri")
  //  val tmp = File.createTempFile("gouri", "tmp")
  //  tmp.deleteOnExit()
  //  WorkWithDataframe.dfoperation(ss)
  //streaming.streaming.str
}
