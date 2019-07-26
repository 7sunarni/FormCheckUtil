package parser;

import org.junit.Test;

import java.math.BigDecimal;

public class FormFieldParserTest {

    @Test
    public void t01() {
        TestBean testBean = new TestBean();
        testBean.setDesc("一二三四五六七八九十一二三四五六" +
                "七八九十一二三四五六七八九十一二三四五六七八九十");
        testBean.setWeight(new BigDecimal(5));
        FormParser formParser = new FormParser();
        formParser.formValidate(testBean);
    }
}