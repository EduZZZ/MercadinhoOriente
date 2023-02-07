package MercadinhoOriente.main;

public class Cashier {

    private int day;
    private int month;
    private String turn;
    private double billing;
    private double saleCard;
    private double expense;

    public Cashier() {
    }

    public Cashier(int day, int month, String turn, double billing, double saleCard, double expense) {
        this.day = day;
        this.month = month;
        this.turn = turn;
        this.billing = billing;
        this.saleCard = saleCard;
        this.expense = expense;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public double getBilling() {
        return billing;
    }

    public void setBilling(double billing) {
        this.billing = billing;
    }

    public double getSaleCard() {
        return saleCard;
    }

    public void setSaleCard(double saleCard) {
        this.saleCard = saleCard;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public double closingCashier() {

        double finalValue = this.getBilling() + this.getSaleCard() + this.getExpense();

        return finalValue;
    }
}
