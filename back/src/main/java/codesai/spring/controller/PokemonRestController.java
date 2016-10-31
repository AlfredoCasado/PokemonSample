package codesai.spring.controller;

import java.util.List;

import codesai.spring.model.Pokemon;
import codesai.spring.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}