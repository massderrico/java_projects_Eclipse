package assignment1;




class Basket_JamTax implements Runnable{
    @Override
    public void run() {
        Basket basket = new Basket();
        basket.add(new Jam("jam", 2, 475));
        int result = basket.getTotalTax();
        int expected = 142;
        if (result != expected){
            throw new AssertionError("getTotalTax() returned " + result
            + " but expected " + expected);
        }
        System.out.println("Basket tax test passed.");
    }
}


class Basket_NumOfProduct implements Runnable{
    @Override
    public void run() {
        Basket myBasket = new Basket();
        myBasket.add(new Egg("organic Eggs",5,380));
        int num = myBasket.getNumOfProducts();
        if (num != 1){
            throw new AssertionError("getNumOfProducts() returned " + num
            + " but expected " + 1);
        }
        System.out.println("Basket number of product test passed.");
    }
}


class Basket_Remove implements Runnable {
    @Override
    public void run() {
        Basket myBasket = new Basket();
        myBasket.add(new Egg("organic Eggs",5,380));
        myBasket.add(new Fruit("Blue Berry",1.5,380));
        myBasket.add(new Fruit("Green Berry",1.5,380));
        myBasket.add(new Fruit("red Berry",1.5,380));
        myBasket.add(new Fruit("Random fruit",1.1,340));
        Boolean a = myBasket.remove(new Fruit("Random fruit",1.1,340));
        Boolean b = myBasket.remove(new Egg("Green Berry",3,350));
        if(!a || b){
            throw new AssertionError("Expected: " + "a=true & b=false"
                    + " but obtained: " + "a=" + a + " & b=" + b);
        }
        System.out.println("Basket remove test passed.");
    }
}


class Basket_add implements Runnable{
    @Override
    public void run() {
        Basket basket = new Basket();
        basket.add(new Egg("brown", 24, 4));
        basket.add(new Fruit("Kiwi", 2.0, 100));
        MarketProduct[] l = basket.getProducts();
        int i = l.length -1;
        String expected = "Kiwi";
        String actual = l[i].getName();
        if(!actual.equals(expected))
            throw new AssertionError("Expected the last item to be the Fruit with name \" "
                    + expected + "\" but got the name \"" + actual + "\" instead. ");
        
        System.out.println("Basket add test passed.");
    }
}


class Basket_clear implements Runnable{
    @Override
    public void run() {
        Basket basket = new Basket();
        basket.add(new Egg("brown", 24, 4));
        basket.add(new Fruit("kiwi", 2.0, 100));
        basket.clear();
        MarketProduct[] l = basket.getProducts();
        if (l.length != 0)
            throw new AssertionError("Expected the MarketProduct array to be empty, but it was not. ");
        System.out.println("Basket clear test passed.");
    }
}


class Basket_getSubTotal implements Runnable{
    @Override
    public void run() {
        Basket basket = new Basket();
        basket.add(new Egg("brown", 24, 4));
        basket.add(new Fruit("kiwi", 2.0, 100));
        int expected = 208;
        int actual = basket.getSubTotal();
        if (actual != expected)
            throw new AssertionError("Expected the total cost to be " + expected + " cents, but got "
                    + actual + " cents instead");
        System.out.println("Basket getSubTotal test passed.");
    }
}


class Basket_toString implements Runnable{
    @Override
    public void run() {
        Basket basket = new Basket();
        basket.add(new Egg("brown", 24, 4));
        basket.add(new Fruit("kiwi", 2.0, 100));
        String expected = "brown\t0.08\n" +
                "kiwi\t2.00\n" +
                "\n" +
                "Subtotal\t2.08\n" +
                "Total Tax\t-\n" +
                "\n" +
                "Total Cost\t2.08";
        String actual = basket.toString().trim();
        if (!actual.equals(expected))
            throw new AssertionError("Incorrect format ");
        System.out.println("Basket toString test passed. ");
    }
}


class Customer_addFunds implements Runnable{
    @Override
    public void run() {
        try{
            Customer cust  = new Customer("Alice",890);
            int newFund = cust.addFunds(10);
            int expectedFund = 900;

            if (newFund != expectedFund){
                throw new AssertionError("addFunds returned " + newFund
                + " but expected " + expectedFund);
            }
        }
        catch(IllegalArgumentException e){
            throw new AssertionError("Unexpected IllegalArgumentException raised.");
        }
        System.out.println("Test passed.");
    }
}


