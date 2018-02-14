package c04

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import jcsp.lang.*
import groovyJCSP.*

class ResetPrefix implements CSProcess {
  
  def int prefixValue = 0
  def ChannelOutput outChannel
  def ChannelInput  inChannel
  def ChannelInput  resetChannel
  
  void run () {
    def guards = [ resetChannel, inChannel  ]
    def alt = new ALT ( guards )
    outChannel.write(prefixValue)
    while (true) {
      def index = alt.priSelect()
	  println index
      if (index == 0 ) {    // resetChannel input
		  println "Trying to read from resetChannel"
        def resetValue = resetChannel.read()
		println "Reading from resetChannel complete"
	//	inChannel.read()
		println "Trying to output resetValue"
        outChannel.write(resetValue)
		println "Output from resetValue complete"
      }
      else {    //inChannel input 
		  println "Trying to read from successor to GPCOPY"
        outChannel.write(inChannel.read())     
		println "writing to GPCopy complete"
      }
    }
  }
}
