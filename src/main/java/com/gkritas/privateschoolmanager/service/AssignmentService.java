package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.domain.Assignment;
import com.gkritas.privateschoolmanager.exception.AssignmentNotFoundException;
import com.gkritas.privateschoolmanager.repository.AssignmentRepository;
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

    public Assignment findAssignmentById(Long assignmentId) {
        return assignmentRepository
                .findById(assignmentId)
                .orElseThrow(()-> new AssignmentNotFoundException("Assignment not found with id " + assignmentId));
    }

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public void deleteAssignmentById(Long assignmentId) {
        Assignment assignment = findAssignmentById(assignmentId);
        assignmentRepository.delete(assignment);
    }

    public Assignment updateAssignment(Long assignmentId, Assignment assignment) {
        Assignment updatedAssignment = findAssignmentById(assignmentId);
        updatedAssignment.setName(assignment.getName());
        updatedAssignment.setDescription(assignment.getDescription());
        updatedAssignment.setDueDate(assignment.getDueDate());
        updatedAssignment.setMaximumScore(assignment.getMaximumScore());
        updatedAssignment.setActualScore(assignment.getActualScore());
        updatedAssignment.setCourse(assignment.getCourse());
        updatedAssignment.setStudents(assignment.getStudents());
        return assignmentRepository.save(updatedAssignment);
    }
}
