package c07

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import jcsp.lang.*
import groovyJCSP.*


class Client implements CSProcess{  
	
  def ChannelInput receiveChannel
  def ChannelOutput requestChannel
  def clientNumber   
  def selectList = [ ] 
  def inRightOrder = true
   
  void run () {
    def iterations = selectList.size
    println "Client $clientNumber has $iterations values in $selectList"
	
    for ( i in 0 ..< iterations) {
      def key = selectList[i]
      requestChannel.write(key)
	  println "Client $clientNumber sent key : $key"
      def v = receiveChannel.read()
	  if(key*10 != v)
	  {
		  inRightOrder=false
		  println "@@@@@@Client $clientNumber is out of order.@@@@@@"
	  }
	  println "Client $clientNumber received: [$key: $v]"
	  
    }
	
    println "Client $clientNumber has finished"
	if (inRightOrder)
		 println "@@@@@@Checking if Client $clientNumber received values in the right order: [[YES]]@@@@@@" 
		 else 
		 println "@@@@@@Checking if Client $clientNumber received values in the right order: [[NO]]@@@@@@" 
		
  }
}
