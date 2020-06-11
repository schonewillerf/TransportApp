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

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.awt.event.ActionEvent;

import static adsd.app.ovapp.ovapp.DBConnection.Connection;

import javax.swing.table.DefaultTableModel;

public class OvApp
{
    // Instance Variables
    //
    // Should we order these in some less random way such as below
    // Open to suggestions such as alphabetically, type or user story
    //
    // Objects
    private Connection conn;
    private Map languageMap;
    private Profile profile;
    private Translate Translate;
    private TravelTime travelTime;
    private DynamicMap dynamicMap;
    //
    // String
    private String selectedTransportType;

    //
    // Java Swing Components
    //
    // Frame and Panels
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JPanel panelProfile;
    private JPanel panelLogin;
    private JPanel panelTravelPlanner;
    private JPanel panelLocation;
    protected static JPanel panelMap;
    private JPanel panelDelays;
    private JPanel panelFavorites;
    private JPanel panelSaved;
    private JPanel panelReminder;
    //
    // Profile Components
    private JButton editButton;
    private JEditorPane cardtxt;
    private JEditorPane firstnametxt;
    private JEditorPane lastnametxt;
    private JEditorPane citytxt;
    private JEditorPane streettxt;
    private JEditorPane agetxt;


    //
    // Planner Components
    public JTextField txtFieldDeparture;
    public JTextField txtFieldDestination;
    private JSpinner SpnrDateTime;


    public static String selectedDeparture;
    public static String selectedDestination;


    public static JLabel lblPriceTxt;


    //
    // Login Components
    private JTextField userName;
    private JPasswordField password;
    private JButton btnLogin;
    private JLabel userid;
    //
    // Dont know where
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
    private JTable tableLocation;
    //
    //
    private JLabel lblDeparture;
    private JLabel lblDestination;
    private JButton btnLanguage;
    private JButton btnPlanTrip;
    private JButton btnNow;
    //
    // Labels from panelMap that are dynamic
    private JLabel lblDepartureTimeTxt;
    private JLabel lblArrivalTimeTxt;
    private JLabel lblTotalTimeTxt;
    private JLabel lblDistanceTxt;
    private JLabel lblTrackDepartureTxt;
    private JLabel lblTrackArrivalTxt;
    //public JLabel lblPriceTxt;
    //
    // Labels from panelLocation that are dynamic
    private JLabel lblDynamicDeparture;
    private JLabel lblDynamicDestination;
    private JLabel lblDynamicTransportType;
    //
    private JButton btnLocationChange;
    private JLabel lblLocationDestination;
    private JLabel lblLocationDeparture;
    private JLabel lblLocationDepartureType;
    private JTable tableDelays;
    private JLabel lblDepartureTime;
    public JLabel lblArrivalTime;
    private JLabel lblTrackDeparture;
    private JLabel lblTrackArrival;
    private JLabel lblPrice;
    private JLabel lblDistance_1;
    private JLabel lblTotalTime;
    private JLabel lblSaveTraject;
    private JLabel lblVertragingen;
    private JTable tableSaved;
    
    //Buttons
    private JButton btnBackFavorites;
    private JButton btnBackSaved;
    private JButton btnBackReminder;
    
    //newscreen is a alias for OvApp, here is Ovapp opened as a new main program
    public static void newScreen()
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    // window is new dialog opening for OvApp
                    OvApp window = new OvApp();
                    // show the window
                    window.frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String selectedDeparture() 
    {
        return selectedDeparture;
    }

    public static String selectedDestination() 
    {
        return selectedDestination;
    }

    public static JLabel lblPriceTxt() 
    {
        return lblPriceTxt;
    }

    /**
     * This method controls what panels to display
     *
     * @param panelToDisplay available: "Planner", "Results", "Details", "Saved", "Favourites"
     */
    private void showPanels(String panelToDisplay)
    {
        // Remove all tabs
        tabbedPane.removeAll();

        // Display login or profile tab appropriately
        if (profile.getId() == 0)
        {
            tabbedPane.addTab(Translate.transLang("Login"), null, panelLogin, null);
        }
        else
        {
            tabbedPane.addTab(Translate.transLang("Profiel"), null, panelProfile, null);
        }

        // Add second tab appropriately
        if (panelToDisplay.equals("Planner"))
        {
            tabbedPane.addTab(Translate.transLang("Reisplanner"), null, panelTravelPlanner, null);
            tabbedPane.setSelectedIndex(1);
        }
        else if (panelToDisplay.equals("Results"))
        {
            tabbedPane.addTab(Translate.transLang("Locatie"), null, panelLocation, null);
            tabbedPane.setSelectedIndex(1);
        }
        else if (panelToDisplay.equals("Details"))
        {
            tabbedPane.addTab(Translate.transLang("Details"), null, panelMap, null);
            tabbedPane.setSelectedIndex(1);
        }
        else if (panelToDisplay.equals("Saved"))
        {
            tabbedPane.addTab(Translate.transLang("Opgeslagen"), null, panelSaved, null);
            tabbedPane.setSelectedIndex(1);
        }
        else if (panelToDisplay.equals("Favourites"))
        {
            tabbedPane.addTab(Translate.transLang("Favorieten"), null, panelFavorites, null);
            tabbedPane.setSelectedIndex(1);
        }
        else if (panelToDisplay.equals("Reminders"))
        {
            tabbedPane.addTab(Translate.transLang("Herinneringen"), null , panelReminder, null);
            tabbedPane.setSelectedIndex(1);
        }
        else
        {
            tabbedPane.addTab(Translate.transLang("Reisplanner"), null, panelTravelPlanner, null);
            tabbedPane.setSelectedIndex(0);
        }

        // Always add the delays tab
        tabbedPane.addTab(Translate.transLang("Vertragingen"), null, panelDelays, null);
    }

    // This is where the app is made and starts
    public OvApp()
    {
        languageMap = new HashMap();
        frame = new JFrame();
        frame.setBounds(100, 100, 521, 716);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().
                getImage(OvApp.class.getResource("/resources/train_128.png")));
        frame.getContentPane().setLayout(null);

