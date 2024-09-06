package com.jeyofdev.spring_dto_mapper.domain.actor;

import com.jeyofdev.spring_dto_mapper.domain.actor.dto.ActorDTO;
import com.jeyofdev.spring_dto_mapper.domain.actor.dto.SaveActorDTO;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<ActorDTO> addNewActor(@Valid @RequestBody SaveActorDTO saveActorDTO) {
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
