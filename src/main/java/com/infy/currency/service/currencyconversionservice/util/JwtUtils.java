package com.infy.currency.service.currencyconversionservice.util;

import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    /*private static String secret = "This_is_secret";
    private static long expiryDuration = 60 * 60;

    public String generateJwt(Customer customer){

        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration * 1000;
	
        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        // claims
        Claims claims = Jwts.claims()
                .setIssuer(customer.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

        // optional claims
        claims.put("type", customer.getUserType());
        claims.put("name", customer.getName());
        claims.put("emailId", customer.getEmailId());

        // generate jwt using claims
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims verify(String authorization) throws Exception {

        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
            return claims;
        } catch(Exception e) {
            throw new AccessDeniedException("Access Denied");
        }

    }
*/}
