package work.arturo.curriculum.entity.header;

import java.util.List;

import work.arturo.curriculum.entity.Text;

public class Header {
	private String fieldId;
	private String iconCSS;
	private List<Text> descriptive;

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getIconCSS() {
		return iconCSS;
	}

	public void setIconCSS(String iconCSS) {
		this.iconCSS = iconCSS;
	}

	public List<Text> getDescriptive() {
		return descriptive;
	}

	public void setDescriptive(List<Text> descriptive) {
		this.descriptive = descriptive;
	}

}
