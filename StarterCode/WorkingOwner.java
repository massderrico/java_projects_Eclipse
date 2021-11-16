
public class WorkingOwner extends Owner {

    private int targetTipPct;

    public WorkingOwner(String name, int targetTipPct) {
        this.targetTipPct = targetTipPct;
        super(name);
    }

    public int getTargetTipPct() {
        return targetTipPct;
    }
}