        //tabbedpane
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 11, 485, 655);
        frame.getContentPane().add(tabbedPane);
        profile = new Profile();
        //Create empty traveltime
        //travelTime = new BusTime("","","","","","",0);
        Translate = new Translate("NL"); //standard language
        selectedTransportType = "Bus";    // Bus is assumed to be default for simplicity
        initialize();
        panelProfile();
        panelLogin();
        panelTravelPlanner();
        panelLocation();
        panelMap();
        panelDelays();
        showPanels("Login");
    }

    // Method for updating the saved TravelTime table
    private void setPanelSaved()
    {
        // Header for table with saved travelTimes
        //String[] header = {"Vervoerstype", "Vertrektijd", "Vertrek", "Aankomsttijd", "Bestemming"};
    	String[] header = 
    		{
    			Translate.transLang("Vervoerstype"),
    			Translate.transLang("Vertrektijd"),
    			Translate.transLang("Vertrek"),
    			Translate.transLang("Aankomsttijd"),
    			Translate.transLang("Bestemming")
    		};
        // Create a tableModel with empty Object[][] as data and header as label
        DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[][]{}, header);

        // Retrieve list of SavedTime objects from Database
        DBHandler dbHandler = new DBHandler();
        List<SavedTime> savedTimes = dbHandler.getSavedTimes(profile.getId());

        // Loop over SavedTime objects and add as row to table
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

        // Add the tableModel to the table
        tableSaved.setModel(defaultTableModel);
    }

    private void panelLogin()
    {
        panelLogin = new JPanel();
        panelLogin.setBackground(Color.WHITE);
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
                    //System.out.println("User doesn't exist");

                    //tabbedPane.remove(panelLogin);
                    panelProfile.revalidate();
                    //addPanels();

                    PreparedStatement DBProfile = conn.prepareStatement("SELECT * FROM profile WHERE emailAdress=? AND password=?");
                    DBProfile.setString(1, String.valueOf(usn));
                    DBProfile.setString(2, String.valueOf(psd));
                    ResultSet rs = DBProfile.executeQuery();

                    while (rs.next())
                    {
                        profile = new Profile(rs.getInt("ID"), rs.getInt("age"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("streetName"), rs.getString("residence"), rs.getString("card"));
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

                // Use showPanels method to display the appropriate tabs
                showPanels("Profile");
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        });

        title.setBounds(200, 90, 200, 20);
        lgn.setBounds(25, 134, 150, 20);
        whtd.setBounds(25, 165, 150, 20);
        userName.setBounds(200, 134, 200, 20);
        password.setBounds(200, 165, 200, 20);
        btnLogin.setBounds(325, 221, 75, 25);
        panelLogin.add(title);
        panelLogin.add(lgn);
        panelLogin.add(whtd);
        panelLogin.add(userName);
        panelLogin.add(password);
        panelLogin.add(btnLogin);
    }

    // Method for panel profile, everything is that is made in profile panel is in this methode.
    private void panelProfile()
    {
        // You can call the methode and get all this code working with just one word of code.
        panelProfile = new JPanel();
        panelProfile.setBackground(Color.WHITE);
        panelProfile.setLayout(null);
        // We need this to store userid
        userid = new JLabel("");
        // Labels
        JLabel lbimage = new JLabel();
        lbimage.setBackground(UIManager.getColor("ToolBar.highlight"));
        // Image added to the label
        lbimage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/rsz_1profile.jpg")));
        lbimage.setBounds(24, 21, 207, 222);
        panelProfile.add(lbimage);
        // Map
        lbMyCard = new JLabel("Kaart");
        lbMyCard.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbMyCard.setBounds(55, 280, 74, 20);
        panelProfile.add(lbMyCard);
        // Name
        lbMyFirstName = new JLabel("Naam" + ":");
        lbMyFirstName.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbMyFirstName.setBounds(55, 310, 74, 20);
        panelProfile.add(lbMyFirstName);
        // LastName
        lbMyLastName = new JLabel("Achternaam" + ":");
        lbMyLastName.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbMyLastName.setBounds(55, 340, 74, 20);
        panelProfile.add(lbMyLastName);
        // Age
        lbMyAge = new JLabel("Leeftijd" + ":");
        lbMyAge.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbMyAge.setBounds(55, 370, 74, 20);
        panelProfile.add(lbMyAge);
        // City
        lbMyCity = new JLabel("Stad" + ":");
        lbMyCity.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbMyCity.setBounds(55, 400, 74, 20);
        panelProfile.add(lbMyCity);
        // Streetadress
        lbMyStreet = new JLabel("Straatnaam" + ":");
        lbMyStreet.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbMyStreet.setBounds(55, 430, 74, 20);
        panelProfile.add(lbMyStreet);

        // Editorpanes profilepanel
        cardtxt = new JEditorPane();
        cardtxt.setFont(new Font("Tahoma", Font.BOLD, 11));
        cardtxt.setText("naam");
        cardtxt.setEnabled(false);
        cardtxt.setBounds(160, 280, 150, 20);
        panelProfile.add(cardtxt);

        firstnametxt = new JEditorPane();
        firstnametxt.setFont(new Font("Tahoma", Font.BOLD, 11));
        firstnametxt.setText("naam");
        firstnametxt.setEnabled(false);
        firstnametxt.setBounds(160, 310, 150, 20);
        panelProfile.add(firstnametxt);

        lastnametxt = new JEditorPane();
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

        lbMyDescription = new JLabel("Mijn beschrijving" + ":");
        lbMyDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbMyDescription.setBounds(55, 467, 118, 20);
        panelProfile.add(lbMyDescription);

        lbFavorites = new JLabel("Favorieten" + ":");
        lbFavorites.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbFavorites.setBounds(301, 43, 74, 20);
        panelProfile.add(lbFavorites);

        lbSaved = new JLabel("Opgeslagen" + ":");
        lbSaved.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbSaved.setBounds(301, 85, 74, 20);
        panelProfile.add(lbSaved);

        lbReminders = new JLabel("Herinneringen" + ":");
        lbReminders.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbReminders.setBounds(301, 138, 94, 20);
        panelProfile.add(lbReminders);

        JLabel lbHome = new JLabel();
        lbHome.setIcon(new ImageIcon(OvApp.class.getResource("/resources/homecolor.png")));
        lbHome.setHorizontalAlignment(SwingConstants.LEFT);
        lbHome.setBounds(24, 430, 24, 27);
        panelProfile.add(lbHome);
        // Textpanes
        JTextPane textPane = new JTextPane();
        textPane.setBackground(new Color(211, 211, 211));
        textPane.setBounds(24, 505, 386, 111);
        panelProfile.add(textPane);
        // Buttons
        editButton = new JButton("Wijzig profiel");// Button for changing profile
        // Action Event for
        // By click the action will be performed with the code  below
        editButton.addActionListener(e ->
        {	// . equals is the same for..
            if (("Wijzig profiel").equals(editButton.getText())) 
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
        });

        editButton.setBounds(55, 240, 120, 20);
        panelProfile.add(editButton);

        JButton btnPencil = new JButton("Write");
        btnPencil.setForeground(Color.WHITE);
        btnPencil.setBackground(Color.WHITE);
        btnPencil.setIcon(new ImageIcon(OvApp.class.getResource("/resources/toolpencil.png")));
        textPane.setEditable(false);
        btnPencil.addActionListener(e -> textPane.setEditable(true));
        btnPencil.setBounds(24, 464, 24, 23);
        panelProfile.add(btnPencil);

        JButton btnCard = new JButton();
        btnCard.setForeground(Color.WHITE);
        btnCard.setBackground(Color.WHITE);
        btnCard.setIcon(new ImageIcon(OvApp.class.getResource("/resources/mycard.png")));
        btnCard.setBounds(24, 278, 29, 23);
        panelProfile.add(btnCard);

        JButton btnFavorites = new JButton();
        btnFavorites.addActionListener(e -> showPanels("Favourites"));

        btnFavorites.setForeground(Color.WHITE);
        btnFavorites.setBackground(Color.WHITE);
        btnFavorites.setIcon(new ImageIcon(OvApp.class.getResource("/resources/favorites.png")));
        btnFavorites.setBounds(262, 43, 29, 23);
        panelProfile.add(btnFavorites);

        JButton btnSaved = new JButton();
        btnSaved.addActionListener(e ->
        {
            setPanelSaved();
            showPanels("Saved");
        });

        btnSaved.setForeground(Color.WHITE);
        btnSaved.setBackground(Color.WHITE);
        btnSaved.setIcon(new ImageIcon(OvApp.class.getResource("/resources/saved.png")));
        btnSaved.setBounds(262, 85, 29, 23);
        panelProfile.add(btnSaved);

        JButton btnReminder = new JButton();
        btnReminder.addActionListener(e -> showPanels("Reminders"));

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
                if (Translate.Language.equals("EN")) // Display Language Dutch
                {
                    Translate.Language = "NL";
                    System.out.println(Translate.Language);
                    showPanels("Profile");
                    btnLanguage.setIcon(new ImageIcon(OvApp.class.getResource("/resources/countryEnglish.png")));
                    btnLanguage.setText(Translate.transLang(("EN")));
                }
                else if (Translate.Language.equals("NL")) // Display Language English
                {
                    Translate.Language = "EN";
                    System.out.println(Translate.Language);
                    showPanels("Profile");
                    btnLanguage.setIcon
                    (new ImageIcon(OvApp.class.getResource("/resources/countryNetherlands.png")));
                    btnLanguage.setText(Translate.transLang(("EN")));
                }
                // Back button
                btnBackReminder.setText(Translate.transLang("Terug"));
                btnBackFavorites.setText(Translate.transLang("Terug"));
                btnBackSaved.setText(Translate.transLang("Terug"));
                // Profile
                lbMyFirstName.setText(Translate.transLang("Naam") + ":");
                lbFavorites.setText(Translate.transLang("Favorieten") + ":");
                lbSaved.setText(Translate.transLang("Opgeslagen") + ":");
                lbReminders.setText(Translate.transLang("Herinneringen:") + ":");
                editButton.setText(Translate.transLang("Wijzig profiel"));
                lbMyCard.setText(Translate.transLang("Kaart") + ":");
                lbMyLastName.setText(Translate.transLang("Achternaam") + ":");
                lbMyAge.setText(Translate.transLang("Leeftijd") + ":");
                lbMyCity.setText(Translate.transLang("Stad") + ":");
                lbMyStreet.setText(Translate.transLang("Straatnaam") + ":");
                lbMyDescription.setText(Translate.transLang("Mijn beschrijving") + ":");
                // TravelPlanner
                lblDestination.setText(Translate.transLang("Aankomst") + ":");
                lblDeparture.setText(Translate.transLang("Vertrek") + ":");
                btnPlanTrip.setText(Translate.transLang("Zoeken"));
                btnNow.setText(Translate.transLang("Nu"));
                // Location
                btnLocationChange.setText(Translate.transLang("Wijzig reis"));
                lblLocationDestination.setText(Translate.transLang("Aankomst") + ":");
                lblLocationDeparture.setText(Translate.transLang("Vertrek") + ":");
                lblLocationDepartureType.setText(Translate.transLang("Vervoerstype") + ":");
                // Map
                lblArrivalTime.setText(Translate.transLang("Aankomsttijd") + ":");
                lblTotalTime.setText(Translate.transLang("Totale tijd") + ":");
                lblSaveTraject.setText(Translate.transLang("Opslaan") + ":");
                lblTrackArrival.setText(Translate.transLang("Spoor") + ":");
                lblTrackDeparture.setText(Translate.transLang("Spoor") + ":");
                lblPrice.setText(Translate.transLang("Prijs") + ":");
                lblDistance_1.setText(Translate.transLang("Afstand") + ":");
                lblDepartureTime.setText(Translate.transLang("Vertrektijd") + ":");
                tableMap.setModel(new DefaultTableModel(
                        // Only way to translate the labels here.
                        new Object[][]
                                {
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
                                        {null, null},
                                },
                        
                        new String[]
                                {
                                        // Translating the label words
                                        Translate.transLang("Tijd"), Translate.transLang("Stop")
                                }
                ));

                
                lblVertragingen.setText(Translate.transLang("Vertragingen") + ":");
                tableDelays.setModel(new DefaultTableModel(
                        new Object[][]
                                {
                                        {"Amersfoort", "Groningen", "00:15"},
                                        {"Heerenveen", "Groningen", "00:15"},
                                        {"Delft", "Groningen", "00:15"},
                                },
                        new String[]
                                {		// Translating the label words
                                        Translate.transLang("Vertrek station"), Translate.transLang("Aankomst station"), Translate.transLang("Vertraging")    
                                }
                ));
            }
        });
        
        // Set the line out for the buttondesign
        btnLanguage.setBounds(389, 11, 74, 23);                                       
        panelProfile.add(btnLanguage);
        // Buttons "back"
        btnBackFavorites = new JButton("Terug");
        btnBackFavorites.addActionListener(e -> showPanels("Profile"));
        btnBackFavorites.setBounds(364, 28, 89, 23);
        panelFavorites.add(btnBackFavorites);
        btnBackSaved = new JButton("Terug");
        btnBackSaved.addActionListener(e -> showPanels("Profile"));

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

        btnDetailsSaved.addActionListener(e ->
        {
            // Get the selected TravelTime from tableLocation
            int selectedRow = tableSaved.getSelectedRow();

            // Will run if there is a selected row
            if (selectedRow >= 0)
            {
                // Show panel with TravelTime details
                showPanels("Details");

                // Not sure if we need a local or global variable for this
                // String transportType = String.valueOf(tableSaved.getModel().getValueAt(selectedSavedRow, 0));
                selectedTransportType = String.valueOf(tableSaved.getModel().getValueAt(selectedRow, 0));
                String departureTime = String.valueOf(tableSaved.getModel().getValueAt(selectedRow, 1));
                String departure = String.valueOf(tableSaved.getModel().getValueAt(selectedRow, 2));
                String arrivalTime = String.valueOf(tableSaved.getModel().getValueAt(selectedRow, 3));
                String destination = String.valueOf(tableSaved.getModel().getValueAt(selectedRow, 4));
                System.out.println(destination);

                // Retrieve TravelTime from DB with corresponding TravelDataModel
                if (selectedTransportType.equals("Bus"))
                {
                    // Using the BusDataModel
                    BusDataModel busDataModel = new BusDataModel();
                    // Getting and storing TravelTime from model
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

                // Set labels to the selected TravelTime
                lblDepartureTimeTxt.setText(travelTime.getDepartureTime());
                lblArrivalTimeTxt.setText(travelTime.getArrivalTime());
                lblTotalTimeTxt.setText(travelTime.getDuration());
                lblDistanceTxt.setText(travelTime.getDistance());
                lblTrackDepartureTxt.setText(travelTime.getPlatform());
                lblTrackArrivalTxt.setText(travelTime.getDestination());
            }
        });

        btnBackReminder = new JButton("Terug");
        btnBackReminder.addActionListener(e -> showPanels("Profile"));

        btnBackReminder.setBounds(316, 30, 89, 23);
        panelReminder.add(btnBackReminder);
    }

    private void panelTravelPlanner()
    {
        // Creates a new panel
        panelTravelPlanner = new JPanel();

        // Buttons for different search options
        JButton btnBus = new JButton("");
        btnBus.setIcon(new ImageIcon(OvApp.class.getResource("/resources/bus_50.png")));
        JButton btnTrain = new JButton("");
        btnTrain.setIcon(new ImageIcon(OvApp.class.getResource("/resources/train_50.png")));
        JButton btnMetro = new JButton("");
        btnMetro.setIcon(new ImageIcon(OvApp.class.getResource("/resources/Metro_50.png")));
        JButton btnTram = new JButton("");
        btnTram.setIcon(new ImageIcon(OvApp.class.getResource("/resources/Tram_50.png")));

        lblDeparture = new JLabel("Vertrek" + ":");
        lblDestination = new JLabel("Aankomst:");
        btnPlanTrip = new JButton("Zoeken");

        // ActionListener for search button
        btnPlanTrip.addActionListener(actionEvent ->
        {
        	//Switch to resultsPanel
        	showPanels("Results");

            // Retrieve search criteria
            selectedDeparture = txtFieldDeparture.getText();
            selectedDestination = txtFieldDestination.getText();

            // Set labels to display used search criteria
            lblDynamicDeparture.setText(txtFieldDeparture.getText());
            lblDynamicDestination.setText(txtFieldDestination.getText());
            lblDynamicTransportType.setText(selectedTransportType);

            // Create a header for the table
            String[] header = {Translate.transLang("Vertrektijd"), "Spoor/Halte", Translate.transLang("Bestemming")};
            header[1] = selectedTransportType.equals("Bus") ? Translate.transLang("Halte") : Translate.transLang("Spoor");

            // Create a new TableModel and apply it to the Table
            DefaultTableModel dtm = new DefaultTableModel(new Object[][]{}, header);
            tableLocation.setModel(dtm);

            // Create a list for storing TravelTime objects
            List<TravelTime> travelTimes = new ArrayList<>();

            // TravelTime objects for the appropriate travel type are retrieved from database
            if (selectedTransportType.equals("Bus"))
            {
                // Create the correct dataModel for the travel type
                BusDataModel dataModel = new BusDataModel();
                // Use the getArrivalTimes() method to get all arrival time in the travelTimes variable
                travelTimes = dataModel.getArrivalTimes();
            }
            else if (selectedTransportType.equals("Train"))
            {
                TrainDataModel dataModel = new TrainDataModel();
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
        });

        //an option to input date and time
        SpnrDateTime = new JSpinner();
        SpnrDateTime.setModel(new SpinnerDateModel(new Date(1589234400000L), null, null, Calendar.DAY_OF_YEAR));
        //button that will show the current time
        btnNow = new JButton("Nu");

        txtFieldDeparture = new JTextField("");
        txtFieldDeparture.setColumns(10);

        //automatically groups the buttons at the same height.
        txtFieldDestination = new JTextField();
        txtFieldDestination.setColumns(10);

        // Action Listeners for Transport Type Buttons
        btnTrain.addActionListener(actionEvent -> selectedTransportType = "Train");
        btnBus.addActionListener(actionEvent -> selectedTransportType = "Bus");
        btnMetro.addActionListener(actionEvent -> selectedTransportType = "Metro");
        btnTram.addActionListener(actionEvent -> selectedTransportType = "Tram");

        // Auto Generate code from Window Builder
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

    // Panel for displaying TravelTime list
    private void panelLocation()
    {
        // Create the panel and add it as tab
        panelLocation = new JPanel();
        //tabbedPane.addTab("Locatie", null, panelLocation, null);

        // Create a return button to search panel
        btnLocationChange = new JButton("Wijzig reis");
        btnLocationChange.addActionListener(actionEvent -> showPanels("Planner"));

        // Create a button for opening detail view
        JButton btnDetails = new JButton("Details");

        // Create labels for departuretime, platform and destination
        lblLocationDeparture = new JLabel("Vertrek" + ":");
        lblLocationDestination = new JLabel("Aankomst" + ":");
        lblLocationDepartureType = new JLabel("Vervoerstype" + ":");

        // Create dynamic labels for departuretime, platform and destination from search panel
        lblDynamicDeparture = new JLabel();
        lblDynamicDestination = new JLabel();
        lblDynamicTransportType = new JLabel();

        tableLocation = new JTable();
        JScrollPane scpLocation = new JScrollPane();
        scpLocation.setViewportView(tableLocation);

        // Add ActionEvent to btnDetails
        // Set labels in panelMap to selected travelTime
        // Used to be set within an EventListener for activating the panelMap but this way is less complicated
        btnDetails.addActionListener(actionEvent ->
                {
                    // Get the selected TravelTime from tableLocation
                    int selectedRow = tableLocation.getSelectedRow();

                    // If there is a selected row it will be stored in the private variable travelTime
                    if (selectedRow >= 0)
                    {
                    	// Switch to the panelMap
                        showPanels("Details");
                    	
                        // Retrieve selected info
                        String departureTime = String.valueOf(tableLocation.getModel().getValueAt(selectedRow, 0));
                        String platform = String.valueOf(tableLocation.getModel().getValueAt(selectedRow, 1));
                        String destination = String.valueOf(tableLocation.getModel().getValueAt(selectedRow, 2));

                        // Load from the correct data model
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
                        
                        // Set labels to the selected TravelTime
                        lblDepartureTimeTxt.setText(travelTime.getDepartureTime());
                        lblArrivalTimeTxt.setText(travelTime.getArrivalTime());
                        lblTotalTimeTxt.setText(travelTime.getDuration());
                        lblDistanceTxt.setText(travelTime.getDistance());
                        lblTrackDepartureTxt.setText(travelTime.getPlatform()+ " "+ travelTime.getStationName());
                        lblTrackArrivalTxt.setText(travelTime.getDestination());
                        

                        // Refresh map here  
                       
                    }
                    
                    
                }
        );

        // From here on only auto generated styling and adding of components
        GroupLayout gl_panelLocation = new GroupLayout(panelLocation);
        gl_panelLocation.setHorizontalGroup(
                gl_panelLocation.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panelLocation.createSequentialGroup()
                                .addContainerGap(22, Short.MAX_VALUE)
                                .addGroup(gl_panelLocation.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(btnDetails)
                                        .addGroup(gl_panelLocation.createParallelGroup(Alignment.LEADING)
                                                .addComponent(scpLocation, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(gl_panelLocation.createSequentialGroup()
                                                        .addGroup(gl_panelLocation.createParallelGroup(Alignment.LEADING, false)
                                                                .addComponent(lblLocationDepartureType, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btnLocationChange)
                                                                .addComponent(lblLocationDeparture, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGap(18)
                                                        .addGroup(gl_panelLocation.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(lblDynamicTransportType, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblDynamicDestination, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblDynamicDeparture)))
                                                .addComponent(lblLocationDestination, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        gl_panelLocation.setVerticalGroup(
                gl_panelLocation.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panelLocation.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnLocationChange)
                                .addGap(18)
                                .addGroup(gl_panelLocation.createParallelGroup(Alignment.LEADING)
                                        .addGroup(Alignment.TRAILING, gl_panelLocation.createSequentialGroup()
                                                .addComponent(lblDynamicDeparture)
                                                .addGap(32))
                                        .addGroup(gl_panelLocation.createSequentialGroup()
                                                .addComponent(lblLocationDeparture)
                                                .addGap(18)))
                                .addGroup(gl_panelLocation.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblDynamicDestination)
                                        .addComponent(lblLocationDestination))
                                .addGap(18)
                                .addGroup(gl_panelLocation.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblLocationDepartureType)
                                        .addComponent(lblDynamicTransportType))
                                .addGap(18)
                                .addComponent(scpLocation, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                                .addGap(18)
                                .addComponent(btnDetails)
                                .addContainerGap())
        );
        panelLocation.setLayout(gl_panelLocation);
    }

    /**
     * Panel for displaying TravelTime details
     */
    private void panelMap()
    {
        DynamicMap.set_Location_Tram();
    	
        panelMap = new JPanel();// Make a new panel named panelMap
        panelMap.setBackground(Color.WHITE);// Set the background to the color white
        // TabbedPane.addTab("Kaart", null, panelMap, null);
        panelMap.setLayout(null);

        // Labels
        lblDepartureTime = new JLabel("Vertrek" + ":");
        lblDepartureTime.setBounds(44, 39, 84, 25);
        lblDepartureTime.setFont(new Font("Tahoma", Font.BOLD, 11));
        panelMap.add(lblDepartureTime);

        lblArrivalTime = new JLabel("Aankomsttijd:");// make new label named lblArrivalTime
        lblArrivalTime.setBounds(44, 75, 84, 25);//Set the outlining design for the button
        // Set the text in a new design, called "font" in programming
        lblArrivalTime.setFont(new Font("Tahoma", Font.BOLD, 11));
        panelMap.add(lblArrivalTime);

        lblTotalTime = new JLabel("Totale tijd:");
        lblTotalTime.setBounds(44, 135, 84, 25);
        lblTotalTime.setFont(new Font("Tahoma", Font.BOLD, 11));
        panelMap.add(lblTotalTime);

        lblSaveTraject = new JLabel("Opslaan");
        lblSaveTraject.setBounds(44, 169, 84, 25);
        lblSaveTraject.setFont(new Font("Tahoma", Font.BOLD, 11));
        panelMap.add(lblSaveTraject);

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

        lblDepartureTimeTxt = new JLabel("<dynamic>");
        lblDepartureTimeTxt.setBounds(138, 39, 70, 19);
        panelMap.add(lblDepartureTimeTxt);

        lblArrivalTimeTxt = new JLabel("<dynamic>");
        lblArrivalTimeTxt.setBounds(138, 75, 70, 19);
        panelMap.add(lblArrivalTimeTxt);

        lblTotalTimeTxt = new JLabel("<dynamic>");
        lblTotalTimeTxt.setBounds(138, 135, 70, 25);
        panelMap.add(lblTotalTimeTxt);

        lblTrackDepartureTxt = new JLabel("<dynamic>");
        lblTrackDepartureTxt.setBounds(348, 40, 132, 23);
        panelMap.add(lblTrackDepartureTxt);

        lblTrackArrivalTxt = new JLabel("<dynamic>");
        lblTrackArrivalTxt.setBounds(348, 75, 132, 23);
        panelMap.add(lblTrackArrivalTxt);

        lblPriceTxt = new JLabel("<dynamic>");
        lblPriceTxt.setBounds(348, 135, 70, 23);
        panelMap.add(lblPriceTxt);

        // Buttons
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

        JButton btnSaveTraject = new JButton("");
        btnSaveTraject.setBounds(10, 169, 25, 23);
        btnSaveTraject.setBackground(Color.WHITE);
        btnSaveTraject.setIcon(new ImageIcon(OvApp.class.getResource("/resources/saved.png")));
        panelMap.add(btnSaveTraject);

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

        // Srollpane
        JScrollPane scrollPaneMap = new JScrollPane();
        scrollPaneMap.setBounds(296, 233, 184, 276);
        panelMap.add(scrollPaneMap);

        // Table
        tableMap = new JTable();                                    //make a new table named tableMap
        tableMap.setModel(new DefaultTableModel(                    //set a model for tableMap
                new Object[][]{
                        {null, null},                                        // all empty"null" for show in gui.
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
                        {                                            //naming for the colums/rows
                                "Tijd", "Stop"
                        }
        ));

        scrollPaneMap.setViewportView(tableMap);// set the tableMap in scrollpane, this will make the table scrollable

        JButton btnDistance = new JButton("");
        btnDistance.setBackground(Color.WHITE);
        btnDistance.setBounds(247, 167, 25, 23);
        btnDistance.setIcon(new ImageIcon(OvApp.class.getResource("/resources/transfer.png")));
        panelMap.add(btnDistance);

        lblDistance_1 = new JLabel("Afstand:");
        lblDistance_1.setFont(new Font("Dialog", Font.BOLD, 11));
        lblDistance_1.setBounds(282, 167, 84, 25);
        panelMap.add(lblDistance_1);

        lblDistanceTxt = new JLabel("");
        lblDistanceTxt.setBounds(348, 170, 70, 23);
        panelMap.add(lblDistanceTxt);

        

        // Add an ActionListener to save Traject to DB
        btnSaveTraject.addActionListener(actionEvent ->
        {
            DBHandler dbHandler = new DBHandler();

            // Save the travelTime to DB
            dbHandler.saveTime(
                    travelTime.getDepartureTime(),
                    travelTime.getStationName(),
                    travelTime.getArrivalTime(),
                    travelTime.getDestination(),
                    selectedTransportType,
                    profile.getId());
        });

        // Quick test for returning to results panel
        // TODO
        // Add a real return button in this panel
        // Replace button name in line below
        btnLocationArrival.addActionListener(e -> showPanels("Results"));
    }

    /**
     * Create tab for displaying hard-coded delays
     */
    private void panelDelays()
    {
        // Create the panel
        panelDelays = new JPanel();

        // Add a label above table
        lblVertragingen = new JLabel("Vertragingen" + ":");

        // Create a JTable with hard-coded data and headers
        tableDelays = new JTable(
                // Hard coded data
                new Object[][]
                        {
                                {"Amersfoort", "Groningen", "00:15"},
                                {"Heerenveen", "Groningen", "00:15"},
                                {"Delft", "Groningen", "00:15"},
                        },
                // Hard coded headers
                new String[]
                        {
                                "Vertrek station", "Aankomst station", "Vertraging"
                        }
        );

        // Add table to a JScrollPane in order to display headers
        JScrollPane scrollPane = new JScrollPane(tableDelays);

        // Autogenerated code from Window Builder from here on
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
    }

}



