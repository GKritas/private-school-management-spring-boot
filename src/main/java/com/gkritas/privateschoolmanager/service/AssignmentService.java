package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.exception.AssignmentNotFoundException;
import com.gkritas.privateschoolmanager.model.Assignment;
import com.gkritas.privateschoolmanager.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public List<Assignment> findAllAssignment() {
        return assignmentRepository.findAll();

    }

    public Assignment findAssignmentById(Long id) {
        return assignmentRepository
                .findById(id)
                .orElseThrow(()-> new AssignmentNotFoundException("Assignment not found with id " + id));
    }

    public Assignment saveAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public void deleteAssignmentById(Long id) {
        Assignment deletedAssignment = findAssignmentById(id);
        assignmentRepository.delete(deletedAssignment);
    }

    public Assignment updateAssignment(Long id, Assignment assignment) {
        Assignment updatedAssignment = findAssignmentById(id);
        updatedAssignment.setName(assignment.getName());
        updatedAssignment.setDescription(assignment.getDescription());
        updatedAssignment.setDueDate(assignment.getDueDate());
        updatedAssignment.setMaximumScore(assignment.getMaximumScore());
        updatedAssignment.setActualScore(assignment.getActualScore());
        return assignmentRepository.save(updatedAssignment);
    }
}
