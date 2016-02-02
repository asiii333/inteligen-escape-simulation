package pl.sp.simulation;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Asia on 2015-06-05.
 */
public class ConstansTest {

    @Test
    public void testConstans() {
        Constans constans = new Constans();
        String key = "test";
        Assert.assertEquals("ok",constans.getValue(key));

    }
}
