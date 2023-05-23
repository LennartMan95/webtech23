package de.htwberlin.webtech23.service;

import de.htwberlin.webtech23.persistence.PersonEntity;
import de.htwberlin.webtech23.persistence.PersonRepository;
import de.htwberlin.webtech23.web.api.Person;
import de.htwberlin.webtech23.web.api.PersonCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        List<PersonEntity> persons = personRepository.findAll();
        return persons.stream()
                .map(personEntity -> transformEntity(personEntity))
                .collect(Collectors.toList());
    }

    public Person create(PersonCreateRequest request) {
        var personEntity = new PersonEntity(request.getFirstName(), request.getLastName(), request.isVaccinated());
        personEntity = personRepository.save(personEntity);
        return transformEntity(personEntity);
    }

    private Person transformEntity(PersonEntity personEntity){
        return new Person(
                personEntity.getId(),
                personEntity.getFirstName(),
                personEntity.getLastName(),
                personEntity.isVaccinated()
        );
    }

}
