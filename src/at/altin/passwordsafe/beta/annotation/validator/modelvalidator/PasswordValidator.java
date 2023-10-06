package at.altin.passwordsafe.beta.annotation.validator.modelvalidator;

import at.altin.passwordsafe.PasswordInfo;
import at.altin.passwordsafe.beta.annotation.validator.Validator;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
// unfortunately it is not used in the project, since we do not want to have spring in the project
// therefore we have commented out the code, we just want to show that we know how to use it
//
//@Component
//@Log4j2
public class PasswordValidator implements Validator<PasswordInfo> {
    @Override
    public void validate(PasswordInfo object) {
        // TODO Auto-generated method stub, do not implement for PasswordSafeConsole Project
    }
//
//    /**
//     * Validates the user data
//     *
//     * @param info the object to validate
//     */
//    @Override
//    public void validate(PasswordInfo info) {
//
//        log.info("Validating passwordInfo: " + info.toString());
//        List<ErrorMessage> errors = new ArrayList<>();
//
//        if (info.getName() == null) {
//            log.error("Name must not be null");
//            errors.add(new ErrorMessage("Name must not be null"));
//        }
//
//        if (!errors.isEmpty()) {
//            throw new ValidationException(errors);
//        }
//
//        log.info("PasswordInfo validated successfully");
//
//
//    }
}
