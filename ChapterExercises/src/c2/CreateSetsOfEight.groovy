package c2

import jcsp.lang.*

class CreateSetsOfEight implements CSProcess{
	
	def ChannelInput inChannel
	def g = 5
	void run(){
		def outList = []
		def v = inChannel.read()
		while (v != -1){
			for ( i in 0 .. g-1 ) {
				// put v into outList and read next input
				outList[i] = v
				v = inChannel.read()
			}
			println " Eight Object is ${outList}"
		}
		println "Finished"
	}
}