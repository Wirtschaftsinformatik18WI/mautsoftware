package Backend;

/**
 * 
 * class to:
 * 		~ create a Statistic View to build up a  nice chart
 * 		 
 * 
 * @author luisa.thiel Mail: luisa.thiel@cideon.com
 * Company: Cideon Software & Services GmbH & Co. KG.
 * created at 07.11.2020
 */

public class StatisticView {
	
	Origin origin;
	long amount;
	
	public StatisticView(Origin origin, long amount) {
		this.origin = origin;
		this.amount = amount;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
	
}
