package com.beachape.analysis

import org.bytedeco.javacpp.opencv_core.{Mat, Rect}

case class Face(id: Long, faceRect: Rect)
case class Shape(id: Long, shape: Mat)
