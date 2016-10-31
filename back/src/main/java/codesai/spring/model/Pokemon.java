package codesai.spring.model;

public class Pokemon {

	private String id;
	private String name;
	private String description;
	private String primaryType;
	private String secondaryType;
	private boolean favorite;

	public Pokemon(String id, String name, String description, String primaryType, String secondaryType, boolean favorite) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.primaryType = primaryType;
		this.secondaryType = secondaryType;
		this.favorite = favorite;
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


}