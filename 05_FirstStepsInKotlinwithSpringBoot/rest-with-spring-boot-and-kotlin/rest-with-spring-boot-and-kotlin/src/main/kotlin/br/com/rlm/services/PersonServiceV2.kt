package br.com.rlm.services

import br.com.rlm.data.vo.v1.PersonVO
import br.com.rlm.data.vo.v2.PersonVOv2
import br.com.rlm.exceptions.ResourceNotFoundException
import br.com.rlm.mapper.DozerMapper
import br.com.rlm.mapper.custom.PersonMapper
import br.com.rlm.model.Person
import br.com.rlm.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonServiceV2 {

    @Autowired
    private lateinit var repository: PersonRepository

    @Autowired
    private lateinit var mapper: PersonMapper

    private val logger = Logger.getLogger((PersonServiceV2::class.java.name))

    fun findAll(): List<PersonVO> {
        logger.info("Finding all people!")
        val persons = repository.findAll()
        return DozerMapper.parseListObjects(persons, PersonVO::class.java)
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person!")
        var person = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Mo records found for this ID!") }
        return DozerMapper.parseObject(person, PersonVO::class.java)

    }

    fun create(person: PersonVOv2): PersonVOv2 {
        logger.info("Create one person with name ${person.firstName}")
        var entity: Person = mapper.mapVoToEntity(person)
        return mapper.mapEntityToVo(repository.save(entity))
    }

    fun update(person: PersonVO): PersonVO {
        logger.info("Update person data ${person.id}")
        val entity = repository.findById(person.id)
            .orElseThrow { ResourceNotFoundException("Mo records found for this ID!") }

        person.firstName = person.firstName
        person.lastName = person.lastName
        person.address = person.address
        person.gender = person.gender

        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long) {
        logger.info("delete person $id")
        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Mo records found for this ID!") }
        repository.delete(entity)

    }

}




