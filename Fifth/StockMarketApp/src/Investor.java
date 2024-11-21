public class Investor {
    private final String name; 

    public Investor(String name) {
        this.name = name;
    }

    public void update(Stock stock) {
        System.out.println("Notification for " + name + ": " + stock.getName() + " price changed to " + stock.getPrice());
    }

    public String getName() {
        return name;
    }
}
