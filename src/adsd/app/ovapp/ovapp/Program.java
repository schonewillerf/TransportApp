package adsd.app.ovapp.ovapp;

import java.awt.EventQueue;

public class Program
{
<<<<<<< src/adsd/app/ovapp/ovapp/Program.java
	EventQueue.invokeLater(new Runnable() 
	{
		public void run() 
		{
			try 
			{
				OvApp nw = new OvApp();	
				nw.newScreen(); // om te verwijzen naar ovapp met gui moet er een newscreen aan gemaakt worden
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	});
}}
=======
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    //newscreen is a alias for OvApp, here is Ovapp opened as a new main program
                    new OvApp().newScreen();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
>>>>>>> src/adsd/app/ovapp/ovapp/Program.java
