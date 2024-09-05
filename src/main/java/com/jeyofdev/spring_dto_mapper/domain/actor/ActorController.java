package com.jeyofdev.spring_dto_mapper.domain.actor;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    /*@PostConstruct
    public void init() {
        Actor actor1 = new Actor(1L, "Al Pacino", "USA", 84, "male", "Alfredo James Pacino (born April 25, 1940) is an American actor and filmmaker.");
        Actor actor2 = new Actor(2L, "John Travolta", "USA", 70, "male", "John Joseph Travolta (born February 18, 1954) is an American actor, film producer, dancer, and singer.");
        Actor actor3 = new Actor(3L, "Uma Thurman", "USA", 54, "female", "Uma Karuna Thurman (née le 29 avril 1970) est une actrice et mannequin américaine.");

        actorService.save(actor1);
        actorService.save(actor2);
        actorService.save(actor3);
    }*/

    @PostMapping
    public ResponseEntity<Actor> addNewActor(@RequestBody Actor newActor) {
        Actor createdActor = actorService.save(newActor);
        return new ResponseEntity<>(createdActor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> actorList = actorService.findAll();
        return new ResponseEntity<>(actorList, HttpStatus.OK);
    }

    @GetMapping("/{actorId}")
    public ResponseEntity<Actor> getActor(@PathVariable("actorId") Long actorId) {
        Actor actor = actorService.findById(actorId);
        return new ResponseEntity<>(actor, HttpStatus.FOUND);
    }

    @PutMapping("/{actorId}")
    public ResponseEntity<Actor> updateActor(@PathVariable("actorId") Long actorId, @RequestBody Actor newActorData) {
        Actor createdActor = actorService.updateById(actorId, newActorData);
        return new ResponseEntity<>(createdActor, HttpStatus.OK);
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity<String> deleteActor(@PathVariable("actorId") Long actorId) {
        String confirm = actorService.deleteById(actorId);
        return new ResponseEntity<>(confirm, HttpStatus.OK);
    }
}
