//package at.altin.passwordsafe.beta.annotation.validator.annotation;
//
//import at.altin.passwordsafe.beta.annotation.validator.Validator;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.InvocationTargetException;
//
///**
// * Aspect for validation <br>
// * It is used for the custom annotation {@link ValidateWith} <br>
// * it runs validate method of the validator class after the method annotated with {@link ValidateWith} is executed
// *
// * unfortunately it is not used in the project, since we do not want to have spring in the project <br>
// * therefore we have commented out the code, we just want to show that we know how to use it
// * @see Validator
// */
//@Aspect
//@Component
//public class ValidationAspect {
//
//    @AfterReturning(pointcut = "@annotation(validateWith)", returning = "result")
//    public void validate(JoinPoint joinPoint, ValidateWith validateWith, Object result) {
//        Class<? extends Validator<?>> validatorClass = validateWith.value();
//        if (result != null && validatorClass != null) {
//            Validator<Object> validator = instantiateValidator(validatorClass);
//            validator.validate(result);
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private <T> Validator<T> instantiateValidator(Class<? extends Validator<?>> validatorClass) {
//        try {
//            return (Validator<T>) validatorClass.getDeclaredConstructor().newInstance();
//        } catch (InstantiationException | IllegalAccessException e) {
//            throw new RuntimeException("Error instantiating validator", e);
//
//        } catch (InvocationTargetException | NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
