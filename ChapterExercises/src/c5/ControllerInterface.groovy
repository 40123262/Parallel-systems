package c5
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import groovyJCSP.*
import jcsp.lang.*
import phw.util.*
import jcsp.util.*
import jcsp.awt.*

class ControllerInterface implements CSProcess {
   
 def ChannelInput inChannel
 def ChannelInput console
 def ChannelOutput suspend
 def ChannelOutput inject
 def int scaling
 
  void run() {
    def ControllerCanvas = new ActiveCanvas()
    def factorConfig = Channel.one2one()
    def suspendConfig = Channel.one2one()
    def uiEvents = Channel.any2one( new OverWriteOldestBuffer(5) )
    def network = [ new ControllerManager ( fromScale: inChannel, 
                                          toSuspend: suspend,
										  toInject: inject,
                                          fromUIButtons: uiEvents.in(),
                                          toUISuspend: suspendConfig.out(),
                                          toUILabel: factorConfig.out(),
                                          scaling: scaling ),
                    new UserInterface   ( canvas: ControllerCanvas, 
                                          factor: factorConfig.in(),
                                          suspend: suspendConfig.in(),
										  console: console,
                                          buttonEvent: uiEvents.out() ,
										  canvasSize: 100)
                  ]
    new PAR ( network ).run()
  }
}