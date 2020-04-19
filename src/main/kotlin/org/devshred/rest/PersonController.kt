package org.devshred.rest

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus

@Controller
class PersonController(private val service: PersonService) {
    @GetMapping("/all")
    fun all(): Iterable<Person> = service.findAll()

    @GetMapping("/{uuid}")
    fun find(@PathVariable("uuid") id: Int) = service.findById(id)

    @PostMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun create(@RequestBody person: Person) = service.create(person)

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("uuid") id: Int) = service.deleteById(id)

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAll() = service.deleteAll()
}
