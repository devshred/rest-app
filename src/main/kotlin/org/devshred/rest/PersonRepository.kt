package org.devshred.rest

import org.springframework.data.repository.CrudRepository

interface PersonRepository : CrudRepository<Person, Int>