package br.net.bart.nursecalc;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bartolomeu.gusella on 14/11/2017.
 */
public class ProportionActivityTest {

    protected ProportionActivity proportionActivity;

    @Before
    public void before(){
        proportionActivity = new ProportionActivity();
    }

    //checkProportion()
    @Test
    public void checkProportion_integers() throws Exception {
        assertEquals(true, proportionActivity.checkProportion(1, 2, 3, 6));
    }

    @Test
    public void checkProportion_integersWrong() throws Exception {
        assertEquals(false, proportionActivity.checkProportion(1, 2, 3, 5));
    }

    @Test
    public void checkProportion_decimals() throws Exception {
        assertEquals(true, proportionActivity.checkProportion(5f, 7f, 28.0f, 39.2f));
    }

    @Test
    public void checkProportion_fristZero() throws Exception {
        assertEquals("Validação com 0 no 1° campo", false, proportionActivity.checkProportion(0, 2, 3, 6));
    }

    @Test
    public void checkProportion_secondZero() throws Exception {
        assertEquals("Validação com 0 no 2° campo", false, proportionActivity.checkProportion(1, 0, 3, 6));
    }

    @Test
    public void checkProportion_thirdZero() throws Exception {
        assertEquals("Validação com 0 no 3° campo", false, proportionActivity.checkProportion(1, 2, 0, 6));
    }

    @Test
    public void checkProportion_fourthZero() throws Exception {
        assertEquals("Validação com 0 no 4° campo", false, proportionActivity.checkProportion(1,2,3,0));
    }
}
