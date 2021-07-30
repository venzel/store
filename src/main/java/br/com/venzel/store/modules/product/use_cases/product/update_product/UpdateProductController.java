package br.com.venzel.store.modules.product.use_cases.product.update_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.venzel.store.modules.product.dtos.ProductDTO;

@RestController
@RequestMapping("/products")
public class UpdateProductController {

    @Autowired
    private UpdateProductService updateProductService;

    @PutMapping("/{id}")
    public ProductDTO handle(@RequestBody ProductDTO dto, @PathVariable Long id) {

        return updateProductService.execute(dto, id);
    }
}