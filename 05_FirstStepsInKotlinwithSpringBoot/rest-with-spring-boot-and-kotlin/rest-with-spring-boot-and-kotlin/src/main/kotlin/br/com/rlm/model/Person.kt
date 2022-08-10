package br.com.rlm.model

import jakarta.persistence.*

@Entity
@Table(name = "person")
data class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "first_name", nullable = false, length = 30)
    var firstName: String = "",

    @Column(name = "last_name", nullable = false, length = 30)
    var lastName: String = "",

    @Column(nullable = false, length = 50)
    var address: String = "",

    @Column(nullable = false, length = 6)
    var gender: String = ""

)