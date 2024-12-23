package app_server.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app_server.dto.CreateUserDto;
import app_server.dto.LoginResponseDTO;
import app_server.dto.LoginUserDto;
import app_server.dto.RecoveryJwtTokenDto;
import app_server.entity.User;
import app_server.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEnconder;
	
	
	@PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
		Optional<User> user = this.userService.findByEmail(loginUserDto.email());
		if (user.isEmpty()) {
			return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
		}
		if (this.passwordEnconder.matches(loginUserDto.password(), user.get().getPassword())) {
			System.out.println("Entrou no segundo if ");
			RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
	        return ResponseEntity.ok(new LoginResponseDTO(user.get().getName(), token.token()));
		}	
		return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto createUserDto) {
    	try {
    		Optional<User> user = Optional.ofNullable(this.userService.findByEmail(createUserDto.email()).orElse(null));
    		if (user.isEmpty()) {
    			this.userService.createUser(createUserDto);
    			return new ResponseEntity<>(null, HttpStatus.CREATED);
    		}else {
    			return new ResponseEntity<>("Email já está cadastrado.", HttpStatus.CONFLICT);
    		}
    		
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro interno.", HttpStatus.NOT_FOUND);
		}
        
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/customer")
    public ResponseEntity<String> getCustomerAuthenticationTest() {
        return new ResponseEntity<>("Cliente autenticado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/test/administrator")
    public ResponseEntity<String> getAdminAuthenticationTest() {
        return new ResponseEntity<>("Administrador autenticado com sucesso", HttpStatus.OK);
    }

}
