package c4


import jcsp.lang.*
import groovyJCSP.*

class ResetSuccessor implements CSProcess {
	  
  def ChannelOutput outChannel
  def ChannelInput  inChannel
  def ChannelInput  resetChannel
	  
  void run () {
    def guards = [ resetChannel, inChannel  ]
    def alt = new ALT ( guards )
	while (true)
	{
	  // deal with inputs from resteChannel and inChannel
	  // use a priSelect
		int index = alt.priSelect()
		if (index == 0 ) 
		{   
		  outChannel.write(resetChannel.read())
		}
		else 
		{   
		  outChannel.write(inChannel.read()+1)
		}		
	}
  }
}
