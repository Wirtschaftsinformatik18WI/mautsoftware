package Backend;

public enum Origin {
    D("D"), F("F"), PL("PL"), CZ("CZ"), GB("GB"), I("I"), LT("LT"), L("L"), CH("CH");

    private final String text;

    /**
     * @param text
     */
    Origin(final String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}

// wenn das Objekt als String ausgegeben werden soll einfach  Origin.D.toString() anwählen o/