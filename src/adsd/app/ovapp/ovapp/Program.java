package adsd.app.ovapp.ovapp;

import java.awt.EventQueue;

public class Program
{
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
