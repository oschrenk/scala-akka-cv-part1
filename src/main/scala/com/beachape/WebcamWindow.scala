package com.beachape

import java.awt.{DisplayMode, GraphicsDevice, GraphicsEnvironment}

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import com.beachape.transform.{Flip, MediaConversion, Resize}
import com.beachape.video.Webcam
import javax.swing.{JFrame, JPanel, WindowConstants}
import org.bytedeco.javacv.CanvasFrame

import scala.swing.Dimension

object WebcamWindow extends App {

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val canvas = new CanvasFrame("Webcam")
  canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

  val dimensions = new Dimension(640, 480)
  val webcamSource = Webcam.source(deviceId = 0, dimension = dimensions)

  val graph = webcamSource
    .map(MediaConversion.toMat)
    // frame grabber does not respect the dimensions, so we manually resize
    .map(m => Resize.to(m, dimensions.width, dimensions.height))
    .map(Flip.horizontal)
    .map(MediaConversion.toFrame)
    .map(canvas.showImage)
    .to(Sink.ignore)

  graph.run()

}