package com.gkritas.privateschoolmanager.assembler;

import com.gkritas.privateschoolmanager.DTO.TrainerDTO;
import com.gkritas.privateschoolmanager.controller.CourseController;
import com.gkritas.privateschoolmanager.model.Trainer;
import com.gkritas.privateschoolmanager.controller.TrainerController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class TrainerModelAssembler implements RepresentationModelAssembler<TrainerDTO, EntityModel<TrainerDTO>> {
    @Override
    public EntityModel<TrainerDTO> toModel(TrainerDTO trainerDTO) {
        Link selfLink = linkTo(methodOn(TrainerController.class).getSingleTrainer(trainerDTO.getTrainerId())).withSelfRel();
        Link allLink = linkTo(methodOn(TrainerController.class).getAllTrainers()).withRel("trainers");

        return EntityModel.of(trainerDTO, selfLink);
    }
}
