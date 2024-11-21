import java.util.ArrayList;
import java.util.List;

public class Stock {
    private final String name; 
    private double price;
    private final List<Investor> investors; 

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
        this.investors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double newPrice) {
        if (newPrice != this.price) {
            this.price = newPrice;
            notifyInvestors();
        }
    }

    public void addInvestor(Investor investor) {
        investors.add(investor);
    }

    public void removeInvestor(Investor investor) {
        investors.remove(investor);
    }

    private void notifyInvestors() {
        for (Investor investor : investors) {
            investor.update(this);
        }
    }
}
