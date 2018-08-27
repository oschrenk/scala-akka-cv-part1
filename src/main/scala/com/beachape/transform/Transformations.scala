package com.beachape.transform

import org.bytedeco.javacpp.opencv_imgproc
import org.bytedeco.javacpp.opencv_imgproc._
import org.bytedeco.javacpp.opencv_core.Size

object Transformations {

  def gaussianBlur(withGrey: WithGrey): WithGrey = {
    val clone = withGrey.grey
    opencv_imgproc.GaussianBlur(clone, clone, new Size(5, 5), 0d)
    withGrey.copy(grey = clone)
  }

  def medianBlur(withGrey: WithGrey, ksize: Int=5): WithGrey = {
    val clone = withGrey.grey
    opencv_imgproc.medianBlur(clone, clone, ksize)
    withGrey.copy(grey = clone)
  }

  def threshold(withGrey: WithGrey): WithGrey = {
    val clone = withGrey.grey
    opencv_imgproc.threshold(clone, clone, 60, 255, THRESH_BINARY)
    withGrey.copy(grey = clone)
  }

}
