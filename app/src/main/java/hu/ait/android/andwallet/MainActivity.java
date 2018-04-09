package hu.ait.android.andwallet;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    boolean Toggle = false;


    @BindView(R.id.etAdd)
    EditText etAdd;

    @BindView(R.id.etAmount)
    EditText etAmount;

    @BindView(R.id.layoutContent)
    LinearLayout layoutContent;

    @BindView(R.id.tvBalance)
    TextView tvBalance;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private int balance=0;
    private int income=0;
    private int expense=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        tvBalance.setText(getString(R.string.balance, balance));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                layoutContent.removeAllViews();
                balance=0;
                income=0;
                expense=0;
                tvBalance.setText(getString(R.string.balance, balance));
                break;

            case R.id.action_summary:
                DataManager.getInstance().setTotal(balance);
                DataManager.getInstance().setIncome(income);
                DataManager.getInstance().setExpense(expense);

                Intent intentSummary = new Intent(this, SummaryActivity.class);
                startActivity(intentSummary);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.btnSave)
    public void addPressed(Button btnSave){
        if(checkEmpty()) {
            final View valueRow = getLayoutInflater().inflate(
                    R.layout.layout_value_row,
                    null);

            TextView tvName = valueRow.findViewById(R.id.tvName);
            tvName.setText(etAdd.getText().toString());

            TextView tvAmount = valueRow.findViewById(R.id.tvAmount);
            tvAmount.setText(String.format("$%s", etAmount.getText().toString()));

            ImageView ivMoney = valueRow.findViewById(R.id.ivMoney);
            if (Toggle) {
                ivMoney.setImageResource(R.drawable.red_money);
            }

            layoutContent.addView(valueRow, 0);

            balanceText(Integer.parseInt(etAmount.getText().toString()));
        }
    }

    public void balanceText(int number){
        if(Toggle){
            balance-=number;
            expense-=number;
        }else{
            balance+=number;
            income+=number;
        }

        tvBalance.setText(getString(R.string.balance, balance));
    }

    public boolean checkEmpty(){
        if(etAdd.getText().toString().isEmpty()){
            Snackbar.make(layoutContent, R.string.nameErrorMessage, Snackbar.LENGTH_LONG).show();
            return false;
        }
        if(etAmount.getText().toString().isEmpty()){
            Snackbar.make(layoutContent, R.string.amountErrorMessageEmpty, Snackbar.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    @OnClick(R.id.btnToggle)
    public void togglePressed(Button btnToggle){
        if(Toggle){
            Toggle=false;
            btnToggle.setText(getResources().getText(R.string.income));
        }else{
            Toggle=true;
            btnToggle.setText(getResources().getText(R.string.expense));
        }
    }


}
