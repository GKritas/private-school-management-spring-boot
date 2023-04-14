package com.gkritas.privateschoolmanager.assembler;

import com.gkritas.privateschoolmanager.controller.TrainerController;
import com.gkritas.privateschoolmanager.model.Trainer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class TrainerModelAssembler implements RepresentationModelAssembler<Trainer, EntityModel<Trainer>> {
    @Override
    public EntityModel<Trainer> toModel(Trainer trainer) {
        Link selfLink = linkTo(methodOn(TrainerController.class).getSingleTrainer(trainer.getId())).withSelfRel();
        Link allLink = linkTo(methodOn(TrainerController.class).getAllTrainers()).withRel("trainers");

        return EntityModel.of(trainer, selfLink);
    }
}
