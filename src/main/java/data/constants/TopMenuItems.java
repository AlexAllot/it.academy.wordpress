package data.constants;

import lombok.Getter;

@Getter
public enum TopMenuItems {

    POSTS("Posts"),
    MEDIA("Media"),
    PAGES("Pages"),
    COMMENTS("Comments");

    private final String value;


    TopMenuItems(String value) {
        this.value=value;
    }
}
