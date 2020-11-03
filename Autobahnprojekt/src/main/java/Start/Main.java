package Start;
import java.util.UUID;

import database.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 DatabaseConnection database = new DatabaseConnection ();
 database.addFeeTest("basic", UUID.randomUUID() , 0.05);
	}

}
