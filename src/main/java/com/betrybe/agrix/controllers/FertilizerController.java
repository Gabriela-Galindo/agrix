package com.betrybe.agrix.controllers;

import com.betrybe.agrix.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller do Fertilizer.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Fertilizer createFertilizer(@RequestBody Fertilizer fertilizer) {
    return fertilizerService.createFertilizer(fertilizer);
  }

  @GetMapping
  @Secured({"ADMIN"})
  public List<Fertilizer> getAllFertilizer() {
    return fertilizerService.getAllFertilizer();
  }

  /**
   * Rota getById do Fertilizer.
   */
  @GetMapping("{fertilizerId}")
  @Secured({"ADMIN"})
  public ResponseEntity<?> getFertilizerById(@PathVariable Long fertilizerId) {
    Optional<Fertilizer> fertilizer = fertilizerService.getFertilizerById(fertilizerId);

    if (fertilizer.isEmpty()) {
      String response = "Fertilizante não encontrado!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    return ResponseEntity.ok(fertilizer.get());
  }
}
