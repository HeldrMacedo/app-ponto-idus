package app_server.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import app_server.auth.JwtTokenGenerationService;
import app_server.auth.SecurityConfiguration;
import app_server.auth.UserDetailsImpl;
import app_server.dto.CreateUserDto;
import app_server.dto.LoginUserDto;
import app_server.dto.RecoveryJwtTokenDto;
import app_server.entity.Role;
import app_server.entity.User;
import app_server.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    private JwtTokenGenerationService jwtTokenService;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private SecurityConfiguration securityConfiguration;
	
	@Autowired
	private PasswordEncoder passwordEnconder;
	
	/*
	 *@method  Método responsável por autenticar um usuário e retornar um token JWT
	 */
    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }
    
    // Método responsável por criar um usuário
    public void createUser(CreateUserDto createUserDto) {

        // Cria um novo usuário com os dados fornecidos
        User newUser = User.builder()
                .email(createUserDto.email())
                .name(createUserDto.name())
                // Codifica a senha do usuário com o algoritmo bcrypt
                .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
                // Atribui ao usuário uma permissão específica
                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                .build();

        // Salva o novo usuário no banco de dados
        userRepository.save(newUser);
    }
    
    public Optional<User> findByEmail(String email) {
    	/*return Optional.ofNullable(this.userRepository.findByEmail(email)
    			.orElseThrow(() -> new RuntimeException("Usuário não encontrado")));*/
    	return this.userRepository.findByEmail(email);
    }
    
    public void save(CreateUserDto userDTO) {
    	User user = new User();
    	user.setPassword(passwordEnconder.encode(userDTO.password()));
    	user.setEmail(userDTO.email());
    	user.setName(userDTO.name());
    	this.userRepository.save(user);
    }
    
    public User findById(Long id) {
    	return this.userRepository.findById(id).get();
    }
}
