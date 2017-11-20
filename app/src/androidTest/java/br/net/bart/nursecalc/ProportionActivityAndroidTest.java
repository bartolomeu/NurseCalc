package br.net.bart.nursecalc;

import android.support.design.widget.TextInputLayout;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by bartolomeu.gusella on 17/11/2017.
 */
@RunWith(AndroidJUnit4.class)
public class ProportionActivityAndroidTest {

    protected ProportionActivity proportionActivity;

    @Before
    public void before(){
        proportionActivity = new ProportionActivity();
    }

    @Test
    public void validateAllETProps() throws Exception {
        //@TODO testa se os campos estão vazios antes de começar

        //validateOnAIR = TRUE;
        proportionActivity.calculate(proportionActivity.findViewById(R.id.prop_button_calculate));

        //@todo testa se todos os campos vazios estão com erro caso tenha mais de 1 campo vázio
    }

    @Test
    public void calculate() throws Exception {
    }

    @Test
    public void setEditTextProportion_1() throws Exception {
        EditText eTProp1 = (EditText) proportionActivity.findViewById(R.id.ETProp1);
        proportionActivity.setEditTextProportion(5,9,8, eTProp1);
        assertEquals("14.4", eTProp1.getText());
    }
    @Test
    public void setEditTextProportion_2() throws Exception {
        EditText eTProp2 = (EditText) proportionActivity.findViewById(R.id.ETProp2);
        proportionActivity.setEditTextProportion(3,6,5.5f, eTProp2);
        assertEquals("11", eTProp2.getText());
    }
    @Test
    public void setEditTextProportion_3() throws Exception {
        EditText eTProp3 = (EditText) proportionActivity.findViewById(R.id.ETProp3);
        proportionActivity.setEditTextProportion(3.5f,7.2f,7, eTProp3);
        assertEquals("14.4", eTProp3.getText());
    }
    @Test
    public void setEditTextProportion_4() throws Exception {
        EditText eTProp4 = (EditText) proportionActivity.findViewById(R.id.ETProp4);
        proportionActivity.setEditTextProportion(1.23f,59.8f,21.1f, eTProp4);
        assertEquals("1025.8374", eTProp4.getText()); //1025.8373983739837398373983739837
    }

    //TESTE ESTÚPIDO REVER !!
    @Test
    public void clearErrorFields() throws Exception {

        ((TextInputLayout) proportionActivity.findViewById(R.id.TIL1)).setError("nu");
        ((TextInputLayout) proportionActivity.findViewById(R.id.TIL2)).setError("nu");
        ((TextInputLayout) proportionActivity.findViewById(R.id.TIL3)).setError("nu");
        ((TextInputLayout) proportionActivity.findViewById(R.id.TIL4)).setError("nu");

        proportionActivity.clearErrorFields();

        assertEquals("TIL1 != null", ((TextInputLayout) proportionActivity.findViewById(R.id.TIL1)).getError(), null);
        assertEquals("TIL2 != null", ((TextInputLayout) proportionActivity.findViewById(R.id.TIL2)).getError(), null);
        assertEquals("TIL3 != null", ((TextInputLayout) proportionActivity.findViewById(R.id.TIL3)).getError(), null);
        assertEquals("TIL4 != null", ((TextInputLayout) proportionActivity.findViewById(R.id.TIL4)).getError(), null);
    }

}