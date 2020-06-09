package adsd.app.ovapp.ovapp;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.view.swing.BrowserView;

//extends ovapp means you can use the protected information in ovapp
public class DynamicMap extends OvApp 
{
	static JInternalFrame internalFrame;
	
	public DynamicMap() 
	{
	open_map();
	}
	
	public static void open_map() 
	{
		System.setProperty("jxbrowser.license.key", "6P830J66YAN5IR2Z6GR197J3OHDLYJNT0WAO11SZM8RRGG9S816S0QPEY2NCP251WS5J");
    	System.setProperty("teamdev.license.info", "true");
    	
    	// Creating and running Chromium engine
        Engine engine = Engine.newInstance(
                EngineOptions.newBuilder(HARDWARE_ACCELERATED).build());

        Browser browser = engine.newBrowser();
        // Loading the required web page
        browser.navigation().loadUrl("file:///C:/googlemapsHTML/simple_map.html");
       

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance
            BrowserView view = BrowserView.newInstance(browser);
            
            
            internalFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            internalFrame.add(view,BorderLayout.CENTER);
            internalFrame.setSize(276, 276);
    		panelMap.add(internalFrame);
    		internalFrame.setVisible(true);
    		BasicInternalFrameTitlePane titlePane =(BasicInternalFrameTitlePane)((BasicInternalFrameUI)internalFrame.getUI()).getNorthPane();
    		internalFrame.remove(titlePane);
    		BasicInternalFrameUI basicInternalFrameUI = ((javax.swing.plaf.basic.BasicInternalFrameUI) internalFrame.getUI());
    		for (MouseListener listener : basicInternalFrameUI.getNorthPane().getMouseListeners()) {
    		    basicInternalFrameUI.getNorthPane().removeMouseListener(listener);
    		}
    		//test
        });  
		
	}

	
	
	
}
