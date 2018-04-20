package c2

import jcsp.lang.*
import junit.framework.TestListener
import groovyJCSP.*

class RunThreeToEightTest  extends GroovyTestCase
{

void testList() {
		def conn1 = Channel.one2one()
		def conn2 = Channel.one2one()
		
		def prod = new GenerateSetsOfThree ( outChannel: conn1.out() )
		def list =  new ListToStream ( inChannel: conn1.in(), outChannel: conn2.out() )
		def consumer = new CreateSetsOfEight ( inChannel: conn2.in(), g: 8 )
		
		def testList = [ prod, list, consumer ]
		
		new PAR (testList).run()

		assertTrue (consumer.testList.size() == consumer.g)
		
		assertTrue (consumer.listOflists.size() == list.counter/consumer.g)
		
		assertTrue (list.lastList == [-1,-1,-1])
				
	
		
}


}