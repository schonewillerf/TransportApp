package adsd.app.ovapp.ovapp;


import adsd.app.ovapp.bus.BusDataModel;
import adsd.app.ovapp.metro.MetroDataModel;
import adsd.app.ovapp.train.TrainDataModel;
import adsd.app.ovapp.tram.TramDataModel;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.awt.event.ActionEvent;

import static adsd.app.ovapp.ovapp.DBConnection.Connection;
import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;

import javax.swing.table.DefaultTableModel;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import java.awt.GridLayout;

public class OvApp
{
													//al the objects that are made, now they can be used everywhere in the code and outside in other files.
	private Map languageMap;
	private String language;
	private String selectedTransportType = "Bus"; 	// Bus is assumed to be default for simplicity
	private JFrame frame;
	private JTabbedPane tabbedPane;
	private JPanel panelProfile;
	private JPanel panelLogin;
	private JPanel panelTravelPlanner;
	private JPanel panelLocation;
	private JPanel panelMap;
	private JPanel panelDelays;
	private JPanel panelFavorites;
	private JPanel panelSaved;
	private JPanel panelReminder;
	
	
	private JButton editButton;
	private JEditorPane cardtxt;
	private JEditorPane firstnametxt; 
	private JEditorPane lastnametxt; 
	private JEditorPane citytxt;
	private JEditorPane streettxt;
	private JEditorPane agetxt; 
	private JLabel lblDistance;

	private JTextField txtFieldDeparture;
	private JTextField txtFieldDestination;
	private JSpinner SpnrDateTime;
	private JTextField userName;

	private JPasswordField password;

	private JButton btnLogin;

	private JLabel userid;

	private Profile newProfile = new Profile();
	private TravelTime travelTime;

	// Quick fix to get to the departure location
	private String departureFix;

	private Connection conn;
	private JTable tableMap;
	private JLabel lbMyCard;
	private JLabel lbMyFirstName;
	private JLabel lbMyLastName;
	private JLabel lbMyAge;
	private JLabel lbMyCity;
	private JLabel lbMyStreet;
	private JLabel lbReminders;
	private JLabel lbSaved;
	private JLabel lbFavorites;
	private JLabel lbMyDescription;
	private JTable tblLocation;
	
	private JLabel lblDeparture;
	private JLabel lblDestination;
	private JButton btnLanguage;
	private JButton btnPlanTrip;
	private JButton btnNow;
	

	private DefaultTableModel locationTableModel;
	private JButton btnLocationChange;
	private JLabel lblLocationDestination;
	private JLabel lblLocationDeparture;
	private JLabel lblLocationDepartureType;
	private JTable table;
	private Translate Translate;
	private JLabel lblDepartureTime;
	private JLabel lblArrivalTime;
	private JLabel lblTrackDeparture;
	private JLabel lblTrackArrival;
	private JLabel lblPrice;
	private JLabel lblDistance_1;
	private JLabel lblTotalTime;
	private JLabel lblTransfer;
	private JLabel lblVertragingen;
	private JTable tableSaved;
	
	JInternalFrame internalFrame;
	
	
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
	
