package br.com.venzel.store.modules.product.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDTO {

    private String name;

    private Double price;

    private Boolean activated;
}
