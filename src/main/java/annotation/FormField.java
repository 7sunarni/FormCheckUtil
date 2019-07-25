package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Model:
 * Description:
 * Author: 张立新
 * created: 2019/7/23
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FormField {

    String respName() default "";

    int respCode() default 201;

    float[] checkRange() default {};

    int checkLength() default Integer.MAX_VALUE;


}
