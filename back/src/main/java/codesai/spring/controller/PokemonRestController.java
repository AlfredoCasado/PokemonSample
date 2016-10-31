package codesai.spring.controller;

import java.util.List;

import codesai.spring.model.Pokemon;
import codesai.spring.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}