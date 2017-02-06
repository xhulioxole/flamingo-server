package com.flamingo.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Xhulio on 2/15/2016.
 */
public class JWT {

    // TODO - Change
    private static final String SECRET = "!!@#*&$!@bscaK!$*&12jgaSDb1t86712457AS*!$#";
    private static final String ISSUER = "flamingo";
    private static final long EXP = new Date().getTime() + (24 * 60 * 60 * 1000); // 1 Day

    /**
     * Generate JWT
     * @param id user id
     * @return token
     */
    public static String generate(String id) throws JOSEException {
        // Create HMAC signer
        JWSSigner signer = new MACSigner(SECRET);

        // JWTHeader
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        // JWTClaims (Payload)
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .issuer(ISSUER)
                .expirationTime(new Date(EXP))
                .jwtID(String.valueOf(new Date().getTime()))
                .claim("_id", id)
                .build();

        // Create JWT
        SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);

        // Sign with HMAC
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    /**
     * Verify token
     * @param token token
     */
    public static boolean isValid(String token) {
        try {
            // Parse token
            SignedJWT jwt = SignedJWT.parse(token);

            // Create HMAC Verifier
            JWSVerifier jwsVerifier = new MACVerifier(SECRET);

            // Verify signature, iss and expiration
            return (jwt.verify(jwsVerifier) &&
                    jwt.getJWTClaimsSet().getIssuer().equals(ISSUER) &&
                    new Date().before(jwt.getJWTClaimsSet().getExpirationTime())
            );
        } catch (JOSEException e) {
            return false;
        } catch (ParseException e) {
            return false;
        }
    }
}
