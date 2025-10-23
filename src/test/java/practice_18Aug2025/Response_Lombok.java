package practice_18Aug2025;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response_Lombok {

    private String instructor;
    private String url;
    private String services;
    private String expertise;
    private Courses1 courses;
    private String linkedIn;

}

@Getter
@Setter
class Courses1 {

    private List<Course1> webAutomation;
    private List<Course1> api;
    private List<Course1> mobile;
}


@Getter
@Setter
class Course1 {

    private String courseTitle;
    private String price;
}
