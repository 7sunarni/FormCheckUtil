package response;

/**
 * Model:
 * Description:
 * Author: 张立新
 * created: 2019/7/26
 */
public class FormCheckResp {

    public final static String LENGTH_OVERFLOW = "%s超出最大长度，限制%s字符";
    public final static String ANNOTATION_ERROR = "范围注解长度错误";
    public final static String RANGE_OVERFLOW = "%s范围限制在%s到%s之间";

    private int code;
    private String desc;

    public FormCheckResp(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "FormCheckResp{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
