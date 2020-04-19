package org.devshred.rest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class PersonServiceIntegrationTest : IntegrationTest() {
    @Autowired
    private lateinit var repository: PersonRepository

    @Autowired
    private lateinit var serviceUnderTest: PersonService

    private val personName = "Peter"

    @BeforeEach
    fun setup() {
        repository.deleteAll()
    }

    @Test
    fun `truncate database`() {
        repository.save(Person(name = personName))
        assertThat(repository.findAll()).hasSize(1)

        serviceUnderTest.deleteAll()

        assertThat(repository.findAll()).isEmpty()
    }

    @Test
    fun `create person`() {
        serviceUnderTest.create(Person(name = personName))
        assertThat(repository.findAll().first().name).isEqualTo(personName)
        assertThat(repository.findAll().first().id).isNotNull()
    }
}