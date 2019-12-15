package Rest.data;

import javax.persistence.*;

@Entity
@Table(name="resultstopic2")
public class RTopic2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "revenues", nullable = false, unique = false)
    private float revenues;

    @Column(name = "expenses", nullable = false, unique = false)
    private float expenses;

    @Column(name = "profit", nullable = false, unique = false)
    private float profit;

    @Column(name = "average_purchase", nullable = false, unique = false)
    private float average_purchase;

    public float getRevenues() {
        return revenues;
    }

    public void setRevenues(float revenues) {
        this.revenues = revenues;
    }

    public float getExpenses() {
        return expenses;
    }

    public void setExpenses(float expenses) {
        this.expenses = expenses;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public float getAverage_purchase() {
        return average_purchase;
    }

    public void setAverage_purchase(float average_purchase) {
        this.average_purchase = average_purchase;
    }


}

