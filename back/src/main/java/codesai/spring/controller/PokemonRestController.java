package codesai.spring.controller;

import java.util.List;

import codesai.spring.model.Pokemon;
import codesai.spring.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PokemonRestController {

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonRestController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

	@GetMapping("/pokemons")
	public List getPokemons() {
		return pokemonRepository.list();
	}

    @GetMapping("/pokemons/{id}")
    public ResponseEntity getPokemon(@PathVariable("id") String id) {

        Pokemon pokemon = pokemonRepository.get(id);
        if (pokemon == null) {
            return new ResponseEntity("No pokemon found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(pokemon, HttpStatus.OK);
    }

    @PostMapping("/pokemons")
    public ResponseEntity createPokemon(@RequestBody Pokemon pokemon) {
        if (alreadyHaveTenFavorites() && pokemon.isFavorite()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (pokemon.isNotValid()) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        pokemonRepository.create(pokemon);

        return new ResponseEntity(pokemon, HttpStatus.OK);
    }

    @PutMapping("/pokemons/{id}")
    public ResponseEntity updatePokemon(@PathVariable String id, @RequestBody Pokemon pokemon) {
        if (pokemon.isNotValid()) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Pokemon beforeUpdate = pokemonRepository.get(id);
        if (null == beforeUpdate) return new ResponseEntity("No pokemon found for ID " + id, HttpStatus.NOT_FOUND);
        if (alreadyHaveTenFavorites() && pokemon.isFavorite() && !beforeUpdate.isFavorite()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        pokemon = pokemonRepository.update(id, pokemon);
        return new ResponseEntity(pokemon, HttpStatus.OK);
    }

    @DeleteMapping("/pokemons/{id}")
    public ResponseEntity deletePokemon(@PathVariable String id) {

        if (null == pokemonRepository.delete(id)) {
            return new ResponseEntity("No pokemon found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);

    }

    @PutMapping("/pokemons/{id}/favorite")
    public ResponseEntity setAsFavorite(@PathVariable String id) {
        if (alreadyHaveTenFavorites()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Pokemon pokemon = pokemonRepository.get(id);
        if (pokemon == null) {
            return new ResponseEntity("No pokemon found for ID " + id, HttpStatus.NOT_FOUND);
        }

        pokemon.setFavorite(!pokemon.isFavorite());
        pokemonRepository.update(pokemon.getId(), pokemon);

        return new ResponseEntity(HttpStatus.OK);
    }

    private boolean alreadyHaveTenFavorites() {
        List<Pokemon> allPokemons = pokemonRepository.list();
        int numberOfFavourites = 0;
        for (Pokemon pokemon : allPokemons) {
            if (pokemon.isFavorite()) numberOfFavourites++;
            if (numberOfFavourites == 10) return true;
        }
        return false;
    }
}
