package br.com.venzel.store.modules.user.profile.profile.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProfileLegalPersonDTO {
    
    private String socialReason;

    private String cnpj;
}
