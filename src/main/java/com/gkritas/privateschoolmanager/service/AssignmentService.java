package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.DTO.AssignmentDTO;
import com.gkritas.privateschoolmanager.model.Assignment;
import com.gkritas.privateschoolmanager.exception.AssignmentNotFoundException;
import com.gkritas.privateschoolmanager.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public List<AssignmentDTO> findAllAssignment() {
        List<Assignment> assignments = assignmentRepository.findAll();
        return assignments.stream().map(AssignmentDTO::new).collect(Collectors.toList());
    }

    public AssignmentDTO findAssignmentById(Long assignmentId) {
        Assignment assignment = assignmentRepository
                .findById(assignmentId)
                .orElseThrow(()-> new AssignmentNotFoundException("Assignment not found with id " + assignmentId));
        return new AssignmentDTO(assignment);
    }

    public AssignmentDTO createAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();
        assignment.setName(assignmentDTO.getName());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setDueDate(assignmentDTO.getDueDate());
        assignment.setMaximumScore(assignmentDTO.getMaximumScore());
        assignment.setActualScore(assignmentDTO.getActualScore());
        assignmentRepository.save(assignment);

        return new AssignmentDTO(assignment);
    }

    public void deleteAssignmentById(Long assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }

    public AssignmentDTO updateAssignment(Long assignmentId, AssignmentDTO assignment) {
        Assignment updatedAssignment = assignmentRepository
                .findById(assignmentId)
                .orElseThrow(()-> new AssignmentNotFoundException("Assignment not found with id " + assignmentId));
        updatedAssignment.setName(assignment.getName());
        updatedAssignment.setDescription(assignment.getDescription());
        updatedAssignment.setDueDate(assignment.getDueDate());
        updatedAssignment.setMaximumScore(assignment.getMaximumScore());
        updatedAssignment.setActualScore(assignment.getActualScore());
        assignmentRepository.save(updatedAssignment);
        return new AssignmentDTO(updatedAssignment);
    }
}
