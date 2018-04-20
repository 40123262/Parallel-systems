package c5

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import groovyJCSP.*
import jcsp.lang.*
import phw.util.*
import jcsp.awt.*
import java.awt.*

class UserInterface implements CSProcess {
 def ActiveCanvas canvas
 def ChannelInput suspend
 def ChannelInput factor
 def ChannelInput console
 def ChannelOutput buttonEvent
 def int canvasSize
 
 
 void run() 
 {
	 def root = new ActiveClosingFrame ("Controller")
	 def mainFrame = root.getActiveFrame()
	 ///elements of the window
	 //output from scaler instead of usual console
	 def Console = new ActiveTextArea(console, null)
	 
	 //user input
	 def SuspendButton = new ActiveButton(suspend, buttonEvent, "SUSPEND")
	 def FactorTextBox = new ActiveTextEnterField(null, buttonEvent)
	 
	 //labels
	 def FactorLabel = new Label ("Current scaling factor:")
	 def FactorValue = new ActiveLabel (factor)
	 def FactorInstructionLabel = new Label("New scaling factor:")
	 
	 //panel
	 Panel FactorPanel = new Panel (new GridLayout (1, 1))
	 FactorPanel.add(FactorTextBox.getActiveTextField())
	 
	 //container
	 def tempContainer = new Container()
     tempContainer.setLayout(new GridLayout (3, 2))
	 tempContainer.add(FactorLabel)
	 tempContainer.add(FactorValue)
	 tempContainer.add(FactorInstructionLabel)
	 tempContainer.add(FactorPanel)
	 tempContainer.add(Console)
	 tempContainer.add(SuspendButton)
	 canvas.setSize(canvasSize,canvasSize)
	 mainFrame.setLayout(new BorderLayout())
	 mainFrame.add(canvas, BorderLayout.CENTER)
	 mainFrame.add(tempContainer, BorderLayout.SOUTH)
	 mainFrame.pack()
	 mainFrame.setVisible(true)
	 
	 def network = [ root, canvas, Console, FactorValue, FactorTextBox, SuspendButton]
	 new PAR (network).run()
	 
	 
 }
}