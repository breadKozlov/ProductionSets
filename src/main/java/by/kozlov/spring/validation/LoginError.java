package by.kozlov.spring.validation;

import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class LoginError {

    String code;
    String message;
}
