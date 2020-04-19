package org.devshred.rest

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        @Column(nullable = false)
        val name: String
) {
    override fun toString(): String {
        return "PersonEntity(id=$id, name=$name)"
    }
}