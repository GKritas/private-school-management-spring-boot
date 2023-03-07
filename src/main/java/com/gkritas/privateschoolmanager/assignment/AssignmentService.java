package com.gkritas.privateschoolmanager.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public List<Assignment> findAllAssignment() {
        return assignmentRepository.findAll();
    }

    public Assignment findAssignmentById(UUID assignmentId) {
        return assignmentRepository.findById(assignmentId).orElseThrow(()-> new AssignmentNotFoundException("Assignment not found with id " + assignmentId));
    }

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public void deleteAssignmentById(UUID assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }

    public Assignment updateAssignment(UUID assignmentId, Assignment assignment) {
        Assignment updatedAssignment = assignmentRepository.findById(assignmentId)
                .map(asmt -> {
                    asmt.setName(assignment.getName());
                    asmt.setDescription(assignment.getDescription());
                    asmt.setDueDate(assignment.getDueDate());
                    asmt.setMaximumScore(assignment.getMaximumScore());
                    asmt.setActualScore(assignment.getActualScore());
                    return asmt;
                })
                .orElseGet(()-> {
                    assignment.setAssignmentId(assignmentId);
                    return assignment;
                });

        return assignmentRepository.save(updatedAssignment);
    }
}
