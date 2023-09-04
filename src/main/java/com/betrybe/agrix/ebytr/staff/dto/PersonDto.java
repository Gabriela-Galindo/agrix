package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * DTO do person.
 */
public record PersonDto(Long id, String username, String password, Role role) {
  public Person toEntity() {
    return new Person(id, username, password, role);
  }
}
