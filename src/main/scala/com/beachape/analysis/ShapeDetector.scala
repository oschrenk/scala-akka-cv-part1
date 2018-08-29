package com.beachape.analysis

import com.beachape.transform.WithGrey
import org.bytedeco.javacpp.opencv_core
import org.bytedeco.javacpp.opencv_core._
import org.bytedeco.javacpp.opencv_imgproc._

case class Shape(id: Long, shape: Mat)

class ShapeDetector {

  def detect(frameMatWithGrey: WithGrey): (WithGrey, Seq[Shape]) = {
    val contours = new MatVector()

    findContours(frameMatWithGrey.grey, contours, 0, 0)

    val contours2 = for {
      i <- 0L until contours.size()
      contour = contours.get(i)
      approx = {
        val m = new Mat()
        approxPolyDP(contour, m, arcLength(contour, true) * 0.1, true)
        m
      }
    } yield Shape(i, approx)

    val triangles = contours2.filter(c => c.shape.rows() == 3)

    val trianglePoints = for {
      c <- triangles
      s = {
        val ipl = new IplImage(c.shape)
        val rows = 0 until c.shape.rows()
        rows.map(r => {
          val scalar = opencv_core.cvGet2D(ipl, r, 0)
          (scalar.get(0),scalar.get(1))
        })
      }
    } yield s
    println(trianglePoints)

    (frameMatWithGrey, triangles)
  }

}
