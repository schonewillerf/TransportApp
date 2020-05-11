package adsd.app.ovapp.ovapp;
//asdasdas
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class TestFrame {

	private JFrame frame;
	private JTextField txtFieldDeparture;
	private JTextField txtFldDestination;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame window = new TestFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public void SwitchPanels(JTabbedPane tabbedPane)
	{
		tabbedPane.removeAll();
		tabbedPane.repaint();
		tabbedPane.revalidate();
	}
	public TestFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 521, 716);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(OvApp.class.getResource("/resources/train_128.png")));
		frame.getContentPane().setLayout(null);
		
		//tabbedpane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 485, 655);
		frame.getContentPane().add(tabbedPane);
		
		
		//panels
		JPanel panelProfile = new JPanel();
		panelProfile.setBackground(Color.WHITE);
		tabbedPane.addTab("Profiel", null, panelProfile, null);
		panelProfile.setLayout(null);
		
		
		JPanel panelTravelPlanner = new JPanel();
		tabbedPane.addTab("Reisplanner", null, panelTravelPlanner, null);
		
		JButton btnMetro = new JButton("");
		btnMetro.setIcon(new ImageIcon(TestFrame.class.getResource("/Resources/Metro_50.png")));
		
		JButton btnTram = new JButton("");
		btnTram.setIcon(new ImageIcon(TestFrame.class.getResource("/Resources/Tram_50.png")));
		
		JButton btnBus = new JButton("");
		btnBus.setIcon(new ImageIcon(TestFrame.class.getResource("/Resources/bus_50.png")));
		
		JButton btnTrain = new JButton("");
		btnTrain.setIcon(new ImageIcon(TestFrame.class.getResource("/Resources/train_50.png")));
		
		JLabel lblDeparture = new JLabel("Vertrek:");
		
		JLabel lblDestination = new JLabel("Bestemming:");
		
		txtFieldDeparture = new JTextField();
		txtFieldDeparture.setColumns(10);
		
		txtFldDestination = new JTextField();
		txtFldDestination.setColumns(10);
		
		JSpinner SpnrDateAndTime = new JSpinner();
		SpnrDateAndTime.setModel(new SpinnerDateModel(new Date(1589148000000L), null, null, Calendar.DAY_OF_YEAR));
		
		JButton btnNow = new JButton("Nu");
		
		JButton btnShowTrip = new JButton("Zoeken");
		GroupLayout gl_panelTravelPlanner = new GroupLayout(panelTravelPlanner);
		gl_panelTravelPlanner.setHorizontalGroup(
			gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTravelPlanner.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelTravelPlanner.createSequentialGroup()
							.addComponent(btnMetro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTram, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBus, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
							.addComponent(lblDeparture)
							.addComponent(txtFieldDeparture, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDestination)
							.addComponent(txtFldDestination, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnShowTrip, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_panelTravelPlanner.createSequentialGroup()
									.addComponent(SpnrDateAndTime, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNow, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnTrain, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(80, Short.MAX_VALUE))
		);
		gl_panelTravelPlanner.setVerticalGroup(
			gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTravelPlanner.createSequentialGroup()
					.addGap(127)
					.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
						.addComponent(btnTrain, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBus, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTram, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMetro))
					.addGap(58)
					.addComponent(lblDeparture)
					.addGap(18)
					.addComponent(txtFieldDeparture, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDestination)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtFldDestination, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.BASELINE)
						.addComponent(SpnrDateAndTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNow))
					.addGap(34)
					.addComponent(btnShowTrip, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(111, Short.MAX_VALUE))
		);
		panelTravelPlanner.setLayout(gl_panelTravelPlanner);
		
		JPanel panelLocation = new JPanel();
		tabbedPane.addTab("Locatie", null, panelLocation, null);
		
		JPanel panelMap = new JPanel();
		tabbedPane.addTab("Kaart", null, panelMap, null);
		
		JPanel panelDelays = new JPanel();
		tabbedPane.addTab("Vertragingen", null, panelDelays, null);
		
		JPanel panelFavorites = new JPanel();
		panelFavorites.setBackground(Color.WHITE);
		/*
		tabbedPane.add(panelFavorites);
		tabbedPane.addTab("Favorieten", null ,panelFavorites, null);
		panelFavorites.setLayout(null);
		*/
		
		//Labels
		JLabel lbimage = new JLabel();
		lbimage.setBackground(UIManager.getColor("ToolBar.highlight"));
		lbimage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/rsz_1profile.jpg")));
		lbimage.setBounds(24, 21, 207, 222);
		panelProfile.add(lbimage);
		
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
		
		JLabel label_4 = new JLabel("Jack Piraat");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setBounds(141, 325, 132, 14);
		panelProfile.add(label_4);
		
		JLabel label_5 = new JLabel("23");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setBounds(139, 356, 140, 14);
		panelProfile.add(label_5);
		
		JLabel label_6 = new JLabel("Amsterdam");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setBounds(139, 393, 140, 14);
		panelProfile.add(label_6);
		
		JLabel label_7 = new JLabel("Kattenburg 12");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setBounds(139, 424, 140, 14);
		panelProfile.add(label_7);
		
		JLabel lbMyCard = new JLabel("Kaart:");
		lbMyCard.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMyCard.setBounds(55, 281, 74, 20);
		panelProfile.add(lbMyCard);
		
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
		btnCard.setForeground(Color.WHITE);
		btnCard.setBackground(Color.WHITE);
		btnCard.setIcon(new ImageIcon(OvApp.class.getResource("/resources/mycard.png")));
		btnCard.setBounds(24, 278, 29, 23);
		panelProfile.add(btnCard);
		
		
		JButton btnBack = new JButton("Terug ");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.addTab("Profiel", null, panelProfile, null);
				tabbedPane.addTab("Reisplanner", null, panelTravelPlanner, null);
				tabbedPane.addTab("Locatie", null, panelLocation, null);
				tabbedPane.addTab("Kaart", null, panelMap, null);
				tabbedPane.addTab("Vertragingen", null, panelDelays, null);
				
				tabbedPane.remove(panelFavorites);
				
			}
		});
		btnBack.setBounds(364, 28, 89, 23);
		panelFavorites.add(btnBack);
		
		JButton btnFavorites = new JButton();
		btnFavorites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.removeAll();
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
		btnSaved.setForeground(Color.WHITE);
		btnSaved.setBackground(Color.WHITE);
		btnSaved.setIcon(new ImageIcon(OvApp.class.getResource("/resources/saved.png")));
		btnSaved.setBounds(262, 85, 29, 23);
		panelProfile.add(btnSaved);
		
		JButton btnReminder = new JButton();
		btnReminder.setForeground(Color.WHITE);
		btnReminder.setBackground(Color.WHITE);
		btnReminder.setIcon(new ImageIcon(OvApp.class.getResource("/resources/reminder.png")));
		btnReminder.setBounds(262, 135, 29, 23);
		panelProfile.add(btnReminder);
		

		
		
		
	
		
		
		
		
		
	}
}
