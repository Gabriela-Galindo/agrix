package com.betrybe.agrix.controllers;

import com.betrybe.agrix.ebytr.staff.dto.AuthenticationDto;
import com.betrybe.agrix.ebytr.staff.dto.TokenDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller do Auth.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;

  @Autowired
  public AuthenticationController(
        AuthenticationManager authenticationManager,
        TokenService tokenService
  ) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
  * Rota post do Login.
  */
  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody AuthenticationDto authenticationDto) {
    UsernamePasswordAuthenticationToken usernamePassword =
              new UsernamePasswordAuthenticationToken(
                  authenticationDto.username(), authenticationDto.password()
              );
    Authentication authentication = authenticationManager
            .authenticate(usernamePassword);
    Person person = (Person) authentication.getPrincipal();
    String token = tokenService.generateToken(person);

    return ResponseEntity.status(HttpStatus.OK).body(TokenDto.formated(token));
  }
}
