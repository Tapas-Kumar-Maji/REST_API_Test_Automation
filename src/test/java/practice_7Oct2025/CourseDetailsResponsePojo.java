package practice_7Oct2025;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDetailsResponsePojo {

    private String instructor;
    private String url;
    private String services;
    private String expertise;
    private Courses courses;
    private String linkedIn;

}

@Getter
@Setter
class Courses {

    private List<Course> webAutomation;
    private List<Course> api;
    private List<Course> mobile;

}

@Getter
@Setter
class Course {

    private String courseTitle;
    private String price;

}
