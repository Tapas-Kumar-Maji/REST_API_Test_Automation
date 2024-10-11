package pojo.oauth;

import java.util.List;

public class Courses {

	private List<Course> webAutomation;
	private List<Course> api;
	private List<Course> mobile;

	public List<Course> getWebAutomation() {
		return this.webAutomation;
	}

	public void setWebAutomation(List<Course> webAutomation) {
		this.webAutomation = webAutomation;
	}

	public List<Course> getApi() {
		return this.api;
	}

	public void setApi(List<Course> api) {
		this.api = api;
	}

	public List<Course> getMobile() {
		return this.mobile;
	}

	public void setMobile(List<Course> mobile) {
		this.mobile = mobile;
	}

}
