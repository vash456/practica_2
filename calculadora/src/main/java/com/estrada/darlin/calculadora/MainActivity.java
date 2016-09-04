package com.estrada.darlin.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    EditText num1, num2;
    TextView resul, signo;
    RadioButton suma, resta, multi, div;
    Button calcula;
    double ope_resul;
    int ope;
    int tamano_num1;
    int tamano_num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText) findViewById (R.id.num1);
        num2 = (EditText) findViewById (R.id.num2);
        resul = (TextView) findViewById (R.id.resul);
        signo = (TextView) findViewById (R.id.signo);
        suma = (RadioButton) findViewById(R.id.suma);
        resta = (RadioButton) findViewById(R.id.resta);
        multi = (RadioButton) findViewById(R.id.multi);
        div = (RadioButton) findViewById(R.id.div);
        calcula = (Button) findViewById(R.id.calcula);

        /////deteccion de cambio en el Edit Text por medio de contador para las posiciones que se
        /////a movido el cursor desde la posicion inicial
        num1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tamano_num1 =s.length();
            }
        });
        num2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tamano_num2 =s.length();
            }
        });

    }

    public void buttonOnClick(View view) {
        double n1, n2;

        if (ope > 0){
            if ((tamano_num1 != 0)&&(tamano_num2 != 0)) {
                //n1 = Integer.parseInt(num1.getText().toString());
                //n2 = Integer.parseInt(num2.getText().toString());
                n1 = Double.parseDouble(num1.getText().toString());
                n2 = Double.parseDouble(num2.getText().toString());
                switch (ope){
                    case 1:
                        ope_resul = n1 + n2;
                        break;
                    case 2:
                        ope_resul = n1 - n2;
                        break;
                    case 3:
                        ope_resul = n1 * n2;
                        break;
                    case 4:
                        ope_resul = n1 / n2;
                        break;
                }

                resul.setText(String.valueOf(ope_resul));
            }else {
                if(TextUtils.isEmpty(num1.getText().toString())){
                    num1.setError("Este item no puede estar vacio.");
                    return;
                }else if (TextUtils.isEmpty(num2.getText().toString())){
                    num2.setError("Este item no puede estar vacio.");
                    return;
                }
            }
        } else Toast.makeText(this, "Seleccione una operacion", Toast.LENGTH_SHORT).show();
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        resul.setText("");

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.suma:
                if (checked)
                    signo.setText("+");
                ope = 1;
                break;
            case R.id.resta:
                if (checked)
                    signo.setText("-");
                ope = 2;
                break;
            case R.id.multi:
                if (checked)
                    signo.setText("×");
                ope = 3;
                break;
            case R.id.div:
                if (checked)
                    signo.setText("÷");
                ope = 4;
                break;
        }
    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}

