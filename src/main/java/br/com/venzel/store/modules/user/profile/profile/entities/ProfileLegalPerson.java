package br.com.venzel.store.modules.user.profile.profile.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.venzel.store.modules.user.user.entities.User;
import br.com.venzel.store.modules.user.user.entities.types.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "profileLegalPerson")
public class ProfileLegalPerson extends Profile {
    
    /* Attributes */

    @Column(nullable = true, length = 100)
    private String socialReason;

    @Column(nullable = true, length = 40)
    private String cnpj;

    /* Constructors */

    public ProfileLegalPerson(User user, UserType type) {
        super(user, type);
    }

    public ProfileLegalPerson(User user, UserType type, String socialReason, String cnpj) {
        super(user, type);
        this.socialReason = socialReason;
        this.cnpj = cnpj;
    }
}