	public static void newScreen()						//newscreen is a alias for OvApp, here is Ovapp opened as a new main program
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					OvApp window = new OvApp();			//window is new dialog opening for OvApp
					window.frame.setVisible(true);		// show the window
				} catch (Exception e) 	
				{
					e.printStackTrace();
				}
			}
		});
	}

	public void switchPanels()							//a methode made so this can be used in a single word for removing all the panels
	{
		tabbedPane.removeAll();							// the code used for removing all paneltabs at once
	}
	
	private void tabTxtEn()
    {
        tabbedPane.removeAll();
        tabbedPane.addTab("Profile", null, panelProfile, null);
        tabbedPane.addTab("Travelplaner", null, panelTravelPlanner, null);
        tabbedPane.addTab("Location", null, panelLocation, null);
        tabbedPane.addTab("Map", null, panelMap, null);
        tabbedPane.addTab("Delays", null, panelDelays, null);
    }
	
	private void tabTxtNl()
    {
        tabbedPane.removeAll();
        tabbedPane.addTab("Profiel", null, panelProfile, null);
        tabbedPane.addTab("Reisplanner", null, panelTravelPlanner, null);
        tabbedPane.addTab("Locatie", null, panelLocation, null);
        tabbedPane.addTab("Kaart", null, panelMap, null);
        tabbedPane.addTab("Vertragingen", null, panelDelays, null);
    }

	private void addPanels()													//methode for adding all panels at once through one word. It keeps yur code clean and out of duplication
	{
		tabbedPane.addTab("Profiel", null, panelProfile, null);				//Panel is added to the tabbedpaneframe
		tabbedPane.addTab("Reisplanner", null, panelTravelPlanner, null);
		tabbedPane.addTab("Locatie", null, panelLocation, null);
		tabbedPane.addTab("Kaart", null, panelMap, null);
		tabbedPane.addTab("Vertragingen", null, panelDelays, null);
	}
	
	public OvApp() 															//This is where the app is made and starts
	{
		languageMap = new HashMap();
		frame = new JFrame();
		frame.setBounds(100, 100, 521, 716);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(OvApp.class.getResource("/resources/train_128.png")));
		frame.getContentPane().setLayout(null);
		
		//tabbedpane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 485, 655);
		frame.getContentPane().add(tabbedPane);
		Translate = new Translate("NL"); //standard language
		initialize();
		createEvents();
		panelProfile();
		panelLogin();
		panelTravelPlanner();
		panelLocation();
		panelMap();
		panelDelays();	
	}

	private void setPanelSaved()
	{
		String[] header = {"Vervoerstype", "Vertrektijd", "Vertrek", "Aankomsttijd", "Bestemming"};
		DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[][]{}, header);

		DBHandler dbHandler = new DBHandler();
		List<SavedTime> savedTimes = dbHandler.getSavedTimes(newProfile.getId());

		for (SavedTime savedTime : savedTimes)
		{
			defaultTableModel.addRow(new Object[]
					{
							savedTime.getTransportType(),
							savedTime.getDepartureTime(),
							savedTime.getDeparture(),
							savedTime.getArrivalTime(),
							savedTime.getDestination()
					}
			);
		}

		tableSaved.setModel(defaultTableModel);
	}

	private void panelLogin()
	{
		panelLogin = new JPanel();
		panelLogin.setBackground(Color.WHITE);
		tabbedPane.addTab("Login", null, panelLogin, null);
		panelLogin.setLayout(null);
		JLabel title = new JLabel("Login");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lgn = new JLabel("Login: ");
		JLabel whtd = new JLabel("Wachtwoord: ");
		userName = new JTextField();
		password = new JPasswordField();
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(e ->
		{
			try
			{
				String usn = userName.getText();
				String psd = password.getText();
				conn = Connection();
				usn = userName.getText();
				psd = password.getText();
				PreparedStatement DBLogin = conn.prepareStatement("SELECT * FROM profile WHERE emailAdress=? AND password=?");
				DBLogin.setString(1, String.valueOf(usn));
				DBLogin.setString(2, String.valueOf(psd));
				ResultSet es = DBLogin.executeQuery();
				
				if (es.next()) 
				{
					System.out.println("User doesn't exist");

					tabbedPane.remove(panelLogin);
					panelProfile.revalidate();
					addPanels();
					PreparedStatement DBProfile = conn.prepareStatement("SELECT * FROM profile WHERE emailAdress=? AND password=?");
					DBProfile.setString(1, String.valueOf(usn));
					DBProfile.setString(2, String.valueOf(psd));
					ResultSet rs = DBProfile.executeQuery();

					while (rs.next())
					{
						newProfile = new Profile(rs.getInt("ID"), rs.getInt("age"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("streetName"), rs.getString("residence"), rs.getString("card"));
						userid.setText(String.valueOf(rs.getInt("ID")));
						cardtxt.setText(rs.getString("card"));
						firstnametxt.setText(rs.getString("firstName"));
						lastnametxt.setText(rs.getString("lastName"));
						agetxt.setText(rs.getString("age"));
						citytxt.setText(rs.getString("residence"));
						streettxt.setText(rs.getString("streetName"));
					}
				}

				else 
				{
					System.out.println("User doesn't exist");
				}
			}
			
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}

		});

		title.setBounds(200, 90,200, 20);
		lgn.setBounds(25, 134,150, 20);
		whtd.setBounds(25, 165,150, 20);
		userName.setBounds(200, 134,200, 20);
		password.setBounds(200, 165,200, 20);
		btnLogin.setBounds(325,221, 75,25);
		panelLogin.add(title);
		panelLogin.add(lgn);
		panelLogin.add(whtd);
		panelLogin.add(userName);
		panelLogin.add(password);
		panelLogin.add(btnLogin);
		
	}

	private void panelProfile() 										//methode for panel profile, everything is that is made in profile panel is in this methode.
	{																	// you can call the methode and get all this code working with just one word of code.
				panelProfile = new JPanel();
				panelProfile.setBackground(Color.WHITE);
				panelProfile.setLayout(null);
				// we need this to store userid
				userid = new JLabel("");
				//Labels
				JLabel lbimage = new JLabel();
				lbimage.setBackground(UIManager.getColor("ToolBar.highlight"));
				lbimage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/rsz_1profile.jpg")));		//image added to the label
				lbimage.setBounds(24, 21, 207, 222);
				panelProfile.add(lbimage);
				//Map
				lbMyCard = new JLabel("Kaart:");
				lbMyCard.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbMyCard.setBounds(55, 280, 74, 20);
				panelProfile.add(lbMyCard);
				//Name
				lbMyFirstName = new JLabel("Naam:");
				lbMyFirstName.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbMyFirstName.setBounds(55, 310, 74, 20);
				panelProfile.add(lbMyFirstName);
				//LastName
				lbMyLastName = new JLabel("Achternaam:");
				lbMyLastName.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbMyLastName.setBounds(55, 340, 74, 20);
				panelProfile.add(lbMyLastName);
				//age
				lbMyAge = new JLabel("Leeftijd:");
				lbMyAge.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbMyAge.setBounds(55, 370, 74, 20);
				panelProfile.add(lbMyAge);
				//city
				lbMyCity = new JLabel("Stad:");
				lbMyCity.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbMyCity.setBounds(55, 400, 74, 20);
				panelProfile.add(lbMyCity);
				//streetadress
				lbMyStreet = new JLabel("Straatnaam:");
				lbMyStreet.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbMyStreet.setBounds(55, 430, 74, 20);
				panelProfile.add(lbMyStreet);
				
				//editorpanes profilepanel
				cardtxt  = new JEditorPane();
				cardtxt.setFont(new Font("Tahoma", Font.BOLD, 11));
				cardtxt.setText("naam");
				cardtxt.setEnabled(false);
				cardtxt.setBounds(160, 280, 150, 20);
				panelProfile.add(cardtxt);

				firstnametxt  = new JEditorPane();
				firstnametxt.setFont(new Font("Tahoma", Font.BOLD, 11));
				firstnametxt.setText("naam");
				firstnametxt.setEnabled(false);
				firstnametxt.setBounds(160, 310, 150, 20);
				panelProfile.add(firstnametxt);

				lastnametxt  = new JEditorPane();
				lastnametxt.setFont(new Font("Tahoma", Font.BOLD, 11));
				lastnametxt.setText("naam");
				lastnametxt.setEnabled(false);
				lastnametxt.setBounds(160, 340, 150, 20);
				panelProfile.add(lastnametxt);
				
				agetxt = new JEditorPane();
				agetxt.setText("leeftijd");
				agetxt.setFont(new Font("Tahoma", Font.BOLD, 11));
				agetxt.setEnabled(false);
				agetxt.setBounds(160, 370, 150, 20);
				panelProfile.add(agetxt);
				
				citytxt = new JEditorPane();
				citytxt.setFont(new Font("Tahoma", Font.BOLD, 11));
				citytxt.setText("plaats");
				citytxt.setEnabled(false);
				citytxt.setBounds(160, 400, 150, 20);
				panelProfile.add(citytxt);
				
				streettxt = new JEditorPane();
				streettxt.setFont(new Font("Tahoma", Font.BOLD, 11));
				streettxt.setText("aderes");
				streettxt.setEnabled(false);
				streettxt.setBounds(160, 430, 150, 20);
				panelProfile.add(streettxt);

				
				lbMyDescription = new JLabel("Mijn beschrijving:");
				lbMyDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbMyDescription.setBounds(55, 467, 118, 20);
				panelProfile.add(lbMyDescription);
				
				lbFavorites = new JLabel("Favorieten");
				lbFavorites.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbFavorites.setBounds(301, 43, 74, 20);
				panelProfile.add(lbFavorites);
				
				lbSaved = new JLabel("Opgeslagen");
				lbSaved.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbSaved.setBounds(301, 85, 74, 20);
				panelProfile.add(lbSaved);
				
				lbReminders = new JLabel("Herinneringen");
				lbReminders.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbReminders.setBounds(301, 138, 94, 20);
				panelProfile.add(lbReminders);
				
				JLabel lbHome = new JLabel();
				lbHome.setIcon(new ImageIcon(OvApp.class.getResource("/resources/homecolor.png")));
				lbHome.setHorizontalAlignment(SwingConstants.LEFT);
				lbHome.setBounds(24, 430, 24, 27);
				panelProfile.add(lbHome);
				//textpanes
				JTextPane textPane = new JTextPane();
				textPane.setBackground(new Color(211, 211, 211));
				textPane.setBounds(24, 505, 386, 111);
				panelProfile.add(textPane);
				//buttons
				editButton = new JButton("Wijzig profiel");									//button for changing profile
				// Action Event for 
				editButton.addActionListener(new ActionListener()							//by click the action will be performed with the code  below
				{
					public void actionPerformed(ActionEvent e) 
					{
						if (("Wijzig profiel").equals(editButton.getText()))				// . equals is the same for..
						{
							cardtxt.setEnabled(true);
							firstnametxt.setEnabled(true);
							lastnametxt.setEnabled(true);
							agetxt.setEnabled(true);
							citytxt.setEnabled(true);
							streettxt.setEnabled(true);
							lastnametxt.setEnabled(true);
							editButton.setText("Opslaan");
						}
						
						else
						{
							cardtxt.setEnabled(false);
							firstnametxt.setEnabled(false);
							lastnametxt.setEnabled(false);
							agetxt.setEnabled(false);
							citytxt.setEnabled(false);
							streettxt.setEnabled(false);
							lastnametxt.setEnabled(false);
							editButton.setText("Wijzig profiel");
							
							try
							{
								conn = Connection();
								String query = "UPDATE profile SET firstName=?, lastName=?, age=?, streetName=?, residence=?, card=? WHERE ID=?";

								PreparedStatement preparedStmt = conn.prepareStatement(query);
								preparedStmt.setString(1, firstnametxt.getText());
								preparedStmt.setString(2, lastnametxt.getText());
								preparedStmt.setString(3, String.valueOf(agetxt.getText()));
								preparedStmt.setString(4, streettxt.getText());
								preparedStmt.setString(5, citytxt.getText());
								preparedStmt.setString(6, String.valueOf(cardtxt.getText()));
								preparedStmt.setString(7, String.valueOf(userid.getText()));

								preparedStmt.executeUpdate();
							}
							catch (SQLException throwables)
							{
								throwables.printStackTrace();
							}
						}
					}
					});
				
				editButton.setBounds(55, 240, 120, 20);
				panelProfile.add(editButton);
				
				JButton btnPencil = new JButton("");
				btnPencil.setForeground(Color.WHITE);
				btnPencil.setBackground(Color.WHITE);
				btnPencil.setIcon(new ImageIcon(OvApp.class.getResource("/resources/toolpencil.png")));
				btnPencil.setBounds(24, 464, 24, 23);
				panelProfile.add(btnPencil);
				
				JButton btnCard = new JButton();
				btnCard.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
					
					}
				});
				btnCard.setForeground(Color.WHITE);
				btnCard.setBackground(Color.WHITE);
				btnCard.setIcon(new ImageIcon(OvApp.class.getResource("/resources/mycard.png")));
				btnCard.setBounds(24, 278, 29, 23);
				panelProfile.add(btnCard);
				
				JButton btnFavorites = new JButton();
				btnFavorites.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						switchPanels();
						tabbedPane.add(panelFavorites);
						tabbedPane.addTab("Favorieten", null ,panelFavorites, null);
					}
				});
				
				btnFavorites.setForeground(Color.WHITE);
				btnFavorites.setBackground(Color.WHITE);
				btnFavorites.setIcon(new ImageIcon(OvApp.class.getResource("/resources/favorites.png")));
				btnFavorites.setBounds(262, 43, 29, 23);
				panelProfile.add(btnFavorites);
				
				JButton btnSaved = new JButton();
				btnSaved.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						switchPanels();
						setPanelSaved();
						tabbedPane.add(panelSaved);
						tabbedPane.addTab("Opgeslagen", null ,panelSaved, null);
					}
				});
				
				// Uncomment to edit savedPanel in Window Builder
				//tabbedPane.add(panelSaved);
				
				btnSaved.setForeground(Color.WHITE);
				btnSaved.setBackground(Color.WHITE);
				btnSaved.setIcon(new ImageIcon(OvApp.class.getResource("/resources/saved.png")));
				btnSaved.setBounds(262, 85, 29, 23);
				panelProfile.add(btnSaved);
				
				JButton btnReminder = new JButton();
				btnReminder.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						switchPanels();
						tabbedPane.add(panelReminder);
						tabbedPane.addTab("Herinneringen", null , panelReminder, null);
					}
				});
				
				btnReminder.setForeground(Color.WHITE);
				btnReminder.setBackground(Color.WHITE);
				btnReminder.setIcon(new ImageIcon(OvApp.class.getResource("/resources/reminder.png")));
				btnReminder.setBounds(262, 135, 29, 23);
				panelProfile.add(btnReminder);
				
				btnLanguage = new JButton("EN");
				btnLanguage.setHorizontalAlignment(SwingConstants.LEFT);
				btnLanguage.setBackground(Color.WHITE);
				btnLanguage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/countryEnglish.png")));
				btnLanguage.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						if (Translate.Language.equals("EN")) //display Language Dutch
						{
							Translate.Language = "NL";
							System.out.println(Translate.Language);
							tabTxtNl();
							btnLanguage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/countryEnglish.png")));
							btnLanguage.setText(Translate.TransLang(("EN")));
						}
						
						else if (Translate.Language.equals("NL")) //display Language English
						{
							Translate.Language = "EN";
							System.out.println(Translate.Language);
							tabTxtEn();
							btnLanguage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/countryNetherlands.png")));
							btnLanguage.setText(Translate.TransLang(("EN")));
						}
						//Profile
						lbMyFirstName.setText(Translate.TransLang("Naam:"));
						lbFavorites.setText(Translate.TransLang("Favorieten:"));
						lbSaved.setText(Translate.TransLang("Opgeslagen:"));
						lbReminders.setText(Translate.TransLang("Herinneringen:"));
						editButton.setText(Translate.TransLang("Wijzig profiel"));
						lbMyCard.setText(Translate.TransLang("Kaart:"));
						lbMyFirstName.setText(Translate.TransLang("Naam:"));
						lbMyLastName.setText(Translate.TransLang("Achternaam:"));
						lbMyAge.setText(Translate.TransLang("Leeftijd:"));
						lbMyCity.setText(Translate.TransLang("Stad:"));
						lbMyStreet.setText(Translate.TransLang("Straatnaam:"));
						lbMyDescription.setText(Translate.TransLang("Mijn beschrijving:"));
						//TravelPlanner
						lblDestination.setText(Translate.TransLang("Aankomst:"));
						lblDeparture.setText(Translate.TransLang("Vertrek:"));
						btnPlanTrip.setText(Translate.TransLang("Zoeken"));
						btnNow.setText(Translate.TransLang("Nu"));
						//Location
						btnLocationChange.setText(Translate.TransLang("Wijzig reis"));
						lblLocationDestination.setText(Translate.TransLang("Bestemming:"));
						lblLocationDeparture.setText(Translate.TransLang("Vertrek:"));
						lblLocationDepartureType.setText(Translate.TransLang("Vervoerstype:"));
						//Map
						lblDeparture.setText(Translate.TransLang("Vertrektijd:"));
						lblArrivalTime.setText(Translate.TransLang("Aankomsttijd:"));
						lblTotalTime.setText(Translate.TransLang("Totale tijd:"));
						lblTransfer.setText(Translate.TransLang("Overdracht:"));
						lblTrackArrival.setText(Translate.TransLang("Spoor:"));
						lblTrackDeparture.setText(Translate.TransLang("Spoor:"));
						lblPrice.setText(Translate.TransLang("Prijs:"));
						lblDistance_1.setText(Translate.TransLang("Afstand:"));
						lblDepartureTime.setText(Translate.TransLang("Vertrektijd:"));
						//Delays
						lblVertragingen.setText(Translate.TransLang("Vertragingen:"));	
						}
					
				});
				btnLanguage.setBounds(389, 11, 74, 23);										//set he line out for the buttondesign
				panelProfile.add(btnLanguage);
				//buttons "back"
				JButton btnBackFavorites = new JButton("Terug ");
				
				btnBackFavorites.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						addPanels();
						tabbedPane.remove(panelFavorites);
					}
					
				});
				
				btnBackFavorites.setBounds(364, 28, 89, 23);
				panelFavorites.add(btnBackFavorites);
				JButton btnBackSaved = new JButton("Terug");
				btnBackSaved.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						addPanels();
						tabbedPane.remove(panelSaved);	
					}
					
				});
				
				btnBackSaved.setBounds(12, 12, 89, 23);
				panelSaved.add(btnBackSaved);
				
				JScrollPane scrollPaneSaved = new JScrollPane();
				scrollPaneSaved.setBounds(12, 47, 456, 528);
				panelSaved.add(scrollPaneSaved);
				
				tableSaved = new JTable();
				scrollPaneSaved.setViewportView(tableSaved);
				
				JButton btnDetailsSaved = new JButton("Details");
				btnDetailsSaved.setBounds(363, 587, 105, 27);
				panelSaved.add(btnDetailsSaved);
				btnDetailsSaved.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						addPanels();
						tabbedPane.remove(panelSaved);	
						tabbedPane.setSelectedIndex(3);
					}
				});
				
				JButton btnBackReminder = new JButton("Terug");
				btnBackReminder.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						addPanels();
						tabbedPane.remove(panelReminder);	
					}
				});
				
				btnBackReminder.setBounds(316, 30, 89, 23);
				panelReminder.add(btnBackReminder);
					
				// Uncomment to edit panelProfile in Window Builder
				// tabbedPane.add(panelProfile);
	}

	private void panelTravelPlanner() 
	{
		//creates a new panel
		panelTravelPlanner = new JPanel();
		tabbedPane.addTab("Reisplanner", null, panelTravelPlanner, null);
		//buttons for different search options
		JButton btnBus = new JButton("");
		btnBus.setIcon(new ImageIcon(OvApp.class.getResource("/resources/bus_50.png")));
		JButton btnTrain = new JButton("");
		btnTrain.setIcon(new ImageIcon(OvApp.class.getResource("/resources/train_50.png")));
		JButton btnMetro = new JButton("");
		btnMetro.setIcon(new ImageIcon(OvApp.class.getResource("/resources/Metro_50.png")));
		JButton btnTram = new JButton("");
		btnTram.setIcon(new ImageIcon(OvApp.class.getResource("/resources/Tram_50.png")));
		
		lblDeparture = new JLabel("Vertrektijd:");
		lblDestination = new JLabel("Aankomst:");
		btnPlanTrip = new JButton("Zoeken");
		// ActionListener for search button
		btnPlanTrip.addActionListener(actionEvent ->
		{
			// Will switch to the 3rd panel, Location
			tabbedPane.setSelectedIndex(2);
		});
		//an option to input date and time
		SpnrDateTime = new JSpinner();
		SpnrDateTime.setModel(new SpinnerDateModel(new Date(1589234400000L), null, null, Calendar.DAY_OF_YEAR));
		//button that will show the current time
		btnNow = new JButton("Nu");
		
		txtFieldDeparture = new JTextField();
		txtFieldDeparture.setColumns(10);
		
		//automatically groups the buttons at the same height.
		txtFieldDestination = new JTextField();
		txtFieldDestination.setColumns(10);

		// Action Listeners for Transport Type Buttons
		btnTrain.addActionListener(actionEvent -> selectedTransportType = "Train");
		btnBus.addActionListener(actionEvent -> selectedTransportType = "Bus");
		btnMetro.addActionListener(actionEvent -> selectedTransportType = "Metro");
		btnTram.addActionListener(actionEvent -> selectedTransportType = "Tram");

		GroupLayout gl_panelTravelPlanner = new GroupLayout(panelTravelPlanner);
		gl_panelTravelPlanner.setHorizontalGroup(
			gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTravelPlanner.createSequentialGroup()
					.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTravelPlanner.createSequentialGroup()
							.addGap(76)
							.addComponent(btnBus)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTrain, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnMetro, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTram, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelTravelPlanner.createSequentialGroup()
							.addGap(134)
							.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelTravelPlanner.createSequentialGroup()
									.addComponent(SpnrDateTime, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNow, 0, 0, Short.MAX_VALUE))
								.addComponent(btnPlanTrip, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
								.addComponent(lblDeparture, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
								.addComponent(txtFieldDeparture)
								.addComponent(txtFieldDestination, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDestination))))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		
		gl_panelTravelPlanner.setVerticalGroup(
			gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTravelPlanner.createSequentialGroup()
					.addGap(136)
					.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBus)
						.addComponent(btnTrain, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMetro, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTram, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addComponent(lblDeparture)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFieldDeparture, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblDestination)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtFieldDestination, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(53)
					.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.BASELINE)
						.addComponent(SpnrDateTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNow))
					.addGap(28)
					.addComponent(btnPlanTrip, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(121, Short.MAX_VALUE))
		);
		
		panelTravelPlanner.setLayout(gl_panelTravelPlanner);

	}
	
	/**
	 * Panel for displaying available departures
	 * Saved about 100+ lines by not looping over each transport type
	 */
	private void panelLocation() 
	{
		// Create the panel and add it as tab
		panelLocation = new JPanel();
		tabbedPane.addTab("Locatie", null, panelLocation, null);

		// Create a return button to search panel
		btnLocationChange = new JButton("Wijzig reis");
		btnLocationChange.addActionListener(actionEvent -> tabbedPane.setSelectedIndex(1));

		// Create a button for opening detail view
		JButton btnDetails = new JButton("Details");
		btnDetails.addActionListener(actionEvent -> tabbedPane.setSelectedIndex(3));

		// Create labels for departuretime, platform and destination
		lblLocationDeparture = new JLabel("Vertrek:");
		lblLocationDestination = new JLabel("Bestemming:");
		lblLocationDepartureType = new JLabel("Vervoerstype");

		// Create dynamic labels for departuretime, platform and destination from search panel
		JLabel lblDynamicDeparture = new JLabel();
		JLabel lblDynamicDestination = new JLabel();
		JLabel lblDynamicTransportType = new JLabel();

		tblLocation = new JTable();
		JScrollPane scpLocation = new JScrollPane();
		scpLocation.setViewportView(tblLocation);

		// Add content to dynamic labels and table when tab is opened
		tabbedPane.addChangeListener(changeEvent ->
		{
			int activeTab = tabbedPane.getSelectedIndex();

			if (activeTab == 2) // Returns true when Location tab is opened
			{
				String selectedDeparture = txtFieldDeparture.getText();
				String selectedDestination = txtFieldDestination.getText();

				lblDynamicDeparture.setText(selectedDeparture);
				lblDynamicDestination.setText(selectedDestination);
				lblDynamicTransportType.setText(selectedTransportType);

				String[] header = {"Vertrektijk", "Spoor/Halte", "Bestemming"};
				header[1] = selectedTransportType.equals("Bus") ? "Halte" : "Spoor";

				DefaultTableModel dtm = new DefaultTableModel(new Object[][]{}, header);
				tblLocation.setModel(dtm);

				List<TravelTime> travelTimes = new ArrayList<>();

				// Here the new data for selected transport type will be added to the table
				// In a non repeating way saving about 100+ lines
				// Had to replace switch statement with if and else if's
				//
				
				if (selectedTransportType.equals("Bus"))
				{
					// Create the correct dataModel for the travel type
					BusDataModel dataModel = new BusDataModel();
					// Use the getArrivalTimes() method to get all arraval time in the travelTimes variable
					travelTimes = dataModel.getArrivalTimes();
				}
				
				else if (selectedTransportType.equals("Train"))
				{
					TrainDataModel dataModel = new TrainDataModel();
					System.out.println("test train");
					travelTimes = dataModel.getArrivalTimes();
				}
				
				else if (selectedTransportType.equals("Tram"))
				{
					TramDataModel dataModel = new TramDataModel();
					travelTimes = dataModel.getArrivalTimes();
				}
				
				else if (selectedTransportType.equals("Metro"))
				{
					MetroDataModel dataModel = new MetroDataModel();
					travelTimes = dataModel.getArrivalTimes();
				}

				// Loop over items in the travelTimes list
				for (TravelTime travelTime : travelTimes)
				{
					// Check if to and from fields in search panel match departure and arrival
					if (travelTime.getDestination().contains(selectedDestination) &&
							travelTime.getStationName().contains(selectedDeparture))
					{
						// Add row to table if filter criteria are met
						dtm.addRow(new Object[]
								{
										// Add data to columns
										travelTime.getDepartureTime(),
										travelTime.getPlatform(),
										travelTime.getDestination(),
								}
						);
					}
				}
			}
		});

		//From here on only auto generated styling and adding of components
		//
		GroupLayout gl_panelLocation = new GroupLayout(panelLocation);
		gl_panelLocation.setHorizontalGroup(
				gl_panelLocation.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelLocation.createSequentialGroup()
								.addContainerGap(20, Short.MAX_VALUE)
								.addGroup(gl_panelLocation.createParallelGroup(Alignment.LEADING)
										.addComponent(btnDetails, Alignment.TRAILING)
										.addGroup(Alignment.TRAILING, gl_panelLocation.createParallelGroup(Alignment.LEADING)
												.addComponent(scpLocation, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panelLocation.createSequentialGroup()
														.addGroup(gl_panelLocation.createParallelGroup(Alignment.LEADING, false)
																.addComponent(lblLocationDepartureType, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(btnLocationChange)
																.addComponent(lblLocationDeparture, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(lblLocationDestination, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
														.addGap(18)
														.addGroup(gl_panelLocation.createParallelGroup(Alignment.LEADING)
																.addComponent(lblDynamicTransportType, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblDynamicDestination, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblDynamicDeparture)))))
								.addContainerGap())
		);
		
		gl_panelLocation.setVerticalGroup(
				gl_panelLocation.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelLocation.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnLocationChange)
								.addGap(18)
								.addGroup(gl_panelLocation.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLocationDestination)
										.addComponent(lblDynamicDeparture))
								.addGap(18)
								.addGroup(gl_panelLocation.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLocationDeparture)
										.addComponent(lblDynamicDestination))
								.addGap(18)
								.addGroup(gl_panelLocation.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLocationDepartureType)
										.addComponent(lblDynamicTransportType))
								.addGap(18)
								.addComponent(scpLocation, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
								.addGap(18)
								.addComponent(btnDetails)
								.addContainerGap())
		);
		
		panelLocation.setLayout(gl_panelLocation);
	}
	
	private void panelMap() 
	{	
		
		
        open_map();   
		
		panelMap = new JPanel();																	// make a new panel named panelMap
		panelMap.setBackground(Color.WHITE);														// set the background to the color white
		tabbedPane.addTab("Kaart", null, panelMap, null);
		panelMap.setLayout(null);
		
		//labels		
		lblDepartureTime = new JLabel("Vertrektijd:");		
		lblDepartureTime.setBounds(44, 39, 84, 25);
		lblDepartureTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelMap.add(lblDepartureTime);
		
		lblArrivalTime = new JLabel("Aankomsttijd:");										// make new label named lblArrivalTime
		lblArrivalTime.setBounds(44, 75, 84, 25);													//Set the outlining design for the button
		lblArrivalTime.setFont(new Font("Tahoma", Font.BOLD, 11));									//set the text in a new design, called "font" in programming
		panelMap.add(lblArrivalTime);
		
		lblTotalTime = new JLabel("Totale tijd:");
		lblTotalTime.setBounds(44, 135, 84, 25);
		lblTotalTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelMap.add(lblTotalTime);

		// Quick fix
		lblTransfer = new JLabel("Traject Opslaan");
		lblTransfer.setBounds(44, 169, 84, 25);
		lblTransfer.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelMap.add(lblTransfer);
		
		lblTrackDeparture = new JLabel("Spoor:");
		lblTrackDeparture.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTrackDeparture.setBounds(282, 39, 56, 25);
		panelMap.add(lblTrackDeparture);
		
		lblTrackArrival = new JLabel("Spoor:");
		lblTrackArrival.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTrackArrival.setBounds(282, 75, 56, 25);
		panelMap.add(lblTrackArrival);
		
		lblPrice = new JLabel("Prijs:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(282, 135, 56, 25);
		panelMap.add(lblPrice);
		
		JLabel lblDepartureTimeTxt = new JLabel("<dynamic>");
		lblDepartureTimeTxt.setBounds(138, 39, 70, 19);
		panelMap.add(lblDepartureTimeTxt);
		
		JLabel lblArrivalTimeTxt = new JLabel("<dynamic>");
		lblArrivalTimeTxt.setBounds(138, 75, 70, 19);
		panelMap.add(lblArrivalTimeTxt);
		
		JLabel lblTotalTimeTxt = new JLabel("<dynamic>");
		lblTotalTimeTxt.setBounds(138, 135, 70, 25);
		panelMap.add(lblTotalTimeTxt);

		// Quick fix
		JLabel lblTransferTxt = new JLabel("");
		lblTransferTxt.setBounds(138, 169, 70, 23);
		panelMap.add(lblTransferTxt);
		
		JLabel lblTrackDepartureTxt = new JLabel("<dynamic>");
		lblTrackDepartureTxt.setBounds(348, 40, 70, 23);
		panelMap.add(lblTrackDepartureTxt);
		
		JLabel lblTrackArrivalTxt = new JLabel("<dynamic>");
		lblTrackArrivalTxt.setBounds(348, 75, 70, 23);
		panelMap.add(lblTrackArrivalTxt);
		
		JLabel lblPriceTxt = new JLabel("<dynamic>");
		lblPriceTxt.setBounds(348, 135, 70, 23);
		panelMap.add(lblPriceTxt);
		
		//buttons
		JButton btnLocationArrival = new JButton("");
		btnLocationArrival.setBounds(10, 76, 25, 23);
		btnLocationArrival.setBackground(Color.WHITE);
		btnLocationArrival.setIcon(new ImageIcon(OvApp.class.getResource("/resources/locationblack.png")));
		panelMap.add(btnLocationArrival);
		
		JButton btnLocationDeparture = new JButton("");
		btnLocationDeparture.setBounds(10, 40, 25, 23);
		btnLocationDeparture.setBackground(Color.WHITE);
		btnLocationDeparture.setIcon(new ImageIcon(OvApp.class.getResource("/resources/locationwhitee.png")));
		panelMap.add(btnLocationDeparture);
		
		JButton btnTotalTime = new JButton("");
		btnTotalTime.setBounds(10, 135, 25, 23);
		btnTotalTime.setIcon(new ImageIcon(OvApp.class.getResource("/resources/time.png")));
		btnTotalTime.setBackground(Color.WHITE);
		panelMap.add(btnTotalTime);
		
		JButton btnTransfers = new JButton("");
		btnTransfers.setBounds(10, 169, 25, 23);
		btnTransfers.setBackground(Color.WHITE);
		btnTransfers.setIcon(new ImageIcon(OvApp.class.getResource("/resources/saved.png")));
		panelMap.add(btnTransfers);
		
		JButton btnTrackDeparture = new JButton("");
		btnTrackDeparture.setBounds(247, 40, 25, 23);
		btnTrackDeparture.setBackground(Color.WHITE);
		btnTrackDeparture.setIcon(new ImageIcon(OvApp.class.getResource("/resources/track1.png")));
		panelMap.add(btnTrackDeparture);
		
		JButton btnTrackArrival = new JButton("");
		btnTrackArrival.setBounds(247, 76, 25, 23);
		btnTrackArrival.setBackground(Color.WHITE);
		btnTrackArrival.setIcon(new ImageIcon(OvApp.class.getResource("/resources/track1.png")));
		panelMap.add(btnTrackArrival);
		
		JButton btnPrice = new JButton("");
		btnPrice.setBounds(247, 135, 25, 23);
		btnPrice.setBackground(Color.WHITE);
		btnPrice.setIcon(new ImageIcon(OvApp.class.getResource("/resources/pay.png")));
		panelMap.add(btnPrice);
		
		//srollpane
		JScrollPane scrollPaneMap = new JScrollPane();
		scrollPaneMap.setBounds(296, 233, 184, 276);
		panelMap.add(scrollPaneMap);
		
		//table
		tableMap = new JTable();									//make a new table named tableMap
		tableMap.setModel(new DefaultTableModel(					//set a model for tableMap	
			new Object[][] {				
				{null, null},										// all empty"null" for show in gui.
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			
			new String[] 
			{											//naming for the colums/rows
				"Tijd", "Stop"
			}
		));
		
		scrollPaneMap.setViewportView(tableMap);					// set the tableMap in scrollpane, this will make the table scrollable
		
		JButton btnDistance = new JButton("");
		btnDistance.setBackground(Color.WHITE);
		btnDistance.setBounds(247, 167, 25, 23);
		btnDistance.setIcon(new ImageIcon(OvApp.class.getResource("/resources/transfer.png")));
		panelMap.add(btnDistance);
		
		lblDistance_1 = new JLabel("Afstand:");
		lblDistance_1.setFont(new Font("Dialog", Font.BOLD, 11));
		lblDistance_1.setBounds(282, 167, 84, 25);
		panelMap.add(lblDistance_1);
		
		JLabel lblDistanceTxt = new JLabel("<dynamic>");
		lblDistanceTxt.setBounds(348, 170, 70, 23);
		panelMap.add(lblDistanceTxt);
		
	
		internalFrame = new JInternalFrame("Map");
		internalFrame.setToolTipText("");
		internalFrame.setFrameIcon(null);
		internalFrame.setBorder(null);
		internalFrame.setBounds(10, 233, 262, 282);
		panelMap.add(internalFrame);
		
		
		


		// Seanan and Raymond working on awesome code for calculating distance
		// Code will execute when user selects the detail view tab
		//
		
		tabbedPane.addChangeListener(changeEvent ->
		{
			int selectedTab = tabbedPane.getSelectedIndex();
			// Execute code when map tab is open
			
			if (selectedTab == 3)
			{
				int selectedRow = tblLocation.getSelectedRow();

				if (selectedRow >= 0)
				{
					// Retrieve selected info
					String departureTime = String.valueOf(tblLocation.getModel().getValueAt(selectedRow, 0));
					String platform = String.valueOf(tblLocation.getModel().getValueAt(selectedRow, 1));
					String destination = String.valueOf(tblLocation.getModel().getValueAt(selectedRow, 2));

					
					if (selectedTransportType.equals("Bus"))
					{
						BusDataModel busDataModel = new BusDataModel();
						travelTime = busDataModel.getTravelTime(
								departureTime,
								platform,
								destination
						);
					}
					else if (selectedTransportType.equals("Train"))
					{
						TrainDataModel trainDataModel = new TrainDataModel();
						travelTime = trainDataModel.getTravelTime(
								departureTime,
								platform,
								destination
						);
					}
					else if (selectedTransportType.equals("Tram"))
					{
						TramDataModel tramDataModel = new TramDataModel();
						travelTime = tramDataModel.getTravelTime(
								departureTime,
								platform,
								destination
						);
					}
					else if (selectedTransportType.equals("Metro"))
					{
						MetroDataModel metroDataModel = new MetroDataModel();
						travelTime = metroDataModel.getTravelTime(
								departureTime,
								platform,
								destination
						);
					}
				}
				else 
				{
					int selectedSavedRow = tableSaved.getSelectedRow();
					
					if (selectedSavedRow >= 0)
					{
						//String transportType = String.valueOf(tableSaved.getModel().getValueAt(selectedSavedRow, 0));
						selectedTransportType = String.valueOf(tableSaved.getModel().getValueAt(selectedSavedRow, 0));
						String departureTime = String.valueOf(tableSaved.getModel().getValueAt(selectedSavedRow, 1));
						String departure = String.valueOf(tableSaved.getModel().getValueAt(selectedSavedRow, 2));
						String arrivalTime = String.valueOf(tableSaved.getModel().getValueAt(selectedSavedRow, 3));
						String destination = String.valueOf(tableSaved.getModel().getValueAt(selectedSavedRow, 4));
						System.out.println(destination);
						
						if (selectedTransportType.equals("Bus"))
						{
							BusDataModel busDataModel = new BusDataModel();
							travelTime = busDataModel.getSavedTravelTime(
									departureTime,
									departure,
									arrivalTime,
									destination
							);
						}
						else if (selectedTransportType.equals("Metro"))
						{
							MetroDataModel metroDataModel = new MetroDataModel();
							travelTime = metroDataModel.getSavedTravelTime(
									departureTime,
									departure,
									arrivalTime,
									destination
							);
						}
						else if (selectedTransportType.equals("Trein"))
						{
							TrainDataModel trainDataModel = new TrainDataModel();
							travelTime = trainDataModel.getSavedTravelTime(
									departureTime,
									departure,
									arrivalTime,
									destination
							);

						}
						else if (selectedTransportType.equals("Tram"))
						{
							TramDataModel tramDataModel = new TramDataModel();
							travelTime = tramDataModel.getSavedTravelTime(
									departureTime,
									departure,
									arrivalTime,
									destination
							);
						}
					}
				}
				// Add data from table to labels
				lblDepartureTimeTxt.setText(travelTime.getDepartureTime());
				lblArrivalTimeTxt.setText(travelTime.getArrivalTime());
				lblTotalTimeTxt.setText(travelTime.getDuration());
				lblDistanceTxt.setText(travelTime.getDistance());
				lblTrackDepartureTxt.setText(travelTime.getPlatform());
				lblTrackArrivalTxt.setText(travelTime.getDestination());

				// Quick fix to get to the departure location
				departureFix = travelTime.getStationName();
			}
		});

		btnTransfers.addActionListener(actionEvent ->
		{
			String departureTime = lblDepartureTimeTxt.getText();
			String arrivalTime = lblArrivalTimeTxt.getText();
			String destination = lblTrackArrivalTxt.getText();
			int loggedInUser = newProfile.getId();
			int transportType = 0;

			// Quick fix to get to the departure location
			String departure = departureFix;

			if (selectedTransportType.equals("Bus"))
			{
				transportType = 1;
			}
			else if (selectedTransportType.equals("Metro"))
			{
				transportType = 2;
			}
			else if (selectedTransportType.equals("Train"))
			{
				transportType = 3;
			}
			else if (selectedTransportType.equals("Tram"))
			{
				transportType = 4;
			}


			DBHandler dbHandler = new DBHandler();
			dbHandler.saveTime(departureTime, departure, arrivalTime, destination, transportType, loggedInUser);
		});
	}		
	
	private void panelDelays() 
	{
		panelDelays = new JPanel();
		tabbedPane.addTab("Vertragingen", null, panelDelays, null);
		lblVertragingen = new JLabel("Vertragingen:");
		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_panelDelays = new GroupLayout(panelDelays);
		gl_panelDelays.setHorizontalGroup(
			gl_panelDelays.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDelays.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDelays.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblVertragingen, Alignment.LEADING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelDelays.setVerticalGroup(
			gl_panelDelays.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDelays.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblVertragingen)
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(159, Short.MAX_VALUE))
		);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				
			new Object[][] 
			{
				{"Amersfoort", "Groningen", "00:15"},
				{"Heerenveen", "Groningen", "00:15"},
				{"Delft", "Groningen", "00:15"},
			},
			
			new String[] 
			{
				"Vertrekpunt", "Aankomstpunt", "Vertragingen"
			}
			
		));
		panelDelays.setLayout(gl_panelDelays);
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() 
	{
		panelFavorites = new JPanel();
		panelFavorites.setBackground(Color.WHITE);
		
		panelSaved = new JPanel();
		panelSaved.setBackground(Color.WHITE);
		panelSaved.setLayout(null);
		
		panelReminder = new JPanel();
		panelReminder.setBackground(Color.WHITE);
		panelReminder.setLayout(null);
		/*
		tabbedPane.add(panelFavorites);
		tabbedPane.addTab("Favorieten", null ,panelFavorites, null);
		panelFavorites.setLayout(null);
		*/
	}
	/*
	 * This method contains all of the code for creating events
	 */
	private void open_map() 
	{
		System.setProperty("jxbrowser.license.key", "6P830J66YAN5IR2Z6GR197J3OHDLYJNT0WAO11SZM8RRGG9S816S0QPEY2NCP251WS5J");
    	System.setProperty("teamdev.license.info", "true");
    	
    	// Creating and running Chromium engine
        Engine engine = Engine.newInstance(
                EngineOptions.newBuilder(HARDWARE_ACCELERATED).build());

        Browser browser = engine.newBrowser();
        // Loading the required web page
        browser.navigation().loadUrl("resources/simple_map.html");
       

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
    		
        });  
		
	}

	private void createEvents() 
	{
		
	}
}







