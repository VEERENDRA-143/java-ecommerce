package com.eCommerce.ecom.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {
    public  static  final  String SECRATE = "38279509348905468476890450932849828987289749828930";

    public String genarateTocken(String userName){
        Map<String,Object> cliams = new HashMap<>();
        return  createToken(cliams,userName);
    }

    private   String  createToken(Map<String,Object> cliams,String userName){
        return Jwts.builder()
                .setClaims(cliams)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignkey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignkey(){
        byte[] keybytes= Decoders.BASE64.decode(SECRATE);
        return Keys.hmacShaKeyFor(keybytes);
    }

    public  String extractUsername(String tocken){
        return  extractClaim(tocken, Claims::getSubject);
    }
    public  <T> T extractClaim(String tocken, Function<Claims, T> claimsResolver){
        final  Claims claims = extractAllclaims(tocken);
        return  claimsResolver.apply(claims);
    }

    private  Claims extractAllclaims(String tocken){
        return  Jwts.parserBuilder().setSigningKey(getSignkey()).build().parseClaimsJws(tocken).getBody();
    }

    private  Boolean isTockenExpeired(String tocken){
        return extractExpiration(tocken).before(new Date());
    }
    public  Date extractExpiration(String tocken){
        return  extractClaim(tocken,Claims::getExpiration);
    }

    public  Boolean validateToken(String tocken, UserDetails userDetails){
        final  String userName = extractUsername(tocken);
        return  (userName.equals(userDetails.getUsername()) && !isTockenExpeired(tocken));
    }
}

