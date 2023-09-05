package com.betrybe.agrix.ebytr.staff.dto;

/**
 * DTO do token.
 */
public record TokenDto(String token) {
  public static TokenDto formated(String token) {
    return new TokenDto(token);
  }
}
