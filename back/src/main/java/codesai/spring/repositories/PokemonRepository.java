package codesai.spring.repositories;

import java.util.ArrayList;
import java.util.List;

import codesai.spring.model.Pokemon;
import org.springframework.stereotype.Component;

@Component
public class PokemonRepository {

	private static List<Pokemon> customers;
	{
		customers = new ArrayList();
		customers.add(new Pokemon(java.util.UUID.randomUUID().toString(), "pikachu", "gato electrico"));
		customers.add(new Pokemon(java.util.UUID.randomUUID().toString(), "Bulbasaur", "otro tipo de pokemon"));
		customers.add(new Pokemon(java.util.UUID.randomUUID().toString(), "Ivysaur", "este ni idea como es"));
	}

	public List list() {
		return customers;
	}

	public Pokemon get(String id) {

		for (Pokemon c : customers) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	public Pokemon create(Pokemon customer) {
		customer.setId(java.util.UUID.randomUUID().toString());
		customers.add(customer);
		return customer;
	}

	public String delete(String id) {

		for (Pokemon c : customers) {
			if (c.getId().equals(id)) {
				customers.remove(c);
				return id;
			}
		}

		return null;
	}

	public Pokemon update(String id, Pokemon customer) {

		for (Pokemon c : customers) {
			if (c.getId().equals(id)) {
				customer.setId(c.getId());
				customers.remove(c);
				customers.add(customer);
				return customer;
			}
		}

		return null;
	}

}