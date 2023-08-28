package com.betrybe.agrix.controllers;

import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller do Crop.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private final CropService cropService;

  private final FertilizerService fertilizerService;

  /**
   * Construtor do Controller.
   */
  @Autowired
  public CropController(
          CropService cropService,
          FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  @GetMapping
  public List<Crop> getAllCrops() {
    return cropService.getAllCrops();
  }

  /**
   * Rota get by id do Crop.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getCropById(@PathVariable Long id) {
    Optional<Crop> crop = cropService.getCropById(id);

    if (crop.isEmpty()) {
      String response = "Plantação não encontrada!";
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    return ResponseEntity.ok(crop.get());
  }

  @GetMapping("/search")
  public ResponseEntity<List<Crop>> searchByHarvestDate(
          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate start,
          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate end
  ) {
    List<Crop> crops = cropService.searchByHarvestDate(start, end);
    return ResponseEntity.ok(crops);
  }
}
