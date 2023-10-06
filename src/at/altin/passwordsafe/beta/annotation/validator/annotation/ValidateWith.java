package at.altin.passwordsafe.beta.annotation.validator.annotation;

import at.altin.passwordsafe.beta.annotation.validator.Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * custom annotation for validation
 *+ this is the annotation Pattern for the custom annotation {@link ValidateWith}
 * unfortunately it is not used in the project, since we do not want to have spring in the project <br>
 * therefore we have commented out the code, we just want to show that we know how to use it
 * @see Validator
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateWith {

    Class<? extends Validator<?>> value();
}
