package GameMechanics;

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

    public int getMultiplication() {
        switch (currency) {
            case "€":
                return 25;
            case "$":
                return 20;
            case "Kc":
                return 1;
            default:
                return 0;
        }
    }

    public String getCurrencyReal(int value) {
        switch (currency) {
            case "€":
                return value / 25 + " €";
            case "$":
                return value / 20 + " $";
            case "Kc":
                return value + " Kc";
            default:
                return "Symbol not defined";
        }
    }

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
