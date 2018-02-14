package c2

import jcsp.lang.*

class CreateSetsOfEight implements CSProcess{
	
	def testList = []
	def listOflists = []
	def ChannelInput inChannel
	def g = 5
	def loopsRemaining
	void run(){
		def outList = []
		def v = inChannel.read()
		
		while (v != -1){
			loopsRemaining = g
			for ( i in 0 .. g-1 ) {
				// put v into outList and read next input
				outList[i] = v
				v = inChannel.read()
				loopsRemaining--
			}
			
			testList = outList;
			listOflists.add(testList)
			println " Eight Object is ${testList}"
		}
		println "Finished"
		
	}
}