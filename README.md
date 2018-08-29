# shapestream

The goal is to hold up lego bricks (on a big lego plate) and to identify those bricks (with colors). This can then be used for automation purposes eg. having a physical calendar out of lego bricks being synced with an online calendar.

## Usage

`sbt run`

## TODO
* replace deprecated ActorPublisher with GraphStage
  (re-enable fatal warnings that again)
* control which camera to use
* control image size
* control to display processed video next to source video
* import a picture
* detect face on picture
* detect square on picture
* detect rectangle on picture
* detect color of shape on picture
* detect position of shape on picture
* import a video
* detect shape with timestamp in video
* after delay with confidence trigger action
* trigger action only once

## Thanks

Thanks to [Lloyd Chan](https://beachape.com) and his amazing work on bringing akka streams and javacv together, read more at

* [Scala and OpenCV Ep 1: Akka Webcam](https://beachape.com/blog/2016/03/08/scala-and-opencv-ep-1-akka-webcam/)
* [Scala and OpenCV Ep 2: Akka Face Detector](https://beachape.com/blog/2016/03/14/scala-and-opencv-ep-2-akka-face-detector/)
