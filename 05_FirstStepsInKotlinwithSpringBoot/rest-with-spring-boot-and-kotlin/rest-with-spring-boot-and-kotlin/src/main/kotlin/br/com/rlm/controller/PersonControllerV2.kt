package br.com.rlm.controller

import br.com.rlm.data.vo.v1.PersonVO
import br.com.rlm.data.vo.v2.PersonVOv2
import br.com.rlm.services.PersonService
import br.com.rlm.services.PersonServiceV2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person/v2")
class PersonControllerV2 {

    @Autowired
    private lateinit var service: PersonServiceV2

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(): List<PersonVO> {
        return service.findAll()
    }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(
        @PathVariable(value = "id") id: Long,
    ): PersonVO {
        return service.findById(id)
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun create(@RequestBody person: PersonVOv2): ResponseEntity<PersonVOv2> {
        service.create(person)
        return ResponseEntity.status(HttpStatus.CREATED).body(person)
    }

    @PutMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun update(@RequestBody person: PersonVO): PersonVO {
        return service.update(person)
    }

    @DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable(value = "id") id: Long): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()

    }
}