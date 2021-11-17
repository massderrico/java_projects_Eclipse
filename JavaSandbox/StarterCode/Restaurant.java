
import java.util.List;

public class Restaurant extends FoodPlace {

	private Staff cook;
	private Server server;

	public Restaurant(String name, double fixedCosts, Owner owner, Staff cook, Server server) {
		/* TODO: Add your code here */
		/* TODO: Also edit the super call */
		super(null,-1,null);
	}

	public Staff getCook() {
		return cook;
	}

	public Server getServer() {
		return server;
	}

	@Override
	public String toString() {
		return "Name of restaurant: " + this.getName() +
				"\n" + "Owner: " + this.getOwner() +
				"\n" + "Cook: " + cook +
				"\n" + "Server: " + server;
	}

	@Override
	public void workShift(int hours) {
		/* TODO: Add your code here */
	}

	@Override
	public List<IncomeTaxPayer> getIncomeTaxPayers() {
		/* TODO: Add your code here */
		/* TODO: Also remove return statement below*/
		return null;
	}

	@Override
	public void distributeIncomeAndSalesTax(Check check) {
		/* TODO: Add your code here */
	}

	@Override
	public double getTipPercentage() {
		/* TODO: Add your code here */
		/* TODO: Also remove return statement below*/
		return -1;
	}

}
