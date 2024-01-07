package data.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Platform {

    WEB("Web"),
    ANDROID("Android");

    private final String name;
}
