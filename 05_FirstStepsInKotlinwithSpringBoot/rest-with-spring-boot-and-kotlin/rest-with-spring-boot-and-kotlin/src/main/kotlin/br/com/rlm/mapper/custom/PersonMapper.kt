package br.com.rlm.mapper.custom

import br.com.rlm.data.vo.v2.PersonVOv2
import br.com.rlm.model.Person
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonMapper {

    fun mapEntityToVo(person: Person): PersonVOv2 {
        val vo = PersonVOv2()
        vo.id = person.id
        vo.address = person.address
        vo.birthDay = Date()
        vo.firstName = person.firstName
        vo.lastName = person.lastName
        vo.gender = person.gender
        return vo
    }

    fun mapVoToEntity(person: PersonVOv2): Person {
        val entity = Person()
        entity.id = person.id
        entity.address = person.address
        //entity.birthDay = Date()
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.gender = person.gender
        return entity
    }
}