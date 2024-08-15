package com.ltp.gradesubmission.security.filter;

// In this class, we take the token and the secret key and generate a signature using
// the algorithm, token header and payload and
// compare the signature generated to the signature in the token
// if the signature is valid, then we set the authentication object on the security context
// holder. the security context holder stores details of who is authenticated
public class JWTAuthorizationFilter {

}
