package adsd.app.ovapp.ovapp;

import java.awt.EventQueue;
import java.awt.Window;

import adsd.app.ovapp.train.TrainDataModel;
import adsd.app.ovapp.train.TrainTime;

public class Program 
{
    static OvApp  ovapp;

public static void main(String[] args) 
{
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

