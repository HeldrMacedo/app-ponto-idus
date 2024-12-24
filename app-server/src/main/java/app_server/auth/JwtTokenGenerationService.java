package app_server.auth;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;


@Service
public class JwtTokenGenerationService {
	
	private static final String SECRET_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTczNDgwODkxMywiaWF0IjoxNzM0ODA4OTEzfQ.R-RFompCOEZhVFunwpiol8PbkVMYOTGlvFXaLgm9XpU";
	private static final String ISSUER = "auth-api-login";//Emissor do token
	
	public String generateToken(UserDetailsImpl user) {
		try {
			
			Map<String, Object> extraClaims = new HashMap<>();
		    extraClaims.put("username", user.getUsername());
		    extraClaims.put("id", user.getId().toString());
		    extraClaims.put("role", user.getAuthorities());
			
			// Define o algoritmo HMAC SHA256 para criar a assinatura do token passando a chave secreta definida
			Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
			
			String jwtToken = JWT.create()
					.withIssuer(ISSUER) // Define o emissor do token
					.withSubject(user.getUsername()) //Define o assunto do token (neste caso, o nome de usuário)
					.withExpiresAt(this.expirationDate())//Define a data de expiração do token
					.withClaim("username", user.getUsername())
					.withClaim("id", user.getId().toString())
					.withClaim("role", user.getAuthorities()
							.stream()
							.map(GrantedAuthority::getAuthority)
							.collect(Collectors.toList()))
					.sign(algorithm);// Assina o token usando o algoritmo especificado
			
			return jwtToken;
		}catch (JWTCreationException e) {
			throw new JWTCreationException("Erro ao gerar token.", e);
		}
	}
	
	
	public String getSubjectFromToken(String token) {
		try {
			// Define o algoritmo HMAC SHA256 para verificar a assinatura do token passando a chave secreta definida
			Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
			
			return JWT.require(algorithm)
					.withIssuer(ISSUER) // Define o emissor do token
					.build()
					.verify(token)// Verifica a validade do token
					.getSubject();// Obtém o assunto (neste caso, o nome de usuário) do token
		}catch (JWTCreationException e) {
			throw new JWTVerificationException("Token inválido ou expirado.");
		}
		
	}
	
	//Gera o tempo do token
	private Instant expirationDate() {
		return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant();
	}
		

}
