import collection.JavaConversions._
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import com.couchbase.client.CouchbaseClient
import java.net.URI
import scala.collection.mutable.ArrayBuffer
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global

class WriterActor extends Actor {
  def receive = {
    case "write" => CouchbaseTalker.talk()
    case _       => println("huh?")
  }
}

object CouchbaseTalker {
  val value = "%01024d".format(123)

  val scanner = new java.util.Scanner(System.in)
  print("Enter your couchbase host name: ")
  val couchbaseHost = scanner.nextLine()

  val uris = ArrayBuffer(URI.create("http://" + couchbaseHost + ":8091/pools"))
  val client = new CouchbaseClient(uris, "default", "")

  def talk(): Future[Long] = future {
    val start = System.currentTimeMillis()
    client.set("hello", 0, value)
    //    println("Receiving -> " + client.get("hello"))
    System.currentTimeMillis() - start
  }

}

object Main1 extends App {

  // an actor needs an ActorSystem
  val system = ActorSystem("HelloSystem")

  // create and start the actor
  val helloActor = system.actorOf(Props[WriterActor], name = "helloactor")

  // send the actor two messages
  for (i <- 1 to 10000) {
    helloActor ! "write"
  }
  Thread.sleep(10000)
  helloActor ! "buenos dias"

  // shut down the system
  system.shutdown

}