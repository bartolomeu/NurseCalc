package br.net.bart.nursecalc;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ProportionActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private  boolean validateOnAir = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proportion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextWatcher watcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void afterTextChanged(Editable s) {
                if(validateOnAir) validateAllETProps();
            }
        };

        EditText editTProp1 = (EditText) findViewById(R.id.ETProp1);
        editTProp1.addTextChangedListener(watcher);

        EditText editTProp2 = (EditText) findViewById(R.id.ETProp2);
        editTProp2.addTextChangedListener(watcher);

        EditText editTProp3 = (EditText) findViewById(R.id.ETProp3);
        editTProp3.addTextChangedListener(watcher);

        EditText editTProp4 = (EditText) findViewById(R.id.ETProp4);
        editTProp4.addTextChangedListener(watcher);

        findViewById(R.id.TVErrorMessage).setVisibility(View.INVISIBLE);

    }

    protected void validateAllETProps() {
        clearErrorFields();

        boolean isEmptyET1=false;
        boolean isEmptyET2=false;
        boolean isEmptyET3=false;
        boolean isEmptyET4=false;

        int count=0;

        try {
            EditText editText = (EditText) findViewById(R.id.ETProp1);
            Float.parseFloat(editText.getText().toString());
        } catch (NullPointerException ex) {
            Log.e("PROPO", "Campos", ex);
            return;
        } catch (NumberFormatException ex) {
            isEmptyET1 = true;
            count ++;
        }

        try {
            EditText editText = (EditText) findViewById(R.id.ETProp2);
            Float.parseFloat(editText.getText().toString());
        } catch (NullPointerException ex) {
            Log.v("PROPO", "Campos", ex);
            return;
        } catch (NumberFormatException ex) {
            isEmptyET2 = true;
            count ++;
        }

        try {
            EditText editText = (EditText) findViewById(R.id.ETProp3);
            Float.parseFloat(editText.getText().toString());
        } catch (NullPointerException ex) {
            Log.v("PROPO", "Campos", ex);
            return;
        } catch (NumberFormatException ex) {
            isEmptyET3 = true;
            count ++;
        }

        try {
            EditText editText=(EditText) findViewById(R.id.ETProp4);
            Float.parseFloat(editText.getText().toString());
        } catch (NullPointerException ex) {
            Log.v("PROPO", "Campos", ex);
            return;
        } catch (NumberFormatException ex) {
            isEmptyET4 = true;
            count ++;
        }

        if(count > 1){
            if(isEmptyET1) ((TextInputLayout) findViewById(R.id.TIL1)).setError(getString(R.string.exec_one_empty_field));
            if(isEmptyET2) ((TextInputLayout) findViewById(R.id.TIL2)).setError(getString(R.string.exec_one_empty_field));
            if(isEmptyET3) ((TextInputLayout) findViewById(R.id.TIL3)).setError(getString(R.string.exec_one_empty_field));
            if(isEmptyET4) ((TextInputLayout) findViewById(R.id.TIL4)).setError(getString(R.string.exec_one_empty_field));
        }

    }

    public void calculate(View view){
        validateOnAir=true;

        EditText campo1 = (EditText) findViewById(R.id.ETProp1);
        EditText campo2 = (EditText) findViewById(R.id.ETProp2);
        EditText campo3 = (EditText) findViewById(R.id.ETProp3);
        EditText campo4 = (EditText) findViewById(R.id.ETProp4);

        float val1=0;
        float val2=0;
        float val3=0;
        float val4=0;

        boolean et1Vazio=false;
        boolean et2Vazio=false;
        boolean et3Vazio=false;
        boolean et4Vazio=false;

        int count=0;

        try {
            val1 = Float.parseFloat(campo1.getText().toString());
        } catch (NullPointerException ex) {
            Log.e("PROPO", "Campos", ex);
            return;
        } catch (NumberFormatException ex) {
            et1Vazio = true;
            count++;
        }

        try {
            val2 = Float.parseFloat(campo2.getText().toString());
        } catch (NullPointerException ex) {
            Log.v("PROPO", "Campos", ex);
            return;
        } catch (NumberFormatException ex) {
            et2Vazio = true;
            count++;
        }

        try {
            val3 = Float.parseFloat(campo3.getText().toString());
        } catch (NullPointerException ex) {
            Log.v("PROPO", "Campos", ex);
            return;
        } catch (NumberFormatException ex) {
            et3Vazio = true;
            count++;
        }

        try {
            val4 = Float.parseFloat(campo4.getText().toString());
        } catch (NullPointerException ex) {
            Log.v("PROPO", "Campos", ex);
            return;
        } catch (NumberFormatException ex) {
            et4Vazio = true;
            count++;
        }

        if(count > 1){
            // mais de um campo vazio = erro
            if(et1Vazio) ((TextInputLayout) findViewById(R.id.TIL1)).setError(getString(R.string.exec_one_empty_field));
            if(et2Vazio) ((TextInputLayout) findViewById(R.id.TIL2)).setError(getString(R.string.exec_one_empty_field));
            if(et3Vazio) ((TextInputLayout) findViewById(R.id.TIL3)).setError(getString(R.string.exec_one_empty_field));
            if(et4Vazio) ((TextInputLayout) findViewById(R.id.TIL4)).setError(getString(R.string.exec_one_empty_field));
        }else if ( count == 0){
            //4 campos estão preenchidos, a proporção está correta?
            String msgValidationProp = getString(
                    checkProportion(val1, val2, val3, val4) ?
                            R.string.proportion_ok :
                            R.string.proportion_failed);

            TextView tvErrorMessage = ((TextView) findViewById(R.id.TVErrorMessage));
            tvErrorMessage.setText(msgValidationProp);
            tvErrorMessage.setVisibility(View.VISIBLE);

        }else{
            //calcula a proporção
            if (et1Vazio){
                setEditTextProportion(val4, val3, val2, campo1);
            }else if (et2Vazio) {
                setEditTextProportion(val3,val4,val1, campo2);
            } else if (et3Vazio) {
                setEditTextProportion(val2,val1,val4, campo3);
            }else if (et4Vazio) {
                setEditTextProportion(val1,val2,val3, campo4);
            }
        }

    }

    protected boolean checkProportion(float val1, float val2, float val3, float val4) {
        if(val1==0 || val2==0 || val3==0 ||val4==0)
            return false;

        float equation1 = Math.round(val1 / val2 * 10000f) / 10000f ; //round 4 decimals
        float equation2 = Math.round(val3 / val4 * 10000f) / 10000f; //round 4 decimals

        return (equation1 == equation2);
    }

    protected void setEditTextProportion(float v1, float v2, float v3, EditText editText) {
        float valueProportional = (v2/v1)*v3;
        //caso o valueCalc seja inteiro seta no EditText 5 ao invés de 5.0
        editText.setText(
                valueProportional == (int) valueProportional ?
                        Integer.toString((int) valueProportional) :
                        Float.toString(valueProportional));
    }

    protected void clearErrorFields() {
        findViewById(R.id.TVErrorMessage).setVisibility(View.INVISIBLE);
        ((TextInputLayout) findViewById(R.id.TIL1)).setError(null);
        ((TextInputLayout) findViewById(R.id.TIL2)).setError(null);
        ((TextInputLayout) findViewById(R.id.TIL3)).setError(null);
        ((TextInputLayout) findViewById(R.id.TIL4)).setError(null);
    }


}
