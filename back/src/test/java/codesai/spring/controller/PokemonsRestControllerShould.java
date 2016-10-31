package codesai.spring.controller;

import codesai.spring.model.Pokemon;
import codesai.spring.repositories.PokemonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class PokemonsRestControllerShould {

    PokemonRepository inMemoryRepository = new PokemonRepository();
    PokemonRestController pokemonRestController = new PokemonRestController(inMemoryRepository);

    @Test
    public void return_all_customers() {
        List<Pokemon> customers = pokemonRestController.getPokemons();

        Assert.assertThat(customers.size(), is(3));
    }

    @Test
    public void return_an_existent_pokemon() {
        PokemonRepository.pokemons.add(new Pokemon("1", "pokemonName", "pokemonDescription"));

        ResponseEntity response = pokemonRestController.getPokemon("1");

        Assert.assertThat(((Pokemon) response.getBody()).getName(), is("pokemonName"));
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void return_not_found_when_there_is_no_podemon_with_this_id() {
        ResponseEntity response = pokemonRestController.getPokemon("badPodemonId");

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }






}