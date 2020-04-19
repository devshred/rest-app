package org.devshred.rest

import org.testcontainers.containers.PostgreSQLContainer

const val postgreSqlDockerImageName = "postgres:11.4"
const val postgreSqlPort = 5432

class MyPostgreSQLContainer(db: String,
                            username: String,
                            password: String,
                            private val propertiesPrefix: String) :
        PostgreSQLContainer<MyPostgreSQLContainer>(postgreSqlDockerImageName) {

    init {
        super.withExposedPorts(postgreSqlPort)
                .withDatabaseName(db)
                .withUsername(username)
                .withPassword(password)
    }

    override fun start() {
        super.start()

        System.setProperty("$propertiesPrefix.url", jdbcUrl)
        System.setProperty("$propertiesPrefix.username", username)
        System.setProperty("$propertiesPrefix.password", password)
    }
}
