package br.com.venzel.store.modules.user.profile.profile.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProfileFhysicalPerson {
    
    private String avatar;

    private String cpf;
}
