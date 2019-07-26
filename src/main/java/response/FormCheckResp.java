package response;

/**
 * Model:
 * Description:
 * Author: 张立新
 * created: 2019/7/26
 */
public class FormCheckResp {
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