class Customer_addFundsException implements Runnable{
    @Override
    public void run() {
        try {
            Customer cust = new Customer("Alice", 890);
            int newFund = cust.addFunds(-10);
            throw new AssertionError("Negative funds added. IllegalArgumentException no raised.");
        }
        catch (IllegalArgumentException e){
            System.out.println("Test passed.");
        }
    }
}


class Customer_addToBasket implements Runnable{
    @Override
    public void run() {
        Customer cust  = new Customer("Alice",900);
        cust.addToBasket(new Egg("Brown Eggs",5,380));
        cust.addToBasket(new Egg("White Eggs",10,400));

        if (cust.getBasket().getNumOfProducts() != 2){
            throw new AssertionError("Products were not added to basket.");
        }

        System.out.println("Test passed.");
    }
}


class Customer_checkOutBalance implements Runnable{
    @Override
    public void run() {
        try{
            Customer cust  = new Customer("Alice",900);
            cust.addToBasket(new Egg("organic Eggs",5,380));
            System.out.println("Balance before checkout: " + cust.getBalance());
            cust.checkOut();
            int newBalance = cust.getBalance();
            System.out.println("Balance after checkout: " + newBalance);
            if (newBalance != 742){
                throw new AssertionError("checkOut did not return correct receipt.");
            }
        }catch (IllegalStateException e){
            throw new AssertionError("Unexpected IllegalStateException raised.");
        }
        System.out.println("Test passed.");
    }
}


class Customer_checkOutException implements Runnable{
    @Override
    public void run() {
        try {
            Customer cust = new Customer("Alice", 10);
            cust.addToBasket(new Fruit("Blue Berry", 1.5, 380));
            cust.checkOut();
            throw new AssertionError("Checkout allowed when insufficient funds. IllegalStateException not raised.");
        }
        catch (IllegalStateException e){
            System.out.println("Test passed.");
        }
    }
}


class Customer_checkOutReceipt implements Runnable{
    @Override
    public void run() {
        try{
            Customer cust  = new Customer("Alice",900);
            cust.addToBasket(new Fruit("Blue Berry",1.5,380));
            String basketBefore = cust.getBasket().toString();
            String receipt = cust.checkOut();
            System.out.println("Receipt:\n" + receipt);
            if (!basketBefore.equals(receipt)){
                throw new AssertionError("checkOut did not return correct receipt.");
            }
        }catch (IllegalStateException e){
            throw new AssertionError("Unexpected IllegalStateException raised.");
        }
        System.out.println("Test passed.");
    }
}


class Customer_getBalance implements Runnable{
    @Override
    public void run() {
        Customer cust  = new Customer("Jane",123);

        if (cust.getBalance() != 123){
            throw new AssertionError("getBalance() did not return correct value.");
        }
        System.out.println("Test passed.");
    }
}


class Customer_getBasket implements Runnable{
    @Override
    public void run() {
        Customer cust  = new Customer("Jane",123);

        if (cust.getBasket() == null){
            throw new AssertionError("getBasket() must not be null.");
        }
        System.out.println("Test passed.");
    }
}


class Customer_getName implements Runnable{
    @Override
    public void run() {
        Customer cust  = new Customer("Jane",123);

        if (!cust.getName().equals(("Jane"))){
            throw new AssertionError("getName() did not return correct value.");
        }
        System.out.println("Test passed.");
    }
}


class Customer_removeFromBasket implements Runnable{
    @Override
    public void run() {
        Customer cust  = new Customer("Alice",900);
        cust.addToBasket(new Egg("Brown Eggs",5,380));
        Fruit product = new Fruit("Blue Berry", 1.5, 500);
        cust.addToBasket(product);
        cust.removeFromBasket(product);
        if (cust.getBasket().getNumOfProducts() != 1){
            throw new AssertionError("Product was not removed from basket.");
        }
        System.out.println("Test passed.");
    }
}


class Egg_Cost implements Runnable {
    @Override
    public void run() {
        Egg fancyEggs = new Egg("Fancy Eggs", 4, 380);
        int costOffancyEggs = fancyEggs.getCost();
        if (costOffancyEggs != 126) {
            throw new AssertionError("getCost() returned " +
                    costOffancyEggs + " but expected " + 126);
        }
        System.out.println("Egg cost test passed.");
    }
}


