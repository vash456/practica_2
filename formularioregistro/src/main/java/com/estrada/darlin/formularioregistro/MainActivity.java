package com.estrada.darlin.formularioregistro;

import android.app.DatePickerDialog;
import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText loggin, password1, password2, e_email;
    private TextView t_sexo ,placebirth, reg_formulario;
    private Button b_aceptar;
    private int all_ok, check_rb;

    private TextView mDateDisplay;
    private Button mPickDate;
    private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATE_DIALOG_ID = 0;

    private String nombre, password, email, sexo, datebirth, ciudad;
    private String hobbies1 = "", hobbies2 = "", hobbies3 = "", hobbies4 = "",allhobbies;

    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loggin = (EditText) findViewById (R.id.et_loggin);
        password1 = (EditText) findViewById (R.id.et_password1);
        password2 = (EditText) findViewById (R.id.et_password2);
        e_email = (EditText) findViewById (R.id.et_email);
        t_sexo = (TextView) findViewById (R.id.t_sexo);
        placebirth = (TextView) findViewById (R.id.t_placebirth);
        reg_formulario = (TextView) findViewById (R.id.t_reg_formulario);
        b_aceptar = (Button) findViewById(R.id.b_aceptar);

        // capture our View elements
        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
        mPickDate = (Button) findViewById(R.id.pickDate);

        // add a click listener to the button
        mPickDate.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.ciudad_spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // get the current date
        /*final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);*/
        // display the current date (this method is below)
        //updateDisplay();
    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        ciudad = parent.getItemAtPosition(pos).toString();

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    // updates the date in the TextView
    private void updateDisplay() {
        mDateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mMonth + 1).append("/")
                        .append(mDay).append("/")
                        .append(mYear).append(" "));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

    public void onRadioButtonClicked(View view) {
        boolean marcado = ((RadioButton) view).isChecked();
        check_rb = 1;

        switch (view.getId()) {
            case R.id.rb_masculino:
                if (marcado) {
                    sexo = "Masculino";
                }
                break;
            case R.id.rb_femenino:
                if (marcado) {
                    sexo = "Femenino";
                }
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cbox_1:
                if (checked)
                    hobbies1 = " Internet";
                else
                    hobbies1 = "";
                break;
            case R.id.cbox_2:
                if (checked)
                    hobbies2 = " Leer";
                else
                    hobbies2 = "";
                break;
            case R.id.cbox_3:
                if (checked)
                    hobbies3 = " Deportes";
                else
                    hobbies3 = "";
                break;
            case R.id.cbox_4:
                if (checked)
                    hobbies4 = " Otro";
                else
                    hobbies4 = "";
                break;

        }
    }

    public void buttonOnClick(View view) {

        String pass1, pass2;

        pass1 = password1.getText().toString();
        pass2 = password2.getText().toString();

        allhobbies = hobbies1+hobbies2+hobbies3+hobbies4;

        all_ok = 0;

        if (TextUtils.isEmpty(loggin.getText().toString())) {
            loggin.setError("Este item no puede estar vacio.");
            return;
        }else all_ok++;//1

        if (TextUtils.isEmpty(password1.getText().toString())) {
            password1.setError("Este item no puede estar vacio.");
            return;
        }else all_ok++;//2

        if (TextUtils.isEmpty(password2.getText().toString())) {
            password2.setError("Este item no puede estar vacio.");
            return;
        }else all_ok++;//3

        if (pass1.equals(pass2) != true) {
            password2.setText("");
            password2.setError("El password debe ser igual.");
            return;
        }else all_ok++;//4

        if (TextUtils.isEmpty(e_email.getText().toString())) {
            e_email.setError("Este item no puede estar vacio.");
            return;
        }else all_ok++;//5

        if (check_rb != 1) {
            Toast.makeText(this, "Seleccione el sexo.", Toast.LENGTH_SHORT).show();
            return;
        }else all_ok++;//6

        if (TextUtils.isEmpty(mDateDisplay.getText().toString())) {
            Toast.makeText(this, "Seleccione la fecha de nacimiento.", Toast.LENGTH_SHORT).show();
            return;
        }else all_ok++;//7

        if (TextUtils.isEmpty(ciudad)) {
            Toast.makeText(this, "Seleccione la ciudad.", Toast.LENGTH_SHORT).show();
            return;
        }else all_ok++;//8

        if (TextUtils.isEmpty(allhobbies)) {
            Toast.makeText(this, "Seleccione algun hobbie.", Toast.LENGTH_SHORT).show();
            return;
        }else all_ok++;//9

        //numero de chequeos para confirmar que todos los campos esten llenos
        if (all_ok == 9) {

            nombre = loggin.getText().toString();
            password = password2.getText().toString();
            email = e_email.getText().toString();
            datebirth = mDateDisplay.getText().toString();

            reg_formulario.setText("\nDatos del Registro\n"+"Loggin: "+nombre+"\npassword: "
                    +password+ "\nEmail: "+email+"\nSexo: "+sexo+"\nFecha de Nacimiento: "
                    +datebirth+"\nLugar de nacimiento: " +ciudad+"\nHobbies: "+allhobbies);
        }

    }

}
