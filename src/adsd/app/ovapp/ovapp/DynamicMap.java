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
	   private static JInternalFrame internalFrame;
	   
	   private static Browser browser;
	   private static BrowserView view;
	   
	    private static final int MIN_ZOOM = 0;
	    private static final int MAX_ZOOM = 21;
	    private static final String setMarkerScript =
	            "var myLatlng = new google.maps.LatLng(48.4431727,23.0488126);\n" +
	                    "var marker = new google.maps.Marker({\n" +
	                    "    position: myLatlng,\n" +
	                    "    map: map,\n" +
	                    "    title: 'Hello World!'\n" +
	                    "});";
	    private static int zoomValue = 4;

	    
		public static void internalFrame() 
		{
			
	        internalFrame = new JInternalFrame("Map");
	        internalFrame.setToolTipText("");
	        internalFrame.setFrameIcon(null);
	        internalFrame.setBorder(null);
	        internalFrame.setBounds(10, 233, 262, 282);
	        panelMap.add(internalFrame);
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
	        browser.navigation().loadUrl("file:///C:/GoogleMaps/map2.html");
	       

	        SwingUtilities.invokeLater(() -> {
	            // Creating Swing component for rendering web content
	            // loaded in the given Browser instance
	            view = BrowserView.newInstance(browser);
	            internalFrame();
	            });
		}
	


	public static void set_Location_Tram() {
		
		System.setProperty("jxbrowser.license.key", "6P830J66YAN5IR2Z6GR197J3OHDLYJNT0WAO11SZM8RRGG9S816S0QPEY2NCP251WS5J");
    	System.setProperty("teamdev.license.info", "true");
    	
    	// Creating and running Chromium engine
        Engine engine = Engine.newInstance(
                EngineOptions.newBuilder(HARDWARE_ACCELERATED).build());

       Browser browser = engine.newBrowser();
        // Loading the required web page
        browser.navigation().loadUrl("file:///C:/googlemapsHTML/map_1aAmstelStation_AmsterdamCtrl_Tram.html");
       

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance
            view = BrowserView.newInstance(browser);
            internalFrame();
    		
        }); 
	}
	
	public static void set_Location_Metro1() {
		System.setProperty("jxbrowser.license.key", "6P830J66YAN5IR2Z6GR197J3OHDLYJNT0WAO11SZM8RRGG9S816S0QPEY2NCP251WS5J");
    	System.setProperty("teamdev.license.info", "true");
    	
    	// Creating and running Chromium engine
        Engine engine = Engine.newInstance(
                EngineOptions.newBuilder(HARDWARE_ACCELERATED).build());

       Browser browser = engine.newBrowser();
        // Loading the required web page
        browser.navigation().loadUrl("file:///C:/googlemapsHTML/map_2eCtrl_Zuidplein_Metro.html");
       

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance
           view = BrowserView.newInstance(browser);
            internalFrame();
             		
        }); 
		
	}
	
	public static void set_Location_Metro2() {
		System.setProperty("jxbrowser.license.key", "6P830J66YAN5IR2Z6GR197J3OHDLYJNT0WAO11SZM8RRGG9S816S0QPEY2NCP251WS5J");
    	System.setProperty("teamdev.license.info", "true");
    	
    	// Creating and running Chromium engine
        Engine engine = Engine.newInstance(
                EngineOptions.newBuilder(HARDWARE_ACCELERATED).build());

       Browser browser = engine.newBrowser();
        // Loading the required web page
        browser.navigation().loadUrl("file:///C:/googlemapsHTML/map_3BWillemshaven_DeMarkt_Metro.html");
       

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance
            view = BrowserView.newInstance(browser);
            internalFrame();
    		
        }); 
		
	}
	
	public static void set_Location_Trein1() {
		System.setProperty("jxbrowser.license.key", "6P830J66YAN5IR2Z6GR197J3OHDLYJNT0WAO11SZM8RRGG9S816S0QPEY2NCP251WS5J");
    	System.setProperty("teamdev.license.info", "true");
    	
    	// Creating and running Chromium engine
        Engine engine = Engine.newInstance(
                EngineOptions.newBuilder(HARDWARE_ACCELERATED).build());

       Browser browser = engine.newBrowser();
        // Loading the required web page
        browser.navigation().loadUrl("file:///C:/googlemapsHTML/map_AmersfoortCtrl_AmsterdamCtrl_Trein.html");
       

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance
            view = BrowserView.newInstance(browser);
            internalFrame();
            
    		
        }); 
		
	}
	
	public static void set_Location_Trein2() {
		System.setProperty("jxbrowser.license.key", "6P830J66YAN5IR2Z6GR197J3OHDLYJNT0WAO11SZM8RRGG9S816S0QPEY2NCP251WS5J");
    	System.setProperty("teamdev.license.info", "true");
    	
    	// Creating and running Chromium engine
        Engine engine = Engine.newInstance(
                EngineOptions.newBuilder(HARDWARE_ACCELERATED).build());

       Browser browser = engine.newBrowser();
        // Loading the required web page
        browser.navigation().loadUrl("file:///C:/googlemapsHTML/map_AmersfoortCtrl_UtrechtCtrl_Trein.html");
       

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance
            view = BrowserView.newInstance(browser);
            internalFrame();
            
 
    		
        }); 
		
	}
	
	public static void set_Location_Bus1() {
		System.setProperty("jxbrowser.license.key", "6P830J66YAN5IR2Z6GR197J3OHDLYJNT0WAO11SZM8RRGG9S816S0QPEY2NCP251WS5J");
    	System.setProperty("teamdev.license.info", "true");
    	
    	// Creating and running Chromium engine
        Engine engine = Engine.newInstance(
                EngineOptions.newBuilder(HARDWARE_ACCELERATED).build());

       Browser browser = engine.newBrowser();
        // Loading the required web page
        browser.navigation().loadUrl("file:///C:/googlemapsHTML/map_EdeCtrl_AmersfoortCrtl_Bus.html");
       

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance
            view = BrowserView.newInstance(browser);
            internalFrame();
    		
        }); 
		
		
	}
	
	public static void set_Location_Bus2() {
		System.setProperty("jxbrowser.license.key", "6P830J66YAN5IR2Z6GR197J3OHDLYJNT0WAO11SZM8RRGG9S816S0QPEY2NCP251WS5J");
    	System.setProperty("teamdev.license.info", "true");
    	
    	// Creating and running Chromium engine
        Engine engine = Engine.newInstance(
                EngineOptions.newBuilder(HARDWARE_ACCELERATED).build());

       Browser browser = engine.newBrowser();
        // Loading the required web page
        browser.navigation().loadUrl("file:///C:/googlemapsHTML/map_AmersfoortCtrl_AmersfoortZuid_Bus.html");
       

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance
        	view = BrowserView.newInstance(browser);
            internalFrame();
           
    		
        }); 
		
	}
	
	
	
}
