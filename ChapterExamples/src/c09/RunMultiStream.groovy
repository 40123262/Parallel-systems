package c09
 
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import jcsp.lang.*
import groovyJCSP.*
import groovyJCSP.plugAndPlay.*
import phw.util.Ask

def sources = Ask.Int ("Number of event sources between 1 and 9 ? ", 1, 9)

minTimes = [ 100, 100, 100, 100, 100, 100, 100, 100, 100 ]
maxTimes = [ 101, 101, 101, 101, 101, 101, 101, 101, 101 ]  
      
def es2ep = Channel.one2oneArray(sources)

ChannelInputList eventsList = new ChannelInputList (es2ep)

def sourcesList = ( 0 ..< sources).collect { i ->
            new EventSource ( source: i+1, 
                              outChannel: es2ep[i].out(),
                              minTime: minTimes[i],
                              maxTime: maxTimes[i] ) 
            }

def eventProcess = new EventProcessing ( eventStreams: eventsList,
                                          minTime: 10,
                                          maxTime: 400 )

new PAR( sourcesList + eventProcess).run()
