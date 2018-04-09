package hu.ait.android.andwallet;

/**
 * Created by teagu_000 on 20/10/2017.
 */

public class DataManager {

    private DataManager(){
    }

    private static DataManager instance = null;

    public static  DataManager getInstance(){
        if(instance==null){
            instance = new DataManager();
        }

        return instance;
    }

    private int total;

    private int income;

    private int expense;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }
}


