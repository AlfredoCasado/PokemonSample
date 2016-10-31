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
    PokemonRestController customerController = new PokemonRestController(inMemoryRepository);

    @Test
    public void return_all_customers() {
        List<Pokemon> customers = customerController.getPokemons();

        Assert.assertThat(customers.size(), is(3));
    }




}