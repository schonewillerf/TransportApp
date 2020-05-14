package adsd.app.ovapp.ovapp;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.util.Objects;

import static adsd.app.ovapp.ovapp.DBConnection.Connection;

public class TestFrame 
{

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

	private JTextField txtFieldDeparture;
	private JTextField txtFieldDestination;
	private JTextField userName;

	private JPasswordField password;

	private JButton btnLogin;

	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel userid;

	private Profile newProfile = new Profile();

	private Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					TestFrame window = new TestFrame();
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
	
	public void SwitchPanels()
	{
		tabbedPane.removeAll();
		
	}
	
	public void AddPanels()
	{
		tabbedPane.addTab("Profiel", null, panelProfile, null);
		tabbedPane.addTab("Reisplanner", null, panelTravelPlanner, null);
		tabbedPane.addTab("Locatie", null, panelLocation, null);
		tabbedPane.addTab("Kaart", null, panelMap, null);
		tabbedPane.addTab("Vertragingen", null, panelDelays, null);
	}
	
	public TestFrame() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 521, 716);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(OvApp.class.getResource("/resources/train_128.png")));
		frame.getContentPane().setLayout(null);
		
		//tabbedpane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 485, 655);
		frame.getContentPane().add(tabbedPane);
		
		initialize();
		createEvents();
		Panel_Profile();
		Panel_Login();
		Panel_TravelPlanner();
		Panel_Location();
		Panel_Map();
		Panel_Delays();
	
	}

	private void Panel_Login()
	{
		panelLogin = new JPanel();
		panelLogin.setBackground(Color.WHITE);
		tabbedPane.addTab("Profiel", null, panelLogin, null);
		panelLogin.setLayout(null);

		userid = new JLabel("");
		userid.setBounds(50,50, 100, 30);
		panelFavorites.add(userid);

		JLabel title = new JLabel("Login");
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
				PreparedStatement ds = conn.prepareStatement("SELECT * FROM profile WHERE emailAdress=? AND password=?");
				ds.setString(1, String.valueOf(usn));
				ds.setString(2, String.valueOf(psd));
				ResultSet es = ds.executeQuery();
				if (es.next()) {
					System.out.println("utilisateur existant");

					// add panels after loggin in
					tabbedPane.remove(panelLogin);
					AddPanels();
					panelProfile.revalidate();


					PreparedStatement ps = conn.prepareStatement("SELECT * FROM profile");
					ResultSet rs = ps.executeQuery();

					while (rs.next())
					{
						newProfile = new Profile(rs.getInt("ID"), rs.getInt("age"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("streetName"), rs.getString("residence"), rs.getString("card"));

						userid.setText(String.valueOf(rs.getInt("ID")));
						label_4.setText(rs.getString("card"));
						label_5.setText(rs.getString("firstName")+ rs.getString("lastName"));
						label_6.setText(rs.getString("age"));
						label_7.setText(rs.getString("residence"));
						label_8.setText(rs.getString("streetName"));

					}


				}

				else {
					System.out.println("utilisateur inexistant");
				}
			}


			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}

		});

		title.setBounds(150, 25,200, 20);
		lgn.setBounds(25, 50,150, 20);
		whtd.setBounds(25, 75,150, 20);
		userName.setBounds(200, 50,200, 20);
		password.setBounds(200, 75,200, 20);
		btnLogin.setBounds(180,110, 150,25);

		panelLogin.add(title);
		panelLogin.add(lgn);
		panelLogin.add(whtd);
		panelLogin.add(userName);
		panelLogin.add(password);
		panelLogin.add(btnLogin);

	}

	public void Panel_Profile() 
	{
		
				panelProfile = new JPanel();
				panelProfile.setBackground(Color.WHITE);

				panelProfile.setLayout(null);
				//Labels
				JLabel lbimage = new JLabel();
				lbimage.setBackground(UIManager.getColor("ToolBar.highlight"));
				lbimage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/rsz_1profile.jpg")));
				lbimage.setBounds(24, 21, 207, 222);
				panelProfile.add(lbimage);

				JLabel lbMyCard = new JLabel("Kaart:");
				lbMyCard.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbMyCard.setBounds(55, 281, 74, 20);
				panelProfile.add(lbMyCard);

				JLabel label = new JLabel("Naam:");
				label.setFont(new Font("Tahoma", Font.BOLD, 11));
				label.setBounds(55, 322, 74, 20);
				panelProfile.add(label);
				
				JLabel label_1 = new JLabel("Leeftijd:");
				label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_1.setBounds(55, 353, 74, 20);
				panelProfile.add(label_1);
				
				JLabel label_2 = new JLabel("Stad:");
				label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_2.setBounds(55, 390, 74, 20);
				panelProfile.add(label_2);
				
				JLabel label_3 = new JLabel("Straatnaam:");
				label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
				label_3.setBounds(55, 421, 74, 20);
				panelProfile.add(label_3);

				label_4 = new JLabel("nummer");
				label_4.setHorizontalAlignment(SwingConstants.LEFT);
				label_4.setBounds(141, 281, 132, 14);
				panelProfile.add(label_4);

				label_5 = new JLabel("Jesse");
				label_5.setHorizontalAlignment(SwingConstants.LEFT);
				label_5.setBounds(141, 325, 132, 14);
				panelProfile.add(label_5);
				
				label_6 = new JLabel("22");
				label_6.setHorizontalAlignment(SwingConstants.LEFT);
				label_6.setBounds(139, 356, 140, 14);
				panelProfile.add(label_6);
				
				label_7 = new JLabel("Amsterdam");
				label_7.setHorizontalAlignment(SwingConstants.LEFT);
				label_7.setBounds(139, 393, 140, 14);
				panelProfile.add(label_7);
				
				label_8 = new JLabel("Kattenburg 12");
				label_8.setHorizontalAlignment(SwingConstants.LEFT);
				label_8.setBounds(139, 424, 140, 14);
				panelProfile.add(label_8);
				

				
				JLabel lbMySubscription = new JLabel("Mijn beschrijving:");
				lbMySubscription.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbMySubscription.setBounds(55, 467, 118, 20);
				panelProfile.add(lbMySubscription);
				
				JLabel lbFavorites = new JLabel("Favorieten:");
				lbFavorites.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbFavorites.setBounds(301, 43, 74, 20);
				panelProfile.add(lbFavorites);
				
				JLabel lbSaved = new JLabel("Opgeslagen:");
				lbSaved.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbSaved.setBounds(301, 85, 74, 20);
				panelProfile.add(lbSaved);
				
				JLabel lbReminders = new JLabel("Herinneringen:");
				lbReminders.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbReminders.setBounds(301, 138, 94, 20);
				panelProfile.add(lbReminders);
				
				JLabel lbHome = new JLabel();
				lbHome.setIcon(new ImageIcon(OvApp.class.getResource("/resources/homecolor.png")));
				lbHome.setHorizontalAlignment(SwingConstants.LEFT);
				lbHome.setBounds(24, 383, 24, 27);
				panelProfile.add(lbHome);
				
				//textpanes
				JTextPane textPane = new JTextPane();
				textPane.setBackground(new Color(211, 211, 211));
				textPane.setBounds(24, 505, 386, 111);
				panelProfile.add(textPane);
				
				//buttons
				JButton button = new JButton("Wijzig profiel");
				button.setBounds(24, 243, 124, 20);
				panelProfile.add(button);
				
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
						
						SwitchPanels();
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
						SwitchPanels();
						tabbedPane.add(panelSaved);
						tabbedPane.addTab("Opgeslagen", null ,panelSaved, null);
						
					}
				});
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
						SwitchPanels();
						tabbedPane.add(panelReminder);
						tabbedPane.addTab("Herinneringen", null , panelReminder, null);
					}
				});
				btnReminder.setForeground(Color.WHITE);
				btnReminder.setBackground(Color.WHITE);
				btnReminder.setIcon(new ImageIcon(OvApp.class.getResource("/resources/reminder.png")));
				btnReminder.setBounds(262, 135, 29, 23);
				panelProfile.add(btnReminder);
				
				//buttons "back"
				JButton btnBackFavorites = new JButton("Terug ");
				btnBackFavorites.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						AddPanels();
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
						AddPanels();
						tabbedPane.remove(panelSaved);
						
					}
				});
				btnBackSaved.setBounds(332, 57, 89, 23);
				panelSaved.add(btnBackSaved);
				
				JButton btnBackReminder = new JButton("Terug");
				btnBackReminder.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						AddPanels();
						tabbedPane.remove(panelReminder);
						
					}
				});
				btnBackReminder.setBounds(316, 30, 89, 23);
				panelReminder.add(btnBackReminder);
					

	}
	
	public void Panel_TravelPlanner() 
	{
		//creates a new panel
		panelTravelPlanner = new JPanel();
		tabbedPane.addTab("Reisplanner", null, panelTravelPlanner, null);
		//buttons for different search options
		JButton btnBus = new JButton("");
		btnBus.setIcon(new ImageIcon(TestFrame.class.getResource("/Resources/bus_50.png")));
		
		JButton btnTrain = new JButton("");
		btnTrain.setIcon(new ImageIcon(TestFrame.class.getResource("/Resources/train_50.png")));
		
		JButton btnMetro = new JButton("");
		btnMetro.setIcon(new ImageIcon(TestFrame.class.getResource("/Resources/Metro_50.png")));
		
		JButton btnTram = new JButton("");
		btnTram.setIcon(new ImageIcon(TestFrame.class.getResource("/Resources/Tram_50.png")));
		
		JLabel lblDeparture = new JLabel("Vertrek:");
		
		JLabel lblDestination = new JLabel("Aankomst:");
		
		JButton btnPlanTrip = new JButton("Zoeken");
		//an option to input date and time
		JSpinner SpnrDateTime = new JSpinner();
		SpnrDateTime.setModel(new SpinnerDateModel(new Date(1589234400000L), null, null, Calendar.DAY_OF_YEAR));
		//button that will show the current time
		JButton btnNow = new JButton("Nu");
		
		txtFieldDeparture = new JTextField();
		txtFieldDeparture.setColumns(10);
		
		//automatically groups the buttons at the same height.
		txtFieldDestination = new JTextField();
		txtFieldDestination.setColumns(10);
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
					.addContainerGap(124, Short.MAX_VALUE))
		);
		panelTravelPlanner.setLayout(gl_panelTravelPlanner);
		
		
		
	}
	
	public void Panel_Location() 
	{
		panelLocation = new JPanel();
		tabbedPane.addTab("Locatie", null, panelLocation, null);
		
	}
	
	public void Panel_Map() 
	{
		panelMap = new JPanel();
		tabbedPane.addTab("Kaart", null, panelMap, null);
		
	}
	
	public void Panel_Delays() 
	{
		panelDelays = new JPanel();
		tabbedPane.addTab("Vertragingen", null, panelDelays, null);
		
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

	private void createEvents() 
	{
		
	}
}