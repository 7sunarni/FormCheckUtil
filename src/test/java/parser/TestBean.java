package parser;

import annotation.FormField;
import annotation.FormType;

import java.math.BigDecimal;

/**
 * Model:
 * Description:
 * Author: 张立新
 * created: 2019/7/26
 */
@FormType
public class TestBean {

    @FormField(checkLength = 20, respCode = 201001, respName = "描述")
    private String desc;

    @FormField(checkLength = 10, checkRange = {10, 1000000}, respCode = 201001, respName = "体重")
    private BigDecimal weight;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
