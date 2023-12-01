package year2023.Day1;

import org.junit.Test;
import year2023.Day1.TransCoder;

public class TransCoderTest {
    @Test
    public void TranscodingBaseTest() {
        assert TransCoder.extractDigits("1abc2", false) == 12;
        assert TransCoder.extractDigits("pqr3stu8vwx", false) == 38;
        assert TransCoder.extractDigits("a1b2c3d4e5f", false) == 15;
        assert TransCoder.extractDigits("treb7uchet", false) == 77;
    }
    @Test
    public void TranscodingBaseTestNumbersAllowed() {
        assert TransCoder.extractDigits("two1nine", true) == 29;
        assert TransCoder.extractDigits("eightwothree", true) == 83;
        assert TransCoder.extractDigits("abcone2threexyz", true) == 13;
        assert TransCoder.extractDigits("xtwone3four", true) == 24;
        assert TransCoder.extractDigits("4nineeightseven2", true) == 42;
        assert TransCoder.extractDigits("zoneight234", true) == 14;
        assert TransCoder.extractDigits("7pqrstsixteen", true) == 76;
    }

    @Test
    public void TranscodingMultiLineTest() {
        assert TransCoder.multiLineExtract("two1nine\n" +
                "eightwothree\n" +
                "abcone2threexyz\n" +
                "xtwone3four\n" +
                "4nineeightseven2\n" +
                "zoneight234\n" +
                "7pqrstsixteen", true) == 281;
    }

}