class Egg_Equal implements Runnable{
    @Override
    public void run() {
        Egg egg1 = new Egg("Fancy Eggs", 4, 380);
        Egg egg2 = new Egg("Fancy Eggs", 4, 380);
        if (!egg1.equals(egg2)){
            throw new AssertionError("equals() doesn't compare two Eggs correctly.");
        }
        System.out.println("Egg equals test passed.");
    }
}


class Egg_Name implements Runnable{
    @Override
    public void run() {
        Egg fancyEggs = new Egg("Fancy Eggs", 4, 380);
        String nameOffancyEggs = fancyEggs.getName();
        if(! nameOffancyEggs.equals("Fancy Eggs")){
            throw new AssertionError("getName() returned " +
                    nameOffancyEggs + " but expected " + "Fancy Eggs");
        }
        System.out.println("Egg name test passed.");
    }
}


class Fruit_Cost implements Runnable {
    @Override
    public void run() {
        Fruit myFruits = new Fruit("Asian Pear",1.25,530);
        int costOfMyFruits = myFruits.getCost();
        int expectedCost = 662;
        if(costOfMyFruits != expectedCost){
            throw new AssertionError("getCost() returned " +
                    costOfMyFruits + " but expected " + expectedCost);
        }
        System.out.println("Fruit cost test passed.");
    }
}


class Fruit_Equal implements Runnable{
    @Override
    public void run() {
        Egg egg = new Egg("Fancy Eggs", 4, 380);
        Fruit fruit = new Fruit("Fancy Eggs", 4, 380);
        if (egg.equals(fruit)){
            throw new AssertionError("equals() doesn't compare Egg with Fruit correctly.");
        }
        System.out.println("Fruit equals test passed.");
    }
}


class Jam_Cost implements Runnable {
    @Override
    public void run() {
        Jam myJam = new Jam("Quince Marmalade",2,475);
        int costOfmyJam = myJam.getCost();
        if(costOfmyJam != 950) {
            throw new AssertionError("getCost() returned " +
                    costOfmyJam + " but expected " + 950);
        }
        System.out.println("Jam cost test passed.");
    }
}


class SeasonalFruit_Cost implements Runnable {
    @Override
    public void run() {
        SeasonalFruit fancyFruit = new SeasonalFruit("Burzul Walnut", 0.5, 480);
        int costOffancyFruit = fancyFruit.getCost();
        if(costOffancyFruit != 204){
            throw new AssertionError("getCost() returned " +
                    costOffancyFruit + " but expected " + 204);
        }
        System.out.printf("SeasonalFruit cost test passed.");
    }
}


public class A1_Minitester {
    // To skip running some tests, just comment them out below.
    static String[] tests = {
        "assignment1.Basket_JamTax",
"assignment1.Basket_NumOfProduct",
"assignment1.Basket_Remove",
"assignment1.Basket_add",
"assignment1.Basket_clear",
"assignment1.Basket_getSubTotal",
"assignment1.Basket_toString",
"assignment1.Customer_addFunds",
"assignment1.Customer_addFundsException",
"assignment1.Customer_addToBasket",
"assignment1.Customer_checkOutBalance",
"assignment1.Customer_checkOutException",
"assignment1.Customer_checkOutReceipt",
"assignment1.Customer_getBalance",
"assignment1.Customer_getBasket",
"assignment1.Customer_getName",
"assignment1.Customer_removeFromBasket",
"assignment1.Egg_Cost",
"assignment1.Egg_Equal",
"assignment1.Egg_Name",
"assignment1.Fruit_Cost",
"assignment1.Fruit_Equal",
"assignment1.Jam_Cost",
"assignment1.SeasonalFruit_Cost"
    };
    public static void main(String[] args) {
        int numPassed = 0;
        for(String className: tests)    {
            System.out.printf("%n======= %s =======%n", className);
            System.out.flush();
            try {
                Runnable testCase = (Runnable) Class.forName(className).getDeclaredConstructor().newInstance();
                testCase.run();
                numPassed++;
            } catch (AssertionError e) {
                System.out.println(e);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        System.out.printf("%n%n%d of %d tests passed.%n", numPassed, tests.length);
    }
}
