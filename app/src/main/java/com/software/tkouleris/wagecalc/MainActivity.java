package com.software.tkouleris.wagecalc;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txtNetAmount = (TextView)findViewById(R.id.netAmount);
        final TextView txtWithHoldingAmount = (TextView)findViewById(R.id.txtWithHoldingAmount);
        final TextView txtTax = (TextView)findViewById(R.id.txtTax);
        final TextView txtMixed = (TextView)findViewById(R.id.txtMixed);
        final TextView txIncomingTransfer = (TextView)findViewById(R.id.txIncomingTransfer);

        // Calculation Button Listener
        Button btnCalculate = (Button)findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                DecimalFormat df2 = new DecimalFormat(".##");

                Double dbWithHoldingAmount = .0;
                Double dbNetAmount = .0;
                Double dbTaxAmount = .0;
                Double dbMixedAmount = .0;
                Double dbIncomingTransfer = .0;
                String tmp = "";

                tmp = txtNetAmount.getText().toString();
                if(isNullOrEmpty(tmp)) tmp = "0.0";

                dbNetAmount = Double.parseDouble(tmp);

                // Calculate Withholdings
                dbWithHoldingAmount = dbNetAmount * 0.20;
                //tmp = Double.toString(  dbWithHoldingAmount  );
                tmp = df2.format(dbWithHoldingAmount );
                txtWithHoldingAmount.setText(tmp);

                // Calculate Tax
                dbTaxAmount = dbNetAmount * 0.24;
                tmp = df2.format(dbTaxAmount );
                txtTax.setText(tmp);

                // Calculate Mixed
                dbMixedAmount = dbNetAmount + dbTaxAmount;
                tmp = df2.format( dbMixedAmount );
                txtMixed.setText(tmp);

                // Calculate Incoming Transfer
                dbIncomingTransfer = dbNetAmount + (dbTaxAmount - dbWithHoldingAmount);
                tmp = df2.format( dbIncomingTransfer );
                txIncomingTransfer.setText(tmp);

            }
        });

        Button btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Clear
                txtNetAmount.setText(".0");
                txtWithHoldingAmount.setText(".0");
                txtTax.setText(".0");
                txtMixed.setText(".0");
                txIncomingTransfer.setText(".0");

            }
        });



    }

    private boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

}
