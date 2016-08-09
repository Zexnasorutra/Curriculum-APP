package work.arturo.curriculum.entity.person;

import java.util.Date;
import java.util.List;

import work.arturo.curriculum.entity.Text;

public class Activity {
	private List<Text> name;
	private Date startDate;
	private Date endDate;
	private List<Text> description;

	public List<Text> getName() {
		return name;
	}

	public void setName(List<Text> name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Text> getDescription() {
		return description;
	}

	public void setDescription(List<Text> description) {
		this.description = description;
	}

}
