package parser;


import annotation.FormField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import response.FormCheckResp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Model:
 * Description:
 * Author: 张立新
 * created: 2019/7/23
 */
public class FormFieldParser {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String RESP_NAME = "respName";
    private static final String RESP_CODE = "respCode";
    private static final String CHECK_RANGE = "checkRange";
    private static final String CHECK_LENGTH = "checkLength";

    private String _name;
    private int _code;
    private float[] _range;
    private int _length;
    private Object _fieldValue;
    private Object _object;

    private FormField annotation;

    private Field field;


    public FormFieldParser(Object object, Field field) {
        this.field = field;
        this._object = object;
        annotation = this.field.getAnnotation(FormField.class);
        setRespName();
        setRespCode();
        setCheckRange();
        setCheckLength();
        setValue();
    }

    private void setRespName() {
        try {
            Method annotationMethod = FormField.class.getMethod(RESP_NAME);
            String annotationName = (String) annotationMethod.invoke(annotation);
            if (annotationName.equals("")) {
                annotationName = this.field.toString();
            }
            this._name = annotationName;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void setRespCode() {
        try {
            Method annotationMethod = FormField.class.getMethod(RESP_CODE);
            _code = (int) annotationMethod.invoke(annotation);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void setCheckRange() {
        try {
            Method annotationMethod = FormField.class.getMethod(CHECK_RANGE);
            _range = (float[]) annotationMethod.invoke(annotation);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private void setCheckLength() {
        try {
            Method annotationMethod = FormField.class.getMethod(CHECK_LENGTH);
            this._length = (int) annotationMethod.invoke(annotation);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void setValue() {
        StringBuilder getMethod = new StringBuilder("get");
        String name = field.getName();
        getMethod = getMethod.append(
                name.length() == 1 ?
                        name.toUpperCase() :
                        name.substring(0, 1).toUpperCase() + name.substring(1)
        );
        try {
            Method method = _object.getClass().getDeclaredMethod(getMethod.toString());
            _fieldValue = method.invoke(_object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private FormCheckResp validateLength() {
        if (_fieldValue.toString().length() > _length) {
            return new FormCheckResp(this._code, this._name + "超出最大长度，限制" + _length);
        } else {
            return new FormCheckResp(200, "OK");
        }
    }

    private FormCheckResp validateRange() {
        if (_range.length < 2) {
            return new FormCheckResp(200, "范围注解长度错误");
        }
        Double doubleValue;
        try {
            doubleValue = Double.valueOf(_fieldValue.toString());
        } catch (Exception e) {
            doubleValue = new Double(0);
//            return new FormCheckResp(200, this._name + "不能转换成数字类型");
        }

        if (doubleValue < _range[0] || doubleValue > _range[1]) {
            return new FormCheckResp(this._code, this._name + "范围限制在" + _range[0] + "到" + _range[1] + "之间");
        } else {
            return new FormCheckResp(200, "OK");
        }
    }

    public FormCheckResp validate() {
        if (_fieldValue == null) {
            return new FormCheckResp(200, this._name + "为空");
        }
        FormCheckResp formCheckResp = validateLength();
        if (formCheckResp.getCode() != 200) {
            return formCheckResp;
        }
        return validateRange();
    }
}
