// @file:Suppress("unused")

package at.altin.passwordsafe.beta.annotation.client
//ACHTUNG, DIENT NUR ALS DOKUMENTATION
//ALTIN IT SOLUTIONS CLIENTS SIND GEWÖHNLICH
//IN KOTLIN IMPLEMENTIERT DAHER ws.kts file
/**
 * Validation Example with Custom Annotation
 * ========================================
 * In this script, we demonstrate how to use the custom @ValidateWith annotation to mark a class
 * for validation using a validator class.
 */

import at.altin.passwordsafe.beta.annotation.validator.annotation.ValidateWith
import at.altin.passwordsafe.beta.annotation.validator.modelvalidator.PasswordValidator

/**
 * PasswordInfo class with @ValidateWith annotation
 */
@ValidateWith(PasswordValidator::class)
class ValidatedPasswordInfo(val name: String, val password: String)

fun main() {
    // Create an instance of ValidatedPasswordInfo
    val validatedInfo = ValidatedPasswordInfo("exampleUser", "Password123")
}
