package com.beachape.modify

import com.beachape.analysis.Face
import com.beachape.transform.WithGrey
import org.bytedeco.javacpp.opencv_core._

class ShapeDrawer(fontScale: Float = 0.6f) {

  def draw(withGrey: WithGrey, shapes: Seq[Face]): Mat = {
    val clonedMat = withGrey.grey.clone()
    clonedMat
  }

}