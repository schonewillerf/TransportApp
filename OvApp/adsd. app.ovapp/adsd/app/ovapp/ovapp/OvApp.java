package adsd.app.ovapp.ovapp;

import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import junit.framework.Test;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.ScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import adsd.app.ovapp.bus.BusTime;
import adsd.app.ovapp.metro.MetroTime;
import adsd.app.ovapp.train.TrainDataModel;
import adsd.app.ovapp.train.TrainTime;
import adsd.app.ovapp.tram.TramTime;

import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JTree;

public class OvApp {
	
																					//constructor verwijzing
	private static TrainTime traintime;												//werkt hetzelfde als een extender alleen nu kan je meerdere files koppelen
	private static TrainDataModel traindatamodel;
	private static BusTime bustime;
	private static TramTime tramtime;
	
	private JFrame frame;
	private JTable table;
	private ArrayList<BusTime> busTimesList = getArrivalTimesBus(); 
	private ArrayList<TrainTime> trainTimesList = getArrivalTimesTrain();
	private ArrayList<TramTime> tramTimesList = getArrivalTimesTram();
	private ArrayList<MetroTime> metroTimesList = getArrivalTimesMetro();
	
	private String [] tableColumns = {"Vertrektijd","Aankomsttijd",  "Platform", "Station", "Bestemming", "Route"};
	
	private DefaultTableModel busmodel = new DefaultTableModel(tableColumns,0);
	private DefaultTableModel trainmodel = new DefaultTableModel(tableColumns,0);
	private DefaultTableModel metromodel = new DefaultTableModel(tableColumns,0);
	private DefaultTableModel trammodel = new DefaultTableModel(tableColumns,0);
	
	/**
	 * Launch the application.
	 */
	

    		
	public static void NewScreen() 													//de mainstream kan omgezet worden naar newscreen om de verwijzing van program compleet te maken
	{																			
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					OvApp window = new OvApp(traintime, traindatamodel);
					window.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OvApp(TrainTime tt, TrainDataModel tdm) 
	{
		traintime = tt;
		traindatamodel = tdm;
		initialize();
		//addRowToJTableTrain();
		//addRowToJTableBus(); 
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(OvApp.class.getResource("/resources/train_128.png")));
		frame.setBounds(100, 100, 545, 795);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		// panels
		JPanel panelTravelTimes = new JPanel();
		panelTravelTimes.setBackground(SystemColor.info);
		panelTravelTimes.setBounds(0, 379, 536, 384);
		frame.getContentPane().add(panelTravelTimes);
		
		JPanel panelProfileInfo = new JPanel();
		panelProfileInfo.setBackground(SystemColor.activeCaption);
		panelProfileInfo.setBounds(0, 0, 536, 381);
		frame.getContentPane().add(panelProfileInfo);
		panelProfileInfo.setLayout(null);
		panelTravelTimes.setLayout(null);
		
		JLabel lbVerhicleType = new JLabel("Vervoerstype");
		lbVerhicleType.setBounds(182, 11, 95, 20);
		lbVerhicleType.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelTravelTimes.add(lbVerhicleType);
		
		JLabel lbimage = new JLabel("image");
		lbimage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/rsz_1profile.jpg")));
		lbimage.setBounds(10, 0, 201, 220);
		panelProfileInfo.add(lbimage);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(10, 242, 74, 20);
		panelProfileInfo.add(lblName);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAge.setBounds(10, 268, 74, 20);
		panelProfileInfo.add(lblAge);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCity.setBounds(10, 293, 74, 20);
		panelProfileInfo.add(lblCity);
		
		JLabel lblStreetname = new JLabel("Streetname:");
		lblStreetname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStreetname.setBounds(10, 320, 74, 20);
		panelProfileInfo.add(lblStreetname);
		
		//labelstxt
		JLabel lbFullNametxt = new JLabel("Jack Piraat");
		lbFullNametxt.setHorizontalAlignment(SwingConstants.CENTER);
		lbFullNametxt.setBounds(86, 245, 132, 14);
		panelProfileInfo.add(lbFullNametxt);
		
		JLabel lblAgetxt = new JLabel("23");
		lblAgetxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgetxt.setBounds(86, 271, 140, 14);
		panelProfileInfo.add(lblAgetxt);
		
		JLabel lblCitytxt = new JLabel("Amsterdam");
		lblCitytxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblCitytxt.setBounds(86, 296, 140, 14);
		panelProfileInfo.add(lblCitytxt);
		
		JLabel lblStreetNametxt = new JLabel("Kattenburg 12");
		lblStreetNametxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblStreetNametxt.setBounds(86, 323, 140, 14);
		panelProfileInfo.add(lblStreetNametxt);
		
		JButton btnNewButton_1 = new JButton("Edit profile");
		btnNewButton_1.setBounds(107, 212, 105, 20);
		panelProfileInfo.add(btnNewButton_1);
		
		JButton btnTrain = new JButton("Train");
		btnTrain.setBounds(42, 42, 63, 23);
		btnTrain.addActionListener(e1 -> {
		
			try {
				trainmodel.setRowCount(0);
				table.setModel(trainmodel);															
		    	Object tableColumns [] = new Object [6];								// geef tot 6 objecten weer per rij
		    	for(int i = 0; i < trainTimesList.size(); i++) {  						//i++ oneindig weergeven van data in arraylist
		    																			// de (i) info uit de array de i++ zorgt ervoor dat er steeds een opvolgend object word weergeven.
		    		tableColumns[0] = trainTimesList.get(i).getArrivalTime(); 			//eerste rij/row in table is aangegeven met 0
		    		tableColumns[1] = trainTimesList.get(i).getDepartureTime();			//tweede rij/row in de table
		    		tableColumns[2] = trainTimesList.get(i).getPlatForm();				
		    		tableColumns[3] = trainTimesList.get(i).getStationName();
		    		tableColumns[4] = trainTimesList.get(i).getDestination();
		    		tableColumns[5] = trainTimesList.get(i).getRoute();
		    		trainmodel.addRow(tableColumns);}
		    		
		    		TableColumn trainmodel = table.getColumnModel().getColumn(0);		//column converter dus table word busmodel en busmodel krijgt zijn eigen tabeleigenschappen.
		    		trainmodel = table.getColumnModel().getColumn(0);
		    		trainmodel.setPreferredWidth(61);									//hoe groot de tabel moet zijn
		    		trainmodel = table.getColumnModel().getColumn(1);
		    		trainmodel.setPreferredWidth(80);
		    		trainmodel = table.getColumnModel().getColumn(2);
		    		trainmodel.setPreferredWidth(51);
		    		trainmodel = table.getColumnModel().getColumn(3);
		    		trainmodel.setPreferredWidth(115);
		    		trainmodel = table.getColumnModel().getColumn(4);
		    		trainmodel.setPreferredWidth(115);
		    		trainmodel = table.getColumnModel().getColumn(5);
		    		trainmodel.setPreferredWidth(39);
		    	
		    	}
			
			catch (Exception ex) {
				
				System.out.println("Data not found");
				
			}
		    
		});
		panelTravelTimes.add(btnTrain);
		
	
		//Buttons
		JButton btnNewButton = new JButton("Bus");
		btnNewButton.addActionListener( e2 -> {
			

			try {  
			
				busmodel.setRowCount(0);
		    	table.setModel(busmodel);									
		    	for(int j = 0; j < busTimesList.size(); j++) {  					
		    		Object [] tableColumns  = new Object [6];
		    		tableColumns [0] = busTimesList.get(j).getArrivalTime(); 			
		    		tableColumns [1] = busTimesList.get(j).getDepartureTime();			
		    		tableColumns [2] = busTimesList.get(j).getPlatform();				
		    		tableColumns [3] = busTimesList.get(j).getStationName();
		    		tableColumns [4] = busTimesList.get(j).getDestination();
		    		tableColumns [5] = busTimesList.get(j).getRoute();
		    		busmodel.addRow(tableColumns);} 
		    	
		    		TableColumn busmodel = table.getColumnModel().getColumn(0);		//column converter dus table word busmodel en busmodel krijgt zijn eigen tabeleigenschappen
		    		busmodel = table.getColumnModel().getColumn(0);
		    		busmodel.setPreferredWidth(61);
		    		busmodel = table.getColumnModel().getColumn(1);
		    		busmodel.setPreferredWidth(80);
		    		busmodel = table.getColumnModel().getColumn(2);
		    		busmodel.setPreferredWidth(51);
		    		busmodel = table.getColumnModel().getColumn(3);
		    		busmodel.setPreferredWidth(115);
		    		busmodel = table.getColumnModel().getColumn(4);
		    		busmodel.setPreferredWidth(115);
		    		busmodel = table.getColumnModel().getColumn(5);
		    		busmodel.setPreferredWidth(50);
				}	
			
			catch (Exception ex) {
				
				System.out.println("Data not found");
				
			}
		});
		
		btnNewButton.setBounds(115, 42, 57, 23);
		panelTravelTimes.add(btnNewButton);
		
		JButton btnTram = new JButton("Tram");
		btnTram.addActionListener(e3 -> {
			
			try {
				trammodel.setRowCount(0);
				table.setModel(trammodel);
				for( int t = 0; t <tramTimesList.size(); t++) {	
				Object [] tableColumns  = new Object [6];
		    	tableColumns [0] = tramTimesList.get(t).getArrivalTime(); 			
		    	tableColumns [1] = tramTimesList.get(t).getDepartureTime();			
		    	tableColumns [2] = tramTimesList.get(t).getPlatform();				
		    	tableColumns [3] = tramTimesList.get(t).getStationName();
		    	tableColumns [4] = tramTimesList.get(t).getDestination();
		    	tableColumns [5] = tramTimesList.get(t).getRoute();
		    	trammodel.addRow(tableColumns);} 
				
				TableColumn trammodel = table.getColumnModel().getColumn(0);
				trammodel = table.getColumnModel().getColumn(0);
				trammodel.setPreferredWidth(61);
				trammodel = table.getColumnModel().getColumn(1);
				trammodel.setPreferredWidth(80);
				trammodel = table.getColumnModel().getColumn(2);
				trammodel.setPreferredWidth(51);
				trammodel = table.getColumnModel().getColumn(3);
				trammodel.setPreferredWidth(115);
				trammodel = table.getColumnModel().getColumn(4);
				trammodel.setPreferredWidth(115);
				trammodel = table.getColumnModel().getColumn(5);
				trammodel.setPreferredWidth(50);
				}	
			
			catch (Exception ex) {
				
				System.out.println("Data not found");	
				
			}
		});
		btnTram.setBounds(182, 42, 71, 23);
		panelTravelTimes.add(btnTram);
		
		JButton btnMetro = new JButton("Metro");
		btnMetro.addActionListener(e4 -> {											//Opdracht voor wat gebeurd als je op de button klikt. e4 -> = event 4 bij klik probeer(try).
			try {
				
				metromodel.setRowCount(0);
				table.setModel(metromodel);
				for( int m = 0; m <metroTimesList.size(); m++) {	
				Object [] tableColumns  = new Object [6];
		    	tableColumns [0] = metroTimesList.get(m).getArrivalTime(); 			
		    	tableColumns [1] = metroTimesList.get(m).getDepartureTime();			
		    	tableColumns [2] = metroTimesList.get(m).getPlatform();				
		    	tableColumns [3] = metroTimesList.get(m).getStationName();
		    	tableColumns [4] = metroTimesList.get(m).getDestination();
		    	tableColumns [5] = metroTimesList.get(m).getRoute();
		    	metromodel.addRow(tableColumns);} 
				
				TableColumn metromodel = table.getColumnModel().getColumn(0);
				metromodel = table.getColumnModel().getColumn(0);
				metromodel.setPreferredWidth(61);
				metromodel = table.getColumnModel().getColumn(1);
				metromodel.setPreferredWidth(80);
				metromodel = table.getColumnModel().getColumn(2);
				metromodel.setPreferredWidth(51);
				metromodel = table.getColumnModel().getColumn(3);
				metromodel.setPreferredWidth(115);
				metromodel = table.getColumnModel().getColumn(4);
				metromodel.setPreferredWidth(115);
				metromodel = table.getColumnModel().getColumn(5);
				metromodel.setPreferredWidth(50);
				
				}	
			
			catch (Exception ex) {													//error message voor als de data niet opgehaald kan worden
				
				System.out.println("Data not found");	
				
				
			}
		});
		btnMetro.setBounds(266, 42, 69, 23);
		panelTravelTimes.add(btnMetro);
		
		JButton btnWholeRoute = new JButton("Show route");
		btnWholeRoute.setBounds(345, 42, 113, 23);
		panelTravelTimes.add(btnWholeRoute);
		
		//scrollpanes
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(5,80 , 521, 293);
		panelTravelTimes.add(scrollPaneTable);
		
		//tables
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setAutoResizeMode(table.AUTO_RESIZE_ALL_COLUMNS);
		scrollPaneTable.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
					"Vertrektijd","Aankomsttijd",  "Platform", "Station", "Bestemming", "Route"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(61);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(51);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		
}
	
