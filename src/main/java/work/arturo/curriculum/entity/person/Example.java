package work.arturo.curriculum.entity.person;

import java.util.List;

import work.arturo.curriculum.entity.Text;

public class Example {
	private String name;
	private String url;
	private List<Text> description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Text> getDescription() {
		return description;
	}

	public void setDescription(List<Text> description) {
		this.description = description;
	}

}
