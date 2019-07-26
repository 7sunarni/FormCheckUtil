package parser;


import annotation.FormField;
import annotation.FormType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import response.FormCheckResp;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

/**
 * Model:
 * Description:
 * Author: 张立新
 * created: 2019/7/23
 */
public class FormParser {

    private Class clazz;
    private Object object;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void formValidate(Object o) {
        this.object = o;
        clazz = o.getClass();

        if (!validateAnnotation(clazz, FormType.class)) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!validateAnnotation(field, FormField.class)) {
                continue;
            }
            FormFieldParser parser = new FormFieldParser(this.object, field);
            FormCheckResp validate = parser.validate();
            System.out.println(validate.toString());
        }
    }

    static boolean validateAnnotation(AnnotatedElement element, Class clazz) {
        return element.getAnnotation(clazz) != null;
    }
}