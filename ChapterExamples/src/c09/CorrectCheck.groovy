package c09

import jcsp.lang.*
import groovyJCSP.*

class CorrectCheck implements CSProcess {
	def ChannelInput inChannel
	def ChannelOutput outChannel
	def int prev = 100
	def int totalMissed = 0
	void run () 
	{
		while (true) 
		{
			def e = inChannel.read().copy()
			totalMissed=totalMissed+e.missed
			if (e.data>100 && e.data != prev + e.missed + 1) 
			{
				println "Incorrect number of missed events"
			}
			prev = e.data
			outChannel.write(e)
			println "Total missed from source $e.source == $totalMissed"
		}
		
	}
}