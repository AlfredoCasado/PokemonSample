package codesai.spring.repositories;

import java.util.ArrayList;
import java.util.List;

import codesai.spring.model.Pokemon;
import org.springframework.stereotype.Component;

@Component
public class PokemonRepository {

	public static List<Pokemon> pokemons;
	static {
		pokemons = new ArrayList();
		pokemons.add(new Pokemon(java.util.UUID.randomUUID().toString(), "pikachu", "gato electrico","electrico","", false));
		pokemons.add(new Pokemon(java.util.UUID.randomUUID().toString(), "Bulbasaur", "otro tipo de pokemon","electrico","", false));
		pokemons.add(new Pokemon(java.util.UUID.randomUUID().toString(), "Ivysaur", "este ni idea como es","electrico","", false));
	}

	public List list() {
		return pokemons;
	}

	public Pokemon get(String id) {

		for (Pokemon c : pokemons) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	public Pokemon create(Pokemon customer) {
		customer.setId(java.util.UUID.randomUUID().toString());
		pokemons.add(customer);
		return customer;
	}

	public String delete(String id) {
		for (Pokemon c : pokemons) {
			if (c.getId().equals(id)) {
				pokemons.remove(c);
				return id;
			}
		}

		return null;
	}

	public Pokemon update(String id, Pokemon customer) {

		for (Pokemon c : pokemons) {
			if (c.getId().equals(id)) {
				customer.setId(c.getId());
				pokemons.remove(c);
				pokemons.add(customer);
				return customer;
			}
		}

		return null;
	}

}