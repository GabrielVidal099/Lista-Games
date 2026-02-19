package com.example.games.controller;

import com.example.games.model.Game;
import com.example.games.repository.GameRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
// Adicione esta linha abaixo para liberar o acesso do seu site
@CrossOrigin(origins = "https://listagames.infinityfreeapp.com")
public class GamesController {

    private final GameRepository repository;

    public GamesController(GameRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Game> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Game getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game não encontrado: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game create(@RequestBody Game game) {
        game.setId(null);
        return repository.save(game);
    }

    @PutMapping("/{id}")
    public Game update(@PathVariable Long id, @Valid @RequestBody Game game) {
        Game existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game não encontrado: " + id));

        existing.setTitle(game.getTitle());
        existing.setDescription(game.getDescription());
        existing.setGenre(game.getGenre());
        existing.setDeveloper(game.getDeveloper());
        existing.setPublisher(game.getPublisher());
        existing.setReleaseDate(game.getReleaseDate());
        existing.setRating(game.getRating());
        existing.setActive(game.getActive());

        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}