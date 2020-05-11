package adsd.app.ovapp.ovapp;

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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTextField;

public class TestFrame {

	private JFrame frame;
	private JTextField txtFldDestination;
	private JTextField textFldDeparture;

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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 485, 655);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelProfile = new JPanel();
		panelProfile.setBackground(UIManager.getColor("Tree.background"));
		tabbedPane.addTab("Profiel", null, panelProfile, null);
		panelProfile.setLayout(null);
		
		JLabel lbimage = new JLabel();
		lbimage.setBackground(UIManager.getColor("ToolBar.highlight"));
		lbimage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/rsz_1profile.jpg")));
		lbimage.setBounds(24, 21, 207, 222);
		panelProfile.add(lbimage);
		
		JButton button = new JButton("Edit profile");
		button.setBounds(24, 243, 105, 20);
		panelProfile.add(button);
		
		JLabel label = new JLabel("Name:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(55, 322, 74, 20);
		panelProfile.add(label);
		
		JLabel label_1 = new JLabel("Age:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(55, 353, 74, 20);
		panelProfile.add(label_1);
		
		JLabel label_2 = new JLabel("City:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(55, 390, 74, 20);
		panelProfile.add(label_2);
		
		JLabel label_3 = new JLabel("Streetname:");
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
		
		JLabel lbMyCard = new JLabel("My Card:");
		lbMyCard.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMyCard.setBounds(55, 281, 74, 20);
		panelProfile.add(lbMyCard);
		
		JLabel lbMySubscription = new JLabel("My subcription:");
		lbMySubscription.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMySubscription.setBounds(55, 467, 118, 20);
		panelProfile.add(lbMySubscription);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(211, 211, 211));
		textPane.setBounds(24, 505, 386, 111);
		panelProfile.add(textPane);
		
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
		
		JLabel lbimageCard = new JLabel();
		lbimageCard.setIcon(new ImageIcon(OvApp.class.getResource("/resources/mycard.png")));
		lbimageCard.setBounds(21, 281, 24, 20);
		panelProfile.add(lbimageCard);
		
		JLabel lbPencil = new JLabel();
		lbPencil.setHorizontalAlignment(SwingConstants.LEFT);
		lbPencil.setIcon(new ImageIcon(OvApp.class.getResource("/resources/toolpencil.png")));
		lbPencil.setBounds(24, 467, 24, 27);
		panelProfile.add(lbPencil);
		
		JLabel lbHome = new JLabel();
		lbHome.setIcon(new ImageIcon(OvApp.class.getResource("/resources/homecolor.png")));
		lbHome.setHorizontalAlignment(SwingConstants.LEFT);
		lbHome.setBounds(24, 383, 24, 27);
		panelProfile.add(lbHome);
		
		JLabel lbFavoritesimage = new JLabel();
		lbFavoritesimage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/favorites.png")));
		lbFavoritesimage.setHorizontalAlignment(SwingConstants.LEFT);
		lbFavoritesimage.setBounds(267, 43, 24, 27);
		panelProfile.add(lbFavoritesimage);
		
		JLabel lbSavedimage = new JLabel();
		lbSavedimage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/saved.png")));
		lbSavedimage.setHorizontalAlignment(SwingConstants.LEFT);
		lbSavedimage.setBounds(267, 85, 24, 27);
		panelProfile.add(lbSavedimage);
		
		JLabel lblReminderimage = new JLabel();
		lblReminderimage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/reminder.png")));
		lblReminderimage.setHorizontalAlignment(SwingConstants.LEFT);
		lblReminderimage.setBounds(267, 131, 24, 27);
		panelProfile.add(lblReminderimage);
		
		
		JPanel panelTravelPlanner = new JPanel();
		tabbedPane.addTab("Reisplanner", null, panelTravelPlanner, null);
		
		JButton btnTram = new JButton("");
		btnTram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTram.setIcon(new ImageIcon(TestFrame.class.getResource("/Resources/Tram_50.png")));
		
		JButton btnTrain = new JButton("");
		btnTrain.setIcon(new ImageIcon(TestFrame.class.getResource("/Resources/train_50.png")));
		
		JButton btnBus = new JButton("");
		btnBus.setIcon(new ImageIcon(TestFrame.class.getResource("/Resources/bus_50.png")));
		
		JButton btnMetro = new JButton("");
		btnMetro.setIcon(new ImageIcon(TestFrame.class.getResource("/Resources/Metro_50.png")));
		
		JButton btnShowTrip = new JButton("Zoeken");
		
		JButton btnSearchNow = new JButton("Nu");
		
		JSpinner SpnrDateAndTime = new JSpinner();
		SpnrDateAndTime.setModel(new SpinnerDateModel(new Date(1588888800000L), null, null, Calendar.DAY_OF_YEAR));
		
		txtFldDestination = new JTextField();
		txtFldDestination.setColumns(10);
		
		textFldDeparture = new JTextField();
		textFldDeparture.setColumns(10);
		
		JLabel lblDeparture = new JLabel("Vertrek:");
		
		JLabel lblDestination = new JLabel("Bestemming:");
		GroupLayout gl_panelTravelPlanner = new GroupLayout(panelTravelPlanner);
		gl_panelTravelPlanner.setHorizontalGroup(
			gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTravelPlanner.createSequentialGroup()
					.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTravelPlanner.createSequentialGroup()
							.addGap(70)
							.addComponent(btnTrain)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBus)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTram))
						.addGroup(gl_panelTravelPlanner.createSequentialGroup()
							.addGap(128)
							.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnShowTrip, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
									.addComponent(lblDeparture)
									.addComponent(textFldDeparture, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDestination))
								.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING, false)
									.addGroup(Alignment.TRAILING, gl_panelTravelPlanner.createSequentialGroup()
										.addComponent(SpnrDateAndTime)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnSearchNow, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
									.addComponent(txtFldDestination, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnMetro, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		gl_panelTravelPlanner.setVerticalGroup(
			gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTravelPlanner.createSequentialGroup()
					.addGap(80)
					.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.LEADING)
						.addComponent(btnTram)
						.addComponent(btnTrain)
						.addComponent(btnMetro, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBus))
					.addGap(49)
					.addComponent(lblDeparture)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textFldDeparture, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDestination)
					.addGap(11)
					.addComponent(txtFldDestination, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(gl_panelTravelPlanner.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearchNow)
						.addComponent(SpnrDateAndTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addComponent(btnShowTrip, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(186, Short.MAX_VALUE))
		);
		panelTravelPlanner.setLayout(gl_panelTravelPlanner);
		
		JPanel panelLocation = new JPanel();
		tabbedPane.addTab("Locatie", null, panelLocation, null);
		
		JPanel panelMap = new JPanel();
		tabbedPane.addTab("Kaart", null, panelMap, null);
		
		JPanel panelDelays = new JPanel();
		tabbedPane.addTab("Vertragingen", null, panelDelays, null);
	}
}