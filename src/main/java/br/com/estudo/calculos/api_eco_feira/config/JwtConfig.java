package br.com.estudo.calculos.api_eco_feira.config;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtConfig {

    public static final String CHAVE_SECRETA = "l_!E7BR2*G?.1em#~.O:X5?q4[!(*v5*R#hZ~lz$6!vd,n&B:0B5GH+Vsz43x;V[*dQ5z.K55GPl2x#6^8l0.]$u-OZ(rQw^v^a";
    public static final SignatureAlgorithm ALGORITMO_ASSINATURA = SignatureAlgorithm.HS256;
    public static final int HORAS_EXPIRACAO_TOKEN = 1;

}
