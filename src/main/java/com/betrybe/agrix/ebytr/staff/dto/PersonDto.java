package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * DTO do person.
 */
public record PersonDto(String username, String password, Role role) {
  /**
   * Dto fromPersonEntity.
   */
  public static PersonDto fromPersonEntity(Person person) {
    return new PersonDto(
            person.getUsername(),
            person.getPassword(),
            person.getRole()
    );
  }

  /**
   * Dto person toEntity.
   */
  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);
    return person;
  }
}
