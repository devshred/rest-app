package org.devshred.rest

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

class WithDbContainerExtension : BeforeAllCallback, AfterAllCallback {

    private val dbContainer: MyPostgreSQLContainer = MyPostgreSQLContainer(
            "test_db",
            "test_user",
            "test_password",
            "spring.datasource")

    override fun beforeAll(context: ExtensionContext?) {
        dbContainer.start()
    }

    override fun afterAll(context: ExtensionContext?) {
    }
}
