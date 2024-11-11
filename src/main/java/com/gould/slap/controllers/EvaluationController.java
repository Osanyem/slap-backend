package com.gould.slap.controllers;

import com.gould.slap.domain.EvaluationEntity;
import com.gould.slap.services.EvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping(path = "/evaluations")
    public ResponseEntity<EvaluationEntity> createEvaluation(@RequestBody EvaluationEntity evaluationEntity) {
        EvaluationEntity createdEvaluation = evaluationService.createEvaluation(evaluationEntity);
        return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
    }

    @GetMapping(path = "/evaluations")
    public List<EvaluationEntity> getAllEvaluations() {
        return evaluationService.findAllEvaluations();
    }

    @GetMapping("/evaluations/{id}")
    public ResponseEntity<EvaluationEntity> getEvaluationById(@PathVariable("id") Long id) {
        Optional<EvaluationEntity> evaluation = evaluationService.findEvaluationById(id);
        return evaluation.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/evaluations/{id}")
    public ResponseEntity<EvaluationEntity> updateEvaluation(@PathVariable("id") Long id, @RequestBody EvaluationEntity evaluationEntity) {
        if (!evaluationService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        evaluationEntity.setEvaluationId(id);
        EvaluationEntity updatedEvaluation = evaluationService.saveEvaluation(evaluationEntity);
        return new ResponseEntity<>(updatedEvaluation, HttpStatus.OK);
    }

    @PatchMapping("/evaluations/{id}")
    public ResponseEntity<EvaluationEntity> partialUpdateEvaluation(@PathVariable("id") Long id, @RequestBody EvaluationEntity evaluationEntity) {
        if (!evaluationService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        EvaluationEntity updatedEvaluation = evaluationService.partialUpdateEvaluation(id, evaluationEntity);
        return new ResponseEntity<>(updatedEvaluation, HttpStatus.OK);
    }

    @DeleteMapping("/evaluations/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable("id") Long id) {
        if (!evaluationService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        evaluationService.deleteEvaluation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
