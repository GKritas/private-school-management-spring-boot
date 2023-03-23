package com.gkritas.privateschoolmanager.modelAssembler;

import com.gkritas.privateschoolmanager.DTO.TrainerDTO;
import com.gkritas.privateschoolmanager.controller.CourseController;
import com.gkritas.privateschoolmanager.domain.Trainer;
import com.gkritas.privateschoolmanager.controller.TrainerController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class TrainerModelAssembler implements RepresentationModelAssembler<Trainer, EntityModel<TrainerDTO>> {
    @Override
    public EntityModel<TrainerDTO> toModel(Trainer trainer) {
        TrainerDTO trainerDTO = new TrainerDTO(trainer);
        Link selfLink = linkTo(methodOn(TrainerController.class).getSingleTrainer(trainer.getTrainerId())).withSelfRel();
        Link courseLink = linkTo(methodOn(CourseController.class).getSingleCourse(trainer.getCourse().getCourseId())).withRel("course");

        return EntityModel.of(trainerDTO, selfLink, courseLink);
    }
}
