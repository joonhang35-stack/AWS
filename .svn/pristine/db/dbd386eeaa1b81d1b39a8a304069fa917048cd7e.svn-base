package com.bcs.zsg.common.helper;

import java.net.URL;
import java.security.interfaces.RSAPublicKey;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;

public class JwtVerifierService {

    private String jwksUrl;

    public JwtVerifierService(String jwksUrl) {
        this.jwksUrl = jwksUrl;
    }

    public boolean verifyToken(String token) {
        try {

            // Parse JWT
            JWSObject jwsObject = JWSObject.parse(token);

            // Load JWKS
            JWKSet jwkSet = JWKSet.load(new URL(jwksUrl));

            // Match key by kid
            String kid = jwsObject.getHeader().getKeyID();
            JWK jwk = jwkSet.getKeyByKeyId(kid);

            if (jwk == null) {
                return false;
            }

            if (!(jwk instanceof RSAKey)) {
                return false;
            }
            
            RSAKey rsaKey = (RSAKey) jwk;
            RSAPublicKey publicKey = rsaKey.toRSAPublicKey();

            JWSVerifier verifier = new RSASSAVerifier(publicKey);

            // Verify signature
            if (!jwsObject.verify(verifier)) {
                return false;
            }

            // Check expiration manually (important in Java 7)
            Object expObj = jwsObject.getPayload().toJSONObject().get("exp");
            if (expObj == null) {
                return false;
            }
            
            long exp;
            if (expObj instanceof Number) {
                exp = ((Number) expObj).longValue();
            } else {
                exp = Long.parseLong(String.valueOf(expObj));
            }

            long now = System.currentTimeMillis() / 1000;

            if (exp < now) {
                return false;
            }

            return true;

        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
}