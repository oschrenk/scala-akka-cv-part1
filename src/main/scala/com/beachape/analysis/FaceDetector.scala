package com.beachape.analysis

import com.beachape.transform.WithGrey
import org.bytedeco.javacpp.opencv_core._
import org.bytedeco.javacpp.opencv_objdetect.{CascadeClassifier, _}

import scala.swing.Dimension

case class Face(id: Long, faceRect: Rect)

sealed abstract class HaarDetectorFlag(val flag: Int)

case object HaarDetectorFlag {

  case object DoCannyPruning extends HaarDetectorFlag(CV_HAAR_DO_CANNY_PRUNING)
  case object ScaleImage extends HaarDetectorFlag(CV_HAAR_SCALE_IMAGE)
  case object FindBiggestObject extends HaarDetectorFlag(CV_HAAR_FIND_BIGGEST_OBJECT)
  case object DoRoughSearch extends HaarDetectorFlag(CV_HAAR_DO_ROUGH_SEARCH)

}

class FaceDetector(
    val dimensions: Dimension,
    classifierPath: String,
    scaleFactor: Double = 1.3,
    minNeighbours: Int = 3,
    detectorFlag: HaarDetectorFlag = HaarDetectorFlag.ScaleImage,
    minSize: Dimension = new Dimension(30,30),
    maxSize: Option[Dimension] = None
) {

  private val faceCascade = new CascadeClassifier(classifierPath)

  private val minSizeOpenCV = new Size(minSize.width, minSize.height)
  private val maxSizeOpenCV = maxSize.map(d => new Size(d.width, d.height)).getOrElse(new Size())

  /**
   * Given a frame matrix, a series of detected faces
   */
  def detect(frameMatWithGrey: WithGrey): (WithGrey, Seq[Face]) = {
    val currentGreyMat = frameMatWithGrey.grey
    val faceRects = findFaces(currentGreyMat)
    val faces = for {
      i <- 0L until faceRects.size()
      faceRect = faceRects.get(i)
    } yield Face(i, faceRect)
    (frameMatWithGrey, faces)
  }

  private def findFaces(greyMat: Mat): RectVector = {
    val faceRects = new RectVector()
    faceCascade.detectMultiScale(greyMat, faceRects, scaleFactor, minNeighbours, detectorFlag.flag, minSizeOpenCV, maxSizeOpenCV)
    faceRects
  }

}