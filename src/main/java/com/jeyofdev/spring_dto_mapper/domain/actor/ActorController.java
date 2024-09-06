package com.jeyofdev.spring_dto_mapper.domain.actor;

import com.jeyofdev.spring_dto_mapper.domain.actor.dto.ActorDTO;
import com.jeyofdev.spring_dto_mapper.domain.actor.dto.SaveActorDTO;
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
    private final ActorMapper actorMapper;

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
    public ResponseEntity<ActorDTO> addNewActor(@RequestBody SaveActorDTO saveActorDTO) {
        Actor actor = actorMapper.mapToEntity(saveActorDTO);
        Actor createdActor = actorService.save(actor);
        ActorDTO actorDTO = actorMapper.mapFromEntity(createdActor, true);

        return new ResponseEntity<>(actorDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ActorDTO>> getAllActors() {
        List<Actor> actorList = actorService.findAll();
        List<ActorDTO> actorListDTO = actorList.stream().map(actor -> actorMapper.mapFromEntity(actor, false)).toList();

        return new ResponseEntity<>(actorListDTO, HttpStatus.OK);
    }

    @GetMapping("/{actorId}")
    public ResponseEntity<ActorDTO> getActor(@PathVariable("actorId") Long actorId) {
        Actor actor = actorService.findById(actorId);
        ActorDTO actorDTO = actorMapper.mapFromEntity(actor, true);

        return new ResponseEntity<>(actorDTO, HttpStatus.FOUND);
    }

    @PutMapping("/{actorId}")
    public ResponseEntity<ActorDTO> updateActor(@PathVariable("actorId") Long actorId, @RequestBody SaveActorDTO saveActorDTO) {
        Actor actor = actorMapper.mapToEntity(saveActorDTO);
        Actor updatedActor = actorService.updateById(actorId, actor);
        ActorDTO actorDTO = actorMapper.mapFromEntity(updatedActor, true);

        return new ResponseEntity<>(actorDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{actorId}")
    public ResponseEntity<String> deleteActor(@PathVariable("actorId") Long actorId) {
        String confirm = actorService.deleteById(actorId);
        return new ResponseEntity<>(confirm, HttpStatus.OK);
    }
}
