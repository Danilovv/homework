import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StringNumbersTest {

    private int _number;
    private String _expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                        {10, "десять"},
                        {1, "один"},
                        {21, "двадцать один"},
                        {2, "два"},
                        {3, "три"},
                        {74, "семьдесят четыре"},
                        {0, "ноль"},

                }
        );
    }

    public StringNumbersTest(int number, String expected) {
        _number = number;
        _expected = expected;
    }

    @Test
    public void test() {
        assertEquals(_expected, StringNumbers.convert(_number));
    }

}