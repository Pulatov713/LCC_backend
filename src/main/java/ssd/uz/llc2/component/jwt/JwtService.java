package ssd.uz.llc2.component.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;  // JWT imzolash uchun maxfiy kalit

    @Value("${jwt.expireDate}")
    private Long expireDate;  // JWT tokenning amal qilish muddati (millisekundlarda)

    // Foydalanuvchi nomidan JWT token yaratadi
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();  // Token ichiga qo'shiladigan ma'lumotlar
        return createToken(claims, userName);  // Token yaratish
    }

    // Token yaratish uchun yordamchi metod
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)  // Token ichidagi ma'lumotlar
                .setSubject(userName)  // Token egasining foydalanuvchi nomi
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Token yaratilgan vaqt
                .setExpiration(new Date(System.currentTimeMillis() + expireDate))  // Tokenning tugash vaqti
                .signWith(getSignKey(), SignatureAlgorithm.HS256)  // Tokenni imzolash
                .compact();  // Tokenni yaratish
    }

    // Maxfiy kalitni olish
    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);  // Base64 kodlangan kalitni dekodlash
        return Keys.hmacShaKeyFor(keyBytes);  // HMAC SHA kalitni yaratish
    }

    // Token ichidagi foydalanuvchi nomini olish
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);  // Tokenning subject (foydalanuvchi nomi) ma'lumotini olish
    }

    // Tokenning tugash vaqtini olish
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);  // Tokenning expiration (tugash) ma'lumotini olish
    }

    // Token ichidagi ma'lumotlarni olish
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);  // Token ichidagi barcha ma'lumotlarni olish
        return claimsResolver.apply(claims);  // Ma'lumotni qaytarish
    }

    // Tokenni barcha ma'lumotlarini olish
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())  // Tokenni imzolashda ishlatilgan kalit
                .build()
                .parseClaimsJws(token)  // Tokenni dekodlash
                .getBody();  // Token ichidagi ma'lumotlarni olish
    }

    // Tokenning muddati tugaganligini tekshirish
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());  // Agar tokenning tugash vaqti hozirgi vaqtdan oldin bo'lsa, true qaytaradi
    }

    // Tokenni validligini tekshirish
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);  // Token ichidan foydalanuvchi nomini olish
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));  // Tokenni validligini tekshirish
    }
}
