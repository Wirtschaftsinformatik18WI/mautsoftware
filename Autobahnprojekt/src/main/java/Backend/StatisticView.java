package Backend;

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
