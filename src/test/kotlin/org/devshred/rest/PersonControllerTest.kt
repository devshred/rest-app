package org.devshred.rest

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class PersonControllerTest {
    private lateinit var mockMvc: MockMvc
    private val service = mock<PersonService>()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(PersonController(service)).build()
    }

    @Test
    fun `truncate database`() {
        mockMvc.perform(delete("/")).andExpect(MockMvcResultMatchers.status().isNoContent)
        verify(service).deleteAll()
    }
}