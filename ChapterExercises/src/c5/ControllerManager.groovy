package c5

import groovyJCSP.*
import jcsp.lang.*
import phw.util.*
import jcsp.awt.*
import java.awt.*

class ControllerManager implements CSProcess {
	
	def ChannelInput fromScale
	def ChannelOutput toSuspend
	def ChannelOutput toInject
	def int scaling
	def ChannelInput fromUIButtons
	def ChannelOutput toUILabel
	def ChannelOutput toUISuspend
	
	void run() 
	{
		def factor = scaling
		toUILabel.write( factor.toString() )
		while (true) 
		{
			def direction = fromUIButtons.read()
			
			if (direction == "SUSPEND") 
			{
				toUISuspend.write("Suspended")
				toSuspend.write(0)
				factor = fromScale.read()
				toUILabel.write(factor.toString())
				def isInteger = false
				while(!isInteger)
				{
					try 
					{
						direction = fromUIButtons.read()
						factor = Integer.parseInt(direction);
						isInteger = true
					}catch (Exception e) 
					{
							println "New factor must be an integer!"
					}
				}
				toUISuspend.write("SUSPEND")
				toUILabel.write(factor.toString())
				toInject.write(factor)
			
			}
			
		}
	}
}