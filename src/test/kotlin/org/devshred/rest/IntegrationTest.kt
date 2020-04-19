package org.devshred.rest

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@ExtendWith(value = [WithDbContainerExtension::class])
@SpringBootTest
@AutoConfigureMockMvc
abstract class IntegrationTest