package sg.edu.rp.c346.id22017723.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // Variables
    EditText enteramount;
    EditText numofpax;
    ToggleButton svsbutton;
    ToggleButton gstbutton;
    EditText enterdiscount;
    RadioGroup paytype;
    Button splitbutton;
    Button resetbutton;
    TextView totalbill;
    TextView eachpay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding?
        enteramount = findViewById(R.id.enteramt);
        numofpax = findViewById(R.id.enterpax);
        svsbutton = findViewById(R.id.SVS);
        gstbutton = findViewById(R.id.GST);
        enterdiscount = findViewById(R.id.enterdisc);
        paytype = findViewById(R.id.paymenttype);
        splitbutton = findViewById(R.id.split);
        resetbutton = findViewById(R.id.reset);
        totalbill = findViewById(R.id.totalBill);
        eachpay = findViewById(R.id.eachPay);


        splitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if amt entered & pax entered is not 0
                if(enteramount.getText().toString().length()!=0 && numofpax.getText().toString().length()!=0){
                    double total = 0.0;
                    // Double = java.lang. parseDouble = String S, TO CONVERT STRING INTO DOUBLE??? OK.
                    // BOTH IS NOT CHECKED
                    if (!svsbutton.isChecked() && !gstbutton.isChecked()){
                        total = Double.parseDouble(enteramount.getText().toString());
                    }
                    //if svs is checked and gst is not checked. SVS = 10%, 1.1.
                    else if(svsbutton.isChecked() && !gstbutton.isChecked()){
                        total = Double.parseDouble(enteramount.getText().toString()) * 1.1;
                    }
                    //if svs is not checked and gst is checked. GST = 7%, 1.07.
                    else if(!svsbutton.isChecked() && gstbutton.isChecked()){
                        total = Double.parseDouble(enteramount.getText().toString()) * 1.08;
                    }
                    //BOTH CHECKED. GST = 117%, 1.17.
                    else{
                        total = (Double.parseDouble(enteramount.getText().toString()) * 1.1 ) * 1.08;
                    }

                    //discounts???  * n /100 BRACKETS I HATE U. NOOOOOO
                    if (enterdiscount.getText().toString().length() != 0){
                        total = total * (1- (Double.parseDouble(enterdiscount.getText().toString()) / 100));
                    }
                    //TOTAL PAYMENT STUFF YA YA YA...
                    totalbill.setText("Total Bill: $" + String.format("%.2f",total));

                    //PAYNOW/CASH

                    //get total pax
                    int numpax = Integer.parseInt(numofpax.getText().toString());
                    if (numpax !=1){
                        int checkedRadioId = paytype.getCheckedRadioButtonId();
                        if(checkedRadioId == R.id.cash) {
                            eachpay.setText("Each Pays: $" + String.format("%.2f", total / numpax) + " in cash");
                        }else if (checkedRadioId == R.id.paynow){
                            eachpay.setText("Each Pays: $" + String.format("%.2f", total / numpax) + " via PayNow to 98765432");
                        }
                    }else{
                        eachpay.setText("Each Pays: $" +String.format("%.2f",total));
                    }

                }
                //OMG I DID IT. HOLY GOD LORD. I WASNT SUPPOSED TO PUT A LINEAR LAYOUT INSIDE THE RADIO GROUP. OH MY BLOODY GOD. END ME PLEASE.









            }
        });
            //REST@@@!!!!!!
        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterdiscount.setText("");
                enteramount.setText("");
                numofpax.setText("");
                svsbutton.setChecked(false);
                gstbutton.setChecked(false);

                //I have no idea how to reset back to the text? Since my text was inside the code ?? I dont know??? If it works it works, im happy enough...
                totalbill.setText("Total Bill: $");
                eachpay.setText("Each Pays: $");
                paytype.clearCheck();

                // I think the only enhancement I did was for the reset to default state and input type=number for the... input? I think that was an enhancement.




            }
        });
    }
}