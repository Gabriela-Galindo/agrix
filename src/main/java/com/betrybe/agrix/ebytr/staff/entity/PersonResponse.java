package com.betrybe.agrix.ebytr.staff.entity;

/**
 * Entity do PersonResponse.
 */
public class PersonResponse {
  private Long id;
  private String username;
  private String role;

  /**
  * Construtor.
  */
  public PersonResponse(Person person) {
    this.id = person.getId();
    this.username = person.getUsername();
    this.role = person.getRole().name();
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getRole() {
    return role;
  }
}
