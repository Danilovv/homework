import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FieldTest {

    @Test
    public void test() {
        Field field = new Field(20, 10);
        assertEquals(20, field.box.length);
        assertNotNull(field.box);
    }

}