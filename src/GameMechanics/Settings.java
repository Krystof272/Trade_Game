package GameMechanics;

/**
 * Stores and manages game settings such as the current background,
 * currency, and simulation delay. Also handles formatting prices 
 * based on the active currency.
 */
public class Settings {
    private final String[] backgroundList;
    private String imageBackgroundName;
    private final String[] currencyList;
    private String currency;
    private int delay;

    public Settings(String[] backgroundList, String[] currencyList) {
        this.backgroundList = backgroundList;
        this.imageBackgroundName = backgroundList[0];
        this.currencyList = currencyList;
        this.currency = currencyList[0];
        this.delay = 200;
    }

    public String getImageBackgroundName() {
        return imageBackgroundName;
    }

    public void setImageBackgroundName(String imageBackgroundName) {
        this.imageBackgroundName = imageBackgroundName;
        setFirst(imageBackgroundName, backgroundList);
    }

    public String getCurrency() {
        return currency;
    }

    /**
     * Formats a raw integer price into a readable string using the current currency,
     * applying conversion rates, formatting with thousands separators, and 
     * shortening large numbers.
     *
     * @param price The raw integer price to format.
     * @return A formatted price string with the currency symbol appended.
     */
    public String toStringPriceSymbol(int price) {
        int multiplication = 1;
        switch (currency) {
            case "€":
                multiplication = 25;
                break;
            case "$":
                multiplication = 20;
                break;
        }

        String moneyText = String.valueOf(price / multiplication);
        String moneyTemp = "";
        int a = 1;
        for (int i = moneyText.length() - 1; i > -1; i--) {
            moneyTemp += moneyText.charAt(i);
            if (a % 3 == 0) {
                moneyTemp += " ";
            }
            a++;
        }
        moneyText = "";
        int moneyTempLength = moneyTemp.length();
        if (moneyTempLength > 8) {
            for (int i = moneyTempLength - 1; i > 7; i--) {
                moneyText += moneyTemp.charAt(i);
            }
            return moneyText + "M " + currency;
        }
        for (int i = moneyTempLength - 1; i > -1; i--) {
            moneyText += moneyTemp.charAt(i);
        }
        return moneyText + " " + currency;
    }

    /**
     * Sets a new active currency and moves it to the top of the currency list.
     *
     * @param currency The new currency symbol.
     */
    public void setCurrency(String currency) {
        this.currency = currency;
        setFirst(currency, currencyList);
    }

    public String[] getBackgroundList() {
        return backgroundList;
    }

    public String[] getCurrencyList() {
        return currencyList;
    }

    /**
     * Reorders an array so that the specified string is at index 0,
     * swapping it with whatever was previously at index 0.
     *
     * @param first The string to move to the first position.
     * @param pole  The array to be modified.
     */
    public void setFirst(String first, String[] pole) {
        String temp = pole[0];
        pole[0] = first;
        int indexFirst = 0;
        for (int i = 0; i < pole.length; i++) {
            if (pole[i].equals(first)) {
                indexFirst = i;
            }
        }
        pole[indexFirst] = temp;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
