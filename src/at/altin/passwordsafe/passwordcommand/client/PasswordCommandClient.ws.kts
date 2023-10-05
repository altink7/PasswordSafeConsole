@file:Suppress("unused")

package at.altin.passwordsafe.passwordcommand.client

/**
 * Command Pattern in PasswordSafeEngine
 * =====================================
 * The Command Pattern is a behavioral design pattern that turns a request into a stand-alone object.
 * This object contains all the information about the request, allowing you to parameterize clients
 * with queues, requests, and operations. In this script, we demonstrate the Command Pattern's use
 * in the PasswordSafeEngine class.
 */

import at.altin.passwordsafe.PasswordInfo
import at.altin.passwordsafe.passwordcommand.command.StoreNewPasswordFileOperation
import at.altin.passwordsafe.passwordcommand.command.DeletePasswordFileOperation
import at.altin.passwordsafe.passwordcommand.invoker.PasswordFileOperationExecutor
import at.altin.passwordsafe.passwordcommand.receiver.PasswordFileOperationService

/**
 * PasswordSafeEngine
 * -----------------
 * The PasswordSafeEngine class exemplifies the Command Pattern, offering a structured approach to
 * managing password-related operations. It utilizes a PasswordInvoker to encapsulate and execute
 * different password commands.
 */
class PasswordSafeEngine {
    private val passwordInvoker = PasswordFileOperationExecutor()

    /**
     * addNewPassword
     * ------------
     * This method demonstrates the Command Pattern by creating a StoreNewPasswordFileOperation
     * and adding it to the PasswordInvoker. It allows the secure storage of a user's password.
     * @param info The PasswordInfo object containing the username and password.
     */
    fun addNewPassword(info: PasswordInfo) {
        val passwordOperationService = PasswordFileOperationService(info.name, null)
        val storeNewPasswordCommand = StoreNewPasswordFileOperation(passwordOperationService)
        passwordInvoker.addOperation(storeNewPasswordCommand)
    }

    /**
     * deletePassword
     * --------------
     * This method demonstrates the Command Pattern by creating a DeletePasswordFileOperation
     * and adding it to the PasswordInvoker. It allows the secure deletion of a user's password.
     * @param passwordName The name of the password to be deleted.
     */
    fun deletePassword(passwordName: String) {
        val passwordOperationService = PasswordFileOperationService(passwordName, null)
        val deletePasswordCommand = DeletePasswordFileOperation(passwordOperationService)
        passwordInvoker.addOperation(deletePasswordCommand)
    }
}

fun main() {
    // Create an instance of PasswordSafeEngine
    val passwordSafeEngine = PasswordSafeEngine()

    // Example usage: Save a password
    passwordSafeEngine.addNewPassword(PasswordInfo("a","b"))

    // Example usage: Delete a password
    passwordSafeEngine.deletePassword("exampleUser")
}
