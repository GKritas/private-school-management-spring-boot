package com.gkritas.privateschoolmanager.modelAssembler;

import com.gkritas.privateschoolmanager.domain.Trainer;
import com.gkritas.privateschoolmanager.controller.TrainerController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class TrainerModelAssembler implements RepresentationModelAssembler<Trainer, EntityModel<Trainer>> {
    @Override
    public EntityModel<Trainer> toModel(Trainer trainer) {
        return EntityModel.of(trainer,
                WebMvcLinkBuilder.linkTo(methodOn(TrainerController.class).getSingleTrainer(trainer.getTrainerId())).withSelfRel(),
                linkTo(methodOn(TrainerController.class).getAllTrainers()).withRel("trainers"));
    }
}
