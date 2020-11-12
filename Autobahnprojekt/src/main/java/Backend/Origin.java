package Backend;

import java.util.ArrayList;

/**
 * 
 * class to:
 * 		~ get the origin easily 
 * 		 
 * 
 * @author luisa.thiel Mail: luisa.thiel@cideon.com
 * Company: Cideon Software & Services GmbH & Co. KG.
 * created at 04.11.2020
 */

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
    /**
     * creates a Array of all Origins
     * 
     * @return Array of all Origins
     */
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

    /**
     * check which country is the given string otherwise it returns D
     * 
     * @param origin String of a origin
     * @return correct formatted origin
     */
    public Origin changeToCorrectOrigin(String origin) {
    	ArrayList<Origin> allOrigins = new ArrayList<>();
    	for(Origin o : allOrigins) {
    		if(o.toString().equals(origin)) {
    			return o;
    		}
    	}
    	return Origin.D;
    }
}