package kr.scshin.scshin_dev.auth.adapter.in.web;

import kr.scshin.scshin_dev.auth.adapter.in.web.dto.request.LoginRequest;
import kr.scshin.scshin_dev.auth.adapter.in.web.dto.response.LoginResponse;
import kr.scshin.scshin_dev.auth.adapter.in.web.dto.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;


    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        Instant now = Instant.now();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.replace("ROLE_", "").toLowerCase())
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("scshin")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims))
                .getTokenValue();

        return new LoginResponse(accessToken);
    }

    @GetMapping("/auth/me")
    public UserInfoResponse me(@AuthenticationPrincipal Jwt jwt) {
        log.info("Authentication principal {}", jwt.getClaims());
        Map<String, Object> claims = jwt.getClaims();
        return UserInfoResponse.builder()
                .sub((String) claims.get("sub"))
                .scope((String) claims.get("scope"))
                .iat((Instant) claims.get("iat"))
                .exp((Instant) claims.get("exp"))
                .build();
    }
}
