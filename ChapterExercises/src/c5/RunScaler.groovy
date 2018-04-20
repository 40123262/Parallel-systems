package c5
 
import jcsp.lang.*
import groovyJCSP.*
import groovyJCSP.plugAndPlay.* 
import c05.Controller   
import c05.ScaledData   
  
def data = Channel.createOne2One()
def timedData = Channel.createOne2One()
def scaledData = Channel.createOne2One()
def oldScale = Channel.createOne2One()
def newScale = Channel.createOne2One()
def pause = Channel.createOne2One()

def network = [ new GNumbers ( outChannel: data.out() ),
                new GFixedDelay ( delay: 1000, 
                		          inChannel: data.in(), 
                		          outChannel: timedData.out() ),
                new Scale ( inChannel: timedData.in(),
                            outChannel: scaledData.out(),
                            factor: oldScale.out(),
                            suspend: pause.in(),
                            injector: newScale.in(),
							scaling: 2),
                new ControllerInterface ( inChannel:oldScale.in(),
                              		      suspend: pause.out(),
										  inject: newScale.out() ,
										  console: scaledData.in(),
										  scaling: 2)
              ]
new PAR ( network ).run()                                                            
