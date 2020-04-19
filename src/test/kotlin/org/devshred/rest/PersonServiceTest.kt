package org.devshred.rest

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito.`when`

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class PersonServiceTest {
    private val repository = mock<PersonRepository>()
    private val service = PersonService(repository)

    private val personCaptor = argumentCaptor<Person>()

    @BeforeAll
    fun initMocks() {
        `when`(repository.save(any<Person>())).thenAnswer { invocation ->
            (invocation.arguments[0] as Person).copy(id = 1)
        }
    }

    @Test
    fun `create a new person`() {
        val personName = "peter"
        val person = service.create(Person(name = personName))

        verify(repository).save(personCaptor.capture())
        assertThat(personCaptor.firstValue.name).isEqualTo(personName)
        assertThat(personCaptor.firstValue.id).isNull()

        assertThat(person.name).isEqualTo(personName)
        assertThat(person.id).isNotNull()
    }

    @Test
    fun `delete all entries`() {
        service.deleteAll()
        verify(repository).deleteAll()
    }
}