package com.jeyofdev.spring_dto_mapper.domain.actor;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;

    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public Actor findById(Long actorId) {
        return actorRepository.findById(actorId).orElseThrow(() -> new EntityNotFoundException("Actor with id " + actorId + " cannot be found"));
    }

    public Actor updateById(Long actorId, Actor updateActorData) {
        Actor currentActor = findById(actorId);

        currentActor.setName(updateActorData.getName());
        currentActor.setCountry(updateActorData.getCountry());
        currentActor.setAge(updateActorData.getAge());
        currentActor.setGender(updateActorData.getGender());
        currentActor.setBiography(updateActorData.getBiography());

        return actorRepository.save(currentActor);
    }

    public String deleteById(Long actorId) {
        actorRepository.deleteById(actorId);
        return "Actor with id " + actorId + " deleted";
    }
}
