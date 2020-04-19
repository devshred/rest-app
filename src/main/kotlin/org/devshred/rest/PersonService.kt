package org.devshred.rest

import org.springframework.stereotype.Service

@Service
class PersonService(private val repository: PersonRepository) {
    fun create(person: Person) = repository.save(person)
    fun findById(id: Int) = repository.findById(id)
    fun findAll(): Iterable<Person> = repository.findAll()
    fun deleteById(id: Int) = repository.deleteById(id)
    fun deleteAll() = repository.deleteAll()
}