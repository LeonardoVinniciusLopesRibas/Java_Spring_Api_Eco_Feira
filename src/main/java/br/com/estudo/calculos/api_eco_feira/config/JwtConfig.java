package br.com.estudo.calculos.api_eco_feira.config;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtConfig {

    public static final String CHAVE_SECRETA = "K5JD9420F9H9CJ4O68G3KSOF854IOW0D9593O3K6J6HRJEJ6I43I4J35J6NIEI45K4K3";
    public static final SignatureAlgorithm ALGORITMO_ASSINATURA = SignatureAlgorithm.HS256;
    public static final int HORAS_EXPIRACAO_TOKEN = 1;

}
