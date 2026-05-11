package Others;

import GameMechanics.Stock;
import Run.Controler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Represents the game data loaded from a JSON file.
 * This class serves as a data container for all static game content, such as items, characters, locations, and quests.
 *
 * @author Misa
 */
public class GameData {
    private ArrayList<Stock> stocks;
    private String[] backgroundList;
    private String[] currencyList;
    private ArrayList<Integer> date;

    /**
     * Loads game data from a JSON file.
     *
     * @param resourcePath path to the resource file
     * @return a Game.GameData object filled with the loaded data
     */
    public static GameData loadGameDataFromResources(String resourcePath) {
        //Vytvoření objektu pro práci s JSON souborem
        ObjectMapper mapper = new ObjectMapper();

        //Načtení souboru gamedata.json, musí být ve složce res/resources, ta musí být označena jako resource složka projektu
        try (InputStream is = Controler.class.getResourceAsStream(resourcePath)) {

            //Zde ověřujeme, zdali soubor existuje
            if (is == null) {
                throw new IllegalStateException("Nenalezen resource: " + resourcePath +
                        " (zkontrolujte, že soubor je v src/main/resources).");
            }

            //Přečte celý JSON a vytvoří instanci Game.GameData, naplní vlastnosti podle názvů klíčů v JSONU, vrátí se hotová třída Game.GameData
            return mapper.readValue(is, GameData.class);
        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage());
        }
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public ArrayList<Integer> getDate() {
        return date;
    }

    public String[] getBackgroundList() {
        return backgroundList;
    }

    public String[] getCurrencyList() {
        return currencyList;
    }
}