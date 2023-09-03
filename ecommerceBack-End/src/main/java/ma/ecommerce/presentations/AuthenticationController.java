package ma.ecommerce.presentations;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ecommerce.dto.TokenDTO;
import ma.ecommerce.dto.UserDTO;
import ma.ecommerce.exceprions.BusinessExceptionAuthentification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ma.ecommerce.jwt.JwtUtils;
import java.util.Collection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<Object > authenticateUser(@Valid @RequestBody UserDTO userVo) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userVo.getUsername(), userVo.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			TokenDTO tokenVo = new TokenDTO();
			tokenVo.setJwttoken(jwt);
			tokenVo.setUsername(userVo.getUsername());
			Collection<? extends GrantedAuthority> list = authentication.getAuthorities();
			list.forEach(authorite -> tokenVo.getRoles().add(authorite.getAuthority()));
			return ResponseEntity.ok(tokenVo);
		} catch (Exception e) {
			throw new BusinessExceptionAuthentification("Login ou mot de passe incorrect",e);
		}
    }
}
