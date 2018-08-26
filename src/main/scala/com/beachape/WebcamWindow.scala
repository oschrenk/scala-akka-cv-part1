package com.beachape

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import com.beachape.transform.{ Flip, MediaConversion }
import com.beachape.video.{ Dimensions, Webcam }
import javax.swing.WindowConstants
import org.bytedeco.javacv.CanvasFrame

object WebcamWindow extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val canvas = new CanvasFrame("Webcam")
  canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

  val imageDimensions = Dimensions(width = 640, height = 480)
  val webcamSource = Webcam.source(deviceId = 0, dimensions = imageDimensions)

  val graph = webcamSource
    .map(MediaConversion.toMat) // most OpenCV manipulations require a Matrix
    .map(Flip.horizontal)
    .map(MediaConversion.toFrame) // convert back to a frame
    .map(canvas.showImage)
    .to(Sink.ignore)

  graph.run()

}