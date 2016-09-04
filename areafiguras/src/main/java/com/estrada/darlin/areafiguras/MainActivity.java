package com.estrada.darlin.areafiguras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.math.*;

public class MainActivity extends AppCompatActivity {

    private View conten_triangulo;
    private View conten_rectangulo;
    EditText lado, base, altura, largo, ancho, radio;
    Button calcular;
    RadioButton cuadrado, triangulo, rectangulo, circulo;
    TextView resultado;
    int opcion;
    double resul_area;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conten_triangulo = findViewById(R.id.ll_conten_triangulo);
        conten_rectangulo = findViewById(R.id.ll_conten_rectangulo);
        lado = (EditText) findViewById (R.id.et_lado);
        base = (EditText) findViewById (R.id.et_base);
        altura = (EditText) findViewById (R.id.et_altura);
        largo = (EditText) findViewById (R.id.et_largo);
        ancho = (EditText) findViewById (R.id.et_ancho);
        radio = (EditText) findViewById (R.id.et_radio);
        resultado = (TextView) findViewById (R.id.b_resultado);
        cuadrado = (RadioButton) findViewById(R.id.rb_cuadrado);
        triangulo = (RadioButton) findViewById(R.id.rb_triangulo);
        rectangulo = (RadioButton) findViewById(R.id.rb_rectangulo);
        circulo = (RadioButton) findViewById(R.id.rb_circulo);
        calcular = (Button) findViewById(R.id.b_calcular);

    }

    public void onRadioButtonClicked(View view) {
        boolean marcado = ((RadioButton) view).isChecked();
        resultado.setText("Area");
        lado.setText("");
        base.setText("");
        altura.setText("");
        largo.setText("");
        ancho.setText("");
        radio.setText("");

        switch (view.getId()) {
            case R.id.rb_cuadrado:
                if (marcado) {
                    lado.setVisibility(View.VISIBLE);
                    conten_triangulo.setVisibility(View.GONE);
                    conten_rectangulo.setVisibility(View.GONE);
                    radio.setVisibility(View.GONE);
                    opcion = 1;
                }
                break;

            case R.id.rb_triangulo:
                if (marcado) {
                    lado.setVisibility(View.GONE);
                    conten_triangulo.setVisibility(View.VISIBLE);
                    conten_rectangulo.setVisibility(View.GONE);
                    radio.setVisibility(View.GONE);
                    opcion = 2;
                }
                break;
            case R.id.rb_rectangulo:
                if (marcado) {
                    lado.setVisibility(View.GONE);
                    conten_triangulo.setVisibility(View.GONE);
                    conten_rectangulo.setVisibility(View.VISIBLE);
                    radio.setVisibility(View.GONE);
                    opcion = 3;
                }
                break;
            case R.id.rb_circulo:
                if (marcado) {
                    lado.setVisibility(View.GONE);
                    conten_triangulo.setVisibility(View.GONE);
                    conten_rectangulo.setVisibility(View.GONE);
                    radio.setVisibility(View.VISIBLE);
                    opcion = 4;
                }
                break;
        }
    }


    public void buttonOnClick(View view) {
        double lado_c, base_t, altura_t, largo_r, ancho_r, radio_c;

        if (opcion > 0) {
            switch (opcion) {
                case 1:
                    if (TextUtils.isEmpty(lado.getText().toString())) {
                        //lado.setError("Este item no puede estar vacio.");
                        Toast.makeText(this, "Este item no puede estar vacio.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        lado_c = Double.parseDouble(lado.getText().toString());
                        resul_area = Math.pow(lado_c, 2);
                    }
                    break;

                case 2:
                    if (TextUtils.isEmpty(base.getText().toString()) ||
                            TextUtils.isEmpty(altura.getText().toString())) {
                        //lado.setError("Este item no puede estar vacio.");
                        Toast.makeText(this, "Los items no puede estar vacios.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        base_t = Double.parseDouble(base.getText().toString());
                        altura_t = Double.parseDouble(altura.getText().toString());
                        resul_area = base_t * altura_t / 2;
                    }
                    break;

                case 3:
                    if (TextUtils.isEmpty(largo.getText().toString()) ||
                            TextUtils.isEmpty(ancho.getText().toString())) {
                        //lado.setError("Este item no puede estar vacio.");
                        Toast.makeText(this, "Los items no puede estar vacios.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        largo_r = Double.parseDouble(largo.getText().toString());
                        ancho_r = Double.parseDouble(ancho.getText().toString());
                        resul_area = largo_r * ancho_r;
                    }
                    break;

                case 4:
                    if (TextUtils.isEmpty(radio.getText().toString())) {
                        //lado.setError("Este item no puede estar vacio.");
                        Toast.makeText(this, "Este item no puede estar vacio.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        radio_c = Double.parseDouble(radio.getText().toString());
                        resul_area = Math.PI * Math.pow(radio_c, 2);
                    }
                    break;
            }
            resultado.setText("Area = "+String.valueOf(resul_area));
        }else Toast.makeText(this, "Seleccione una figura.", Toast.LENGTH_SHORT).show();


    }

}

