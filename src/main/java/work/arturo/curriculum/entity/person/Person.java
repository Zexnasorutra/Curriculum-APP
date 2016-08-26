package work.arturo.curriculum.entity.person;

import java.util.List;

import work.arturo.curriculum.entity.Text;

public class Person {
	private String name;
	private String photo;
	private List<String> availableLanguages;
	private List<Text> description;

	private List<Studie> studies;
	private List<Job> jobs;
	private List<String> skills;
	private List<Example> examples;
	private Contact contact;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<String> getAvailableLanguages() {
		return availableLanguages;
	}

	public void setAvailableLanguages(List<String> availableLanguages) {
		this.availableLanguages = availableLanguages;
	}

	public List<Text> getDescription() {
		return description;
	}

	public void setDescription(List<Text> description) {
		this.description = description;
	}

	public List<Studie> getStudies() {
		return studies;
	}

	public void setStudies(List<Studie> studies) {
		this.studies = studies;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<Example> getExamples() {
		return examples;
	}

	public void setExamples(List<Example> examples) {
		this.examples = examples;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
