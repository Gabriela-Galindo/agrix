package com.betrybe.agrix.controllers;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.entity.PersonResponse;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de Person.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
  * Rota post do person.
  */
  @PostMapping
  public ResponseEntity<PersonResponse> createPerson(@RequestBody Person person) {
    Person createdPerson = personService.create(person);

    PersonResponse personResponse = new PersonResponse(createdPerson);

    return ResponseEntity.status(HttpStatus.CREATED).body(personResponse);
  }
}
