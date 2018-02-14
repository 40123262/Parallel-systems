package c2

import jcsp.lang.*

class ListToStream implements CSProcess{
	
	def ChannelInput inChannel
	def ChannelOutput outChannel
	
	void run (){
		def inList = inChannel.read()
		while (inList[0] != -1) {
			// hint: output	list elements as single integers
			for(int i : inList)
				outChannel.write(i)
			inList = inChannel.read()
		}
		outChannel.write(-1)
		println "List to stream finished"
	}
	
}