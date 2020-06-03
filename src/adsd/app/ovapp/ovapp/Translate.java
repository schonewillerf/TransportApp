package adsd.app.ovapp.ovapp;

import java.util.HashMap;
import java.util.Map;

public class Translate 
{
    private Map languageMap;
    protected String Language;

    public Translate(String language) 
    {
        Language = language;
        languageMap = new HashMap();
        FillLanguageMap();
    }
    //puts a new value into the language list.
    private void FillLanguageMap() 
    {
    	//Profile
        languageMap.put("Wijzig profiel", "Edit profile");
        languageMap.put("Favorieten:", "Favorites:");
        languageMap.put("Opgeslagen:", "Saved:");
        languageMap.put("Herinneringen:", "Reminders:");
        languageMap.put("Mijn beschrijving:", "My description:");
        languageMap.put("Kaart:", "Map:");
        languageMap.put("Naam:", "Name:");
        languageMap.put("Achternaam:", "Surname:");
        languageMap.put("Leeftijd:", "Age:");
        languageMap.put("Stad:", "City:");
        languageMap.put("Straatnaam:", "Streetname:");
        languageMap.put("EN", "NL");
        //TravelPlanner
        languageMap.put("Aankomst:", "Destination:");
        languageMap.put("Vertrek:", "Departure:");
        languageMap.put("Nu", "Now");
        languageMap.put("Zoeken", "Search");
        //Location
        languageMap.put("Bestemming:", "Destination:");
        languageMap.put("Vervoerstype:", "Transport type:");
        languageMap.put("Wijzig reis", "Change trip");
        //Map
        languageMap.put("Vertrektijd:", "Departure time:");
        languageMap.put("Aankomsttijd:", "Arrival time:");
        languageMap.put("Totale tijd:", "Overall time:");
        languageMap.put("Overdracht:", "Transfer:");
        languageMap.put("Spoor:", "Platform:");
        languageMap.put("Prijs:", "Price:");
        languageMap.put("Afstand:", "Distance:");
        //Delays
        languageMap.put("Vertragingen:", "Delays");
    }
    
    //gets the translated word from languagemap and returns the word.
    public String TransLang(String word) 
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