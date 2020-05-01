package adsd.app.ovapp;

import java.awt.EventQueue;
import java.awt.Window;

import adsd.app.ovapp.train.TrainDataModel;
import adsd.app.ovapp.train.TrainTime;





public class Program {
	static OvApp  ovapp;
	private static TrainTime traintime;
	private static TrainDataModel traindatamodel;
	
	


public static void main(String[] args) {
	
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				OvApp nw = new OvApp(traintime, traindatamodel);	
				nw.NewScreen(); // om te verwijzen naar ovapp met gui moet er een newscreen aan gemaakt worden
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	
	
}}