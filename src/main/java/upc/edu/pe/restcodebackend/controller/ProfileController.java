package upc.edu.pe.restcodebackend.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.restcodebackend.domain.model.Profile;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
import upc.edu.pe.restcodebackend.resource.AuthUser;
import upc.edu.pe.restcodebackend.resource.save.AuthRequest;
import upc.edu.pe.restcodebackend.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ProfileController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/users/authentication")
    public AuthUser login(@Valid @RequestBody AuthRequest request) {
        Profile profile = userService.authentication(request.getEmail(),request.getPassword());
        if(profile == null){
            return null;
        }

        String token = getJWTToken(profile.getUserName());
        AuthUser user = new AuthUser();
        user.setId(profile.getId());
        user.setEmail(profile.getEmail());
        user.setUsername(profile.getUserName());
        user.setDiscriminator(profile.getPersonType());
        user.setToken(token);
        return user;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }
}
