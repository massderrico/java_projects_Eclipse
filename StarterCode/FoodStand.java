
import java.util.List;

public class FoodStand extends FoodPlace {

    public FoodStand(String name, double fixedCosts, WorkingOwner owner) {
        /* TODO: Add your code here */
        /* TODO: Also edit the super call */
        super(null,-1,null);
    }

    @Override
    public String toString() {
        return "Name of FoodStand: " + this.getName() +
                "\n" + "Owner: " + this.getOwner();
    }

    @Override
    public void workShift(int hours) {
        // no salaried workers so do nothing
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
