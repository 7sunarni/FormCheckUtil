package parser;


import annotation.FormField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import response.FormCheckResp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static response.FormCheckResp.LENGTH_OVERFLOW;
import static response.FormCheckResp.RANGE_OVERFLOW;

/**
 * Model:
 * Description:
 * Author: 张立新
 * created: 2019/7/23
 */
public class FormFieldParser {

    private static Logger logger = LoggerFactory.getLogger(FormFieldParser.class);

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
                annotationName = this.field.getName();
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
            return new FormCheckResp(this._code, String.format(LENGTH_OVERFLOW, this._name, this._length));
        } else {
            return new FormCheckResp(200, "OK");
        }
    }

    private FormCheckResp validateRange() {
        if (_range.length < 2) {
            return new FormCheckResp(200, FormCheckResp.ANNOTATION_ERROR);
        }
        Double doubleValue;
        try {
            doubleValue = Double.valueOf(_fieldValue.toString());
        } catch (Exception e) {
            doubleValue = new Double(0);
        }

        if (doubleValue < _range[0] || doubleValue > _range[1]) {
            return new FormCheckResp(this._code, String.format(RANGE_OVERFLOW, this._name, _range[0], _range[1]));
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
