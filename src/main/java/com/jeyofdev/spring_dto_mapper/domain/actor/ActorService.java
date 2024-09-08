package com.jeyofdev.spring_dto_mapper.domain.actor;

import com.jeyofdev.spring_dto_mapper.common.AbstractDomainService;
import com.jeyofdev.spring_dto_mapper.domain.movie.Movie;
import com.jeyofdev.spring_dto_mapper.domain.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService extends AbstractDomainService<Actor> {
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ActorService(ActorRepository  actorRepository, MovieRepository movieRepository) {
        super(actorRepository, "actor");
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
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

    @Override
    public String deleteById(Long entityId) {
        Actor actor = findById(entityId);
        List<Movie> movieList = actor.getMovieList();

        for (Movie movie : movieList) {
            movie.getActorList().remove(actor);
            movieRepository.save(movie);
        }

        return super.deleteById(entityId);
    }
}
