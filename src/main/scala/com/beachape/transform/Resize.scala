package com.beachape.transform
import org.bytedeco.javacpp.opencv_core._
import org.bytedeco.javacpp.opencv_imgproc._
object Resize {

  def to(mat: Mat, width: Int, height: Int): Mat = {
    val cloned = mat.clone()
    val size = new Size(width, height)
    resize(mat, cloned, size)
    cloned
  }

}
