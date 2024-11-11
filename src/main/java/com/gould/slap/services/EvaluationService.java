package com.gould.slap.services;

import com.gould.slap.domain.EvaluationEntity;
import com.gould.slap.repositories.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public EvaluationEntity createEvaluation(EvaluationEntity evaluationEntity) {
        return evaluationRepository.save(evaluationEntity);
    }

    public List<EvaluationEntity> findAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public Optional<EvaluationEntity> findEvaluationById(Long id) {
        return evaluationRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return evaluationRepository.existsById(id);
    }

    public EvaluationEntity saveEvaluation(EvaluationEntity evaluationEntity) {
        return evaluationRepository.save(evaluationEntity);
    }

    public EvaluationEntity partialUpdateEvaluation(Long id, EvaluationEntity evaluationEntity) {
        return evaluationRepository.findById(id)
                .map(existingEvaluation -> {
                    if (evaluationEntity.getFeedback() != null) {
                        existingEvaluation.setFeedback(evaluationEntity.getFeedback());
                    }
                    if (evaluationEntity.getGrade() != 0) {
                        existingEvaluation.setGrade(evaluationEntity.getGrade());
                    }
                    return evaluationRepository.save(existingEvaluation);
                })
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }

    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }
}
