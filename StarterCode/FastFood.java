
import java.util.ArrayList;
import java.util.List;

public class FastFood extends FoodPlace {

    private List<Staff> staff = new ArrayList<>();

    public FastFood(String name, double fixedCosts, Owner owner, List<Staff> staff) {
        /* TODO: Add your code here */
    	super(name,fixedCosts,owner);
    	this.staff = staff.clone();
    	
        /* TODO: Also edit the super call */
        
    }

    public List<Staff> getStaff() {
        return staff;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name of FastFood: " + this.getName() +
                "\n" + "Owner: " + this.getOwner());
        int index = 1;
        for (Staff staff: staff) {
            builder.append("\n" + "Staff " + index++ + " : " + staff );
        }
        return builder.toString();
    }

    @Override
    public void workShift(int hours) {
        /* TODO: Add your code here */
    	
    }

    @Override
    public List<IncomeTaxPayer> getIncomeTaxPayers() {
    	/* TODO: Add your code here */
    	/* TODO: Also remove return statement below*/
    	List<IncomeTaxPayer> incomeTaxPayer = new ArrayList<>();
    	for (Staff i:this.staff) {
    		incomeTaxPayer.add(i);
    	}
    	incomeTaxPayer.add(this.getOwner());
        return incomeTaxPayer.clone();
    }

    @Override
    public void distributeIncomeAndSalesTax(Check check) {
        /* TODO: Add your code here */
    	
    }

    @Override
    public double getTipPercentage() {
        return 0;
    }
}
