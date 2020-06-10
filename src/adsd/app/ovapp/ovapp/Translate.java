package adsd.app.ovapp.ovapp;

import java.util.HashMap;
import java.util.Map;

import javax.swing.table.JTableHeader;

public class Translate 
{
    private Map languageMap;
    protected String Language;

    public Translate(String language) 
    {
        Language = language;
        languageMap = new HashMap();
        fillLanguageMap();
    }
    // Puts a new value into the language list.
    private void fillLanguageMap() 
    {
    	// Profile
        languageMap.put("Wijzig profiel", "Edit profile");
        languageMap.put("Favorieten", "Favorites");
        languageMap.put("Opgeslagen", "Saved");
        languageMap.put("Herinneringen", "Reminders");
        languageMap.put("Mijn beschrijving", "My description");
        languageMap.put("Kaart", "Map");
        languageMap.put("Naam", "Name");
        languageMap.put("Achternaam", "Surname");
        languageMap.put("Leeftijd", "Age");
        languageMap.put("Stad", "City");
        languageMap.put("Straatnaam", "Streetname");
        languageMap.put("EN", "NL");
        // TravelPlanner
        languageMap.put("Aankomst", "Destination");
        languageMap.put("Vertrek", "Departure");
        languageMap.put("Nu", "Now");
        languageMap.put("Zoeken", "Search");
        // Location
        languageMap.put("Bestemming", "Destination");
        languageMap.put("Vervoerstype", "Transport type");
        languageMap.put("Wijzig reis", "Change trip");
        languageMap.put("Vertrektijd", "Departure time");
        languageMap.put("Halte", "Bus stop");
        languageMap.put("Spoor", "Platform");
        // Map
        languageMap.put("Vertrektijd", "Departure time");
        languageMap.put("Aankomsttijd", "Arrival time");
        languageMap.put("Totale tijd", "Overall time");
        languageMap.put("Opslaan", "Save");
        languageMap.put("Spoor", "Platform");
        languageMap.put("Prijs", "Price");
        languageMap.put("Afstand", "Distance");
        languageMap.put("Tijd", "Time");
        languageMap.put("Stop", "Stop");
        // Delays
        languageMap.put("Vertragingen", "Delays");
        // TableDelay
        languageMap.put("Aankomst station", "Arrival station");
        languageMap.put("Vertraging", "Delay");
        languageMap.put("Vertrek station", "Departure station");
        // Tabs
        languageMap.put("Login", "Login");
        languageMap.put("Profiel", "Profile");
        languageMap.put("Reisplanner", "Travel Planner");
        languageMap.put("Details", "Details");
        languageMap.put("Opgeslagen", "Saved");
        languageMap.put("Favorieten", "Favourites");
        languageMap.put("Herinneringen", "Reminders");
        languageMap.put("Locatie", "Location");
        languageMap.put("Vertragingen", "Delays");
        languageMap.put("Terug", "Back");
    }
    
    // Gets the translated word from fillLanguagemap and returns the word.
    public String transLang(String word) 
    {
        if (Language == "EN")
        {
            if ((String)languageMap.get(word) != null)
            {
                return (String)languageMap.get(word);
            }
        }
        return word;
    }
  
	}