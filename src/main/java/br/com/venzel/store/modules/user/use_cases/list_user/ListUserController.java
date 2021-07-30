package br.com.venzel.store.modules.user.use_cases.list_user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.venzel.store.modules.user.dtos.UserDTO;

@RestController
@RequestMapping("/users")
public class ListUserController {
    
    @Autowired
    private ListUserServices listUserServices;

    @GetMapping
    public List<UserDTO> handle() {
        return this.listUserServices.execute();
    }
}
