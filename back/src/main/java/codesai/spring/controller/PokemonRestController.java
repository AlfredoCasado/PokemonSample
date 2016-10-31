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
    public PokemonRestController(PokemonRepository customerRepository) {
        this.pokemonRepository = customerRepository;
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
        if (pokemon.isNotValid()) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        pokemonRepository.create(pokemon);

        return new ResponseEntity(pokemon, HttpStatus.OK);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity updatePokemon(@PathVariable String id, @RequestBody Pokemon pokemon) {
        if (pokemon.isNotValid()) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        pokemon = pokemonRepository.update(id, pokemon);

        if (null == pokemon) return new ResponseEntity("No pokemon found for ID " + id, HttpStatus.NOT_FOUND);

        return new ResponseEntity(pokemon, HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity deletePokemon(@PathVariable String id) {

        if (null == pokemonRepository.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

}