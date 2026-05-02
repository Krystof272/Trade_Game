package GameMechanics;

public class Settings {
    private final String[] backgroundList;
    private String imageBackgroundName;
    private final String[] currencyList;
    private String currency;

    public Settings(String[] backgroundList, String[] currencyList) {
        this.backgroundList = backgroundList;
        this.imageBackgroundName = backgroundList[0];
        this.currencyList = currencyList;
        this.currency = currencyList[0];
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
}
