package codesai.spring.model;

public class Pokemon {

	private String id;
	private String name;
	private String description;
	private String primaryType;
	private String secondaryType;
	private boolean favorite;
	private String evolution;

	public Pokemon(String id, String name, String description, String primaryType, String secondaryType, boolean favorite, String evolution) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.primaryType = primaryType;
		this.secondaryType = secondaryType;
		this.favorite = favorite;
		this.evolution = evolution;
	}

	public Pokemon() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}

	public String getSecondaryType() {
		return secondaryType;
	}

	public void setSecondaryType(String secondaryType) {
		this.secondaryType = secondaryType;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}


	public boolean isNotValid() {
		return invalidName() || invalidDescription() || doesNotHavePrimaryType();
	}

	private boolean doesNotHavePrimaryType() {
		return primaryType == null || "".equals(primaryType);
	}

	private boolean invalidDescription() {
		return description == null || description.length() < 30;
	}

	private boolean invalidName() {
		return name == null || name.length() < 4 || name.length() > 24;
	}

	public String getEvolution() {
		return evolution;
	}

	public void setEvolution(String evolution) {
		this.evolution = evolution;
	}


}