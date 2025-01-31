package kiddo.kiddomanager.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PersonalRoleEnum {
    ADMIN("Administrateur"),
    NURSE("Educatrice");

    private final String role;

}
