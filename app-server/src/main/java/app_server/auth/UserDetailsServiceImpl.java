package app_server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app_server.entity.User;
import app_server.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
    private UserRepository userRepository;
	
	/*
	 * O método é usado para carregar os detalhes do usuário com base no nome de usuário fornecido
	 * @param String username usuário
	 * @return um UserDetails com os detalhes do usuário.
	 */
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return new UserDetailsImpl(user);
    }
}
