public class SalesTaxCalculator {
    public static void main(String[] args) {
        String stateName = args[0];
        double saleAmount = Double.parseDouble(args[1]); //declare variables to handle the command line arguments
        State state;
        SalesTaxBehavior taxBehavior;
        //determine state and tax type
        //create objects of each state
        switch (stateName.toLowercase()){
            case "indiana":
                state = new Indiana();
                taxBehavior = new SevenPercent();
                break;
            case "alaska":
                state = new Alaska();
                taxBehavior = new NoTax();
                break;
            case "hawaii":
                state = new Hawaii();
                taxBehavior = new FourPointFivePercent();
                break;
            default:
                System.out.println("unsupported state: "+stateName);
                return;
        }
        double tax = taxBehavior.compute(saleAmount);//find the tax amount
        System.out.printf("The sales tax on $%.2f in %s is $%.2f.%n", saleAmount, state.getName(), tax);
        //format the print statement to two decimal places
    }

    public abstract static class State {
        private String name; //private because of the - symbol on the UML diagram
        public String getName() {
            return name;
        }
        //make getter and setter for the two state names
        //public because of the + symbol on UML diagram
        public void setName(String name) {
            this.name = name;
        }
        //public because of the + symbol on UML diagram
        public abstract String showTax(); //implemented showTax to allow access for classes that extend State
        //public because of the + symbol on UML diagram

    }

    public static class Indiana extends State{
        public Indiana() {
            setName("Indiana");//set name for indiana
        }
        @Override
        public String showTax() {
                return "7% Sales tax"; //use the Override function to get the showTax function from parent class
            }
    }

    public static class Alaska extends State {
        public Alaska() {
            setName("Alaska"); //set name for Alaska
        }
        @Override
        public String showTax() {
                return "No sales tax"; //use the Override function to get the showTax function from parent class
        }
    }

    public static class Hawaii extends State{
        public Hawaii() {setName("Hawaii");}

        @Override
        public String showTax() {
            return "4.5% Sales tax"; //Get showtax function from parent class
        }
    }

    interface SalesTaxBehavior{
        Double compute(Double amount); //declare here to be used by both classes that implement the interface
    }
    public static class NoTax implements SalesTaxBehavior{
        @Override //implement the parent class due to the arrows in the UML diagram
        public Double compute(Double amount) {
            return amount;
        }
    }
    public static class SevenPercent implements SalesTaxBehavior {
        @Override
        public Double compute(Double amount){
            return amount*0.07;
        }
    }
    public static class FourPointFivePercent implements SalesTaxBehavior{
        @Override
        public Double compute(Double amount) { return amount *0.045;}
    }
}

