package codesai.spring.controller;

import codesai.spring.model.Pokemon;
import codesai.spring.repositories.PokemonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

public class PokemonsRestControllerShould {

    private static final String validDescription = "una descripción sólo es valida si tiene más de 30 caracteres, menos es insuficiente para describir las virtudes de este podemon";

    PokemonRepository inMemoryRepository = new PokemonRepository();
    PokemonRestController pokemonRestController = new PokemonRestController(inMemoryRepository);

    @Before
    public void setUp() throws Exception {
        inMemoryRepository.pokemons.clear();
    }

    @Test
    public void return_all_pokemons() {
        inMemoryRepository.pokemons.add(new Pokemon("1", "pokemonName", validDescription,"electrico","", false, ""));
        inMemoryRepository.pokemons.add(new Pokemon("2", "pokemonName", validDescription,"electrico","", false, ""));

        List<Pokemon> pokemons = pokemonRestController.getPokemons();

        Assert.assertThat(pokemons.size(), is(2));
    }

    @Test
    public void return_an_existent_pokemon() {
        inMemoryRepository.pokemons.add(new Pokemon("1", "pokemonName", validDescription,"electrico","", false, ""));

        ResponseEntity response = pokemonRestController.getPokemon("1");

        Assert.assertThat(((Pokemon) response.getBody()).getName(), is("pokemonName"));
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void return_not_found_when_there_is_no_podemon_with_this_id() {
        ResponseEntity response = pokemonRestController.getPokemon("badPodemonId");

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void create_a_new_pokemon() {
        Pokemon pokemon = new Pokemon("pokemonid", "pokemonName", validDescription, "PrimaryType", "SecondaryType", true, "");

        pokemonRestController.createPokemon(pokemon);

        Assert.assertThat(inMemoryRepository.pokemons.size(), is(1));
    }

    @Test
    public void return_bad_request_when_try_to_create_a_pokemon_without_name() {
        Pokemon pokemon = new Pokemon("pokemonid", "", validDescription, "PrimaryType", "SecondaryType", true, "");

        ResponseEntity response = pokemonRestController.createPokemon(pokemon);

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void return_bad_request_when_try_to_create_a_pokemon_with_small_name() {
        Pokemon pokemon = new Pokemon("pokemonid", "a", validDescription, "PrimaryType", "SecondaryType", true, "");

        ResponseEntity response = pokemonRestController.createPokemon(pokemon);

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void return_bad_request_when_try_to_create_a_pokemon_with_large_name() {
        Pokemon pokemon = new Pokemon("pokemonid", "nombre con más de 24 caracteres, demasiado para un pokemon", validDescription, "PrimaryType", "SecondaryType", true, "");

        ResponseEntity response = pokemonRestController.createPokemon(pokemon);

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void return_bad_request_when_try_to_create_a_pokemon_with_a_invalid_description() {
        Pokemon pokemon = new Pokemon("pokemonid", "valid name", "invalid description", "PrimaryType", "SecondaryType", true, "");

        ResponseEntity response = pokemonRestController.createPokemon(pokemon);

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void return_bad_request_when_try_to_create_a_pokemon_without_primary_type() {
        Pokemon pokemon = new Pokemon("pokemonid", "valid name", validDescription, "", "SecondaryType", true, "");

        ResponseEntity response = pokemonRestController.createPokemon(pokemon);

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void update_a_pokemon() {
        Pokemon originalPokemon = new Pokemon("1", "pokemonName", validDescription,"electrico","", false, "");
        inMemoryRepository.pokemons.add(originalPokemon);

        Pokemon updatedPokemon = new Pokemon("1", "updatedName", validDescription,"electrico","", false, "");
        pokemonRestController.updatePokemon("1", updatedPokemon);

        Assert.assertThat(inMemoryRepository.get("1").getName(), is("updatedName"));
    }

    @Test
    public void return_not_found_when_try_to_edit_a_pokemon_that_does_not_exists() {
        ResponseEntity response = pokemonRestController.updatePokemon("1", new Pokemon("1", "pokemonName", validDescription,"electrico","", false, ""));

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void return_bad_request_when_try_to_update_a_pokemon_with_bad_description() {
        Pokemon originalPokemon = new Pokemon("1", "pokemonName", validDescription,"electrico","", false, "");
        inMemoryRepository.pokemons.add(originalPokemon);

        Pokemon updatedPokemon = new Pokemon("1", "updatedName", "invalid description","electrico","", false, "");
        ResponseEntity response = pokemonRestController.updatePokemon("1", updatedPokemon);

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void return_bad_request_when_try_to_edit_a_pokemon_withouth_primary_type() {
        Pokemon originalPokemon = new Pokemon("1", "pokemonName", validDescription,"electrico","", false, "");
        inMemoryRepository.pokemons.add(originalPokemon);

        Pokemon updatedPokemon = new Pokemon("1", "updatedName", "invalid description","","", false, "");
        ResponseEntity response = pokemonRestController.updatePokemon("1", updatedPokemon);

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void return_bad_request_when_try_to_update_a_pokemon_with_bad_name() {
        Pokemon originalPokemon = new Pokemon("1", "pokemonName", validDescription,"electrico","", false, "");
        inMemoryRepository.pokemons.add(originalPokemon);

        Pokemon updatedPokemon = new Pokemon("1", "", validDescription,"electrico","", false, "");
        ResponseEntity response = pokemonRestController.updatePokemon("1", updatedPokemon);

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void delte_a_pokemon() {
        inMemoryRepository.pokemons.add(new Pokemon("1", "pokemonName", validDescription,"electrico","", false, ""));

        ResponseEntity response = pokemonRestController.deletePokemon("1");

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
        Assert.assertThat(inMemoryRepository.pokemons.size(), is(0));
    }

    @Test
    public void return_no_found_when_try_to_delete_a_non_existent_pokemon() {
        ResponseEntity response = pokemonRestController.deletePokemon("nonExistentPokemon");

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void set_a_pokemon_as_one_of_my_favorites() {
        inMemoryRepository.pokemons.add(new Pokemon("1", "pokemonName", validDescription,"electrico","", false, ""));

        ResponseEntity response = pokemonRestController.setAsFavorite("1");

        Assert.assertThat(inMemoryRepository.get("1").isFavorite(), is(true));
    }

    @Test
    public void return_not_found_when_try_to_favorite_a_pokemon_that_not_exist() {
        ResponseEntity response = pokemonRestController.setAsFavorite("1");

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void return_bad_request_when_try_to_favorite_more_than_10_pokemons() {
        addTenPokemonsAsFavorites();
        inMemoryRepository.pokemons.add(new Pokemon("11", "pokemonName", validDescription,"electrico","", false, ""));

        ResponseEntity response = pokemonRestController.setAsFavorite("1");

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    private void addTenPokemonsAsFavorites() {
        for (int i = 0; i<10; i++) {
            inMemoryRepository.pokemons.add(new Pokemon(java.util.UUID.randomUUID().toString(), "aName", validDescription,"electrico","", true, ""));
        }
    }

}