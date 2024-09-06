package com.jeyofdev.spring_dto_mapper.domain.actor;

import com.jeyofdev.spring_dto_mapper.common.AbstractDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService extends AbstractDomainService<Actor> {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository  actorRepository) {
        super(actorRepository, "actor");
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor updateById(Long entityId, Actor updateEntityData) {
        Actor currentActor = findById(entityId);

        currentActor.setName(updateEntityData.getName());
        currentActor.setCountry(updateEntityData.getCountry());
        currentActor.setAge(updateEntityData.getAge());
        currentActor.setGender(updateEntityData.getGender());
        currentActor.setBiography(updateEntityData.getBiography());

        return actorRepository.save(currentActor);
    }
}
