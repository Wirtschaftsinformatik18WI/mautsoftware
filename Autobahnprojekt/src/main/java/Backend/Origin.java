package Backend;

import java.util.ArrayList;

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
    
    public ArrayList<Origin> getAllOrigins(){
    	ArrayList<Origin> allOrigins = new ArrayList<>();
    	allOrigins.add(D);
    	allOrigins.add(F);
    	allOrigins.add(PL);
    	allOrigins.add(CZ);
    	allOrigins.add(GB);
    	allOrigins.add(I);
    	allOrigins.add(LT);
    	allOrigins.add(L);
    	allOrigins.add(CH);
    	
    	return allOrigins;
    }

}

// wenn das Objekt als String ausgegeben werden soll einfach  Origin.D.toString() anwählen o/