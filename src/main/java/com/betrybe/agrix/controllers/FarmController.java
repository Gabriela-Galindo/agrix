package com.betrybe.agrix.controllers;

import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.entities.Farm;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller do Farm.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;

  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Farm createFarm(@RequestBody Farm farm) {
    return farmService.createFarm(farm);
  }

  @GetMapping
  public List<Farm> getAllFarms() {
    return farmService.getAllFarms();
  }

  /**
   * Rota Get farm by id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getFarmById(@PathVariable Long id) {
    Optional<Farm> farm = farmService.getFarmById(id);

    if (farm.isEmpty()) {
      String response = "Fazenda não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    return ResponseEntity.ok(farm.get());
  }

  @Autowired
  private CropService cropService;

  /**
   * Rota de criação do Crop.
   */
  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> createCrop(@PathVariable Long farmId, @RequestBody Crop crop) {
    Optional<Farm> farm = farmService.getFarmById(farmId);

    if (farm.isEmpty()) {
      String response = "Fazenda não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    Crop newCrop = cropService.createCrop(crop, farm.get());
    return ResponseEntity.status(HttpStatus.CREATED).body(newCrop);
  }

  /**
   * Rota getCropsByFarmId.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<?> getCropsByFarmId(@PathVariable Long farmId) {
    Optional<Farm> farm = farmService.getFarmById(farmId);
    if (farm.isEmpty()) {
      String response = "Fazenda não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    List<Crop> crops = cropService.getCropByFarmdId(farm.get());
    return ResponseEntity.ok(crops);
  }
}
