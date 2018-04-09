package hu.ait.android.andwallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        int income = DataManager.getInstance().getIncome();

        int expense = DataManager.getInstance().getExpense();

        int total = DataManager.getInstance().getTotal();

        TextView tvIncome= (TextView) findViewById(R.id.tvIncome);
        tvIncome.setText(getString(R.string.incomeSummary, income));

        TextView tvExpense= (TextView) findViewById(R.id.tvExpense);
        tvExpense.setText(getString(R.string.expenseSummary, expense));

        TextView tvTotal= (TextView) findViewById(R.id.tvTotal);
        tvTotal.setText(getString(R.string.total, total));
    }
}
