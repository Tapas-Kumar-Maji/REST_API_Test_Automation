package practice_7Oct2025;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddPlaceRequestPojo {

    private Location location;
    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private List<String> types;
    private String website;
    private String language;

}
