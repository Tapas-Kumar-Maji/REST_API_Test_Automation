package pojo.oauth;

public class ResponsePojo {

	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private Courses courses;
	private String linkedIn;

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	public String getInstructor() {
		return this.instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getUrl() {
		return this.url;
	}

	public String getServices() {
		return this.services;
	}

	public String getExpertise() {
		return this.expertise;
	}

	public String getLinkedIn() {
		return this.linkedIn;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

}