    public ArrayList getArrivalTimesTrain() 
    {
    	
    	ArrayList <TrainTime> trainTimesList = new ArrayList<TrainTime>();											//aangemaakte arraylist
        TrainTime train1 = new TrainTime("13:00", "13:15", "Spoor 2","Utrecht CRL","Amsterdam Crl","4c");			//data van de eerste trein die rijd volgens de traintimeconstructorfile.
    	TrainTime train2 = new TrainTime("14:00", "14:15", "Spoor 4","Amsterdam CRL","Amersfoort Crl","5b");		//data van tweede trein die rijd volgens de traintimeconstructorfile.
    	trainTimesList.add(train1);																					// voeg de data toe aan de arraylijst
    	trainTimesList.add(train2);
    	
        return trainTimesList;																						//geef de lijst met de totale arraydata weer van arraylist traintime.
    }
    
    public ArrayList getArrivalTimesBus() 
    {
    	ArrayList <BusTime> busTimesList = new ArrayList<BusTime>();											
    	BusTime bus1 = new BusTime("13:15", "13.45", "perron D", "Amersfoort","Amersfoort ZD","perron A");			
    	BusTime bus2 = new BusTime("13:30", "14.15", "peroon F", "Amersfoort", "Amersfoort CRL","Perron J");		
    	busTimesList.add(bus1);																					
    	busTimesList.add(bus2);
    	
        return busTimesList;																					
    }
    
    public ArrayList getArrivalTimesTram() 
    {
    	ArrayList <TramTime> tramTimesList = new ArrayList<TramTime>();											
    	TramTime tram1 = new TramTime("15:00", "15:15", "perron 1a", "Amsterdam Ctrl","Amsterdam OS","6c");			
    	TramTime tram2 = new TramTime("16:00", "16:30", "perron 2b", "Amsterdam ZD","Amsterdam CRL","5d");		
    	tramTimesList.add(tram1);																					
    	tramTimesList.add(tram2);
    	
        return tramTimesList;																					
    }
    
    public ArrayList getArrivalTimesMetro() 
    {
    	ArrayList <MetroTime> metroTimesList = new ArrayList<MetroTime>();											
    	MetroTime metro1 = new MetroTime("12:00", "12:15", "perron 1c", "Amsterdam ZD","Amsterdam OST","6c");			
    	MetroTime metro2 = new MetroTime("13:00", "13:15", "perron 1a", "Amsterdam Ctrl","Amsterdam WST","2d");		
    	metroTimesList.add(metro1);																					
    	metroTimesList.add(metro2);
    	
        return metroTimesList;																					
    }
}
	  	   
	





