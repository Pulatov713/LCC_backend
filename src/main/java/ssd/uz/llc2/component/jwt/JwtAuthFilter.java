package ssd.uz.llc2.component.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ssd.uz.llc2.service.AuthService;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService; // JWT xizmatlarini ta'minlovchi servis

    @Autowired
    private AuthService userDetailsService; // Foydalanuvchi haqida ma'lumot olish uchun xizmat

    // HTTP so'rov va javoblarni filtrlash uchun metod
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // So'rovdagi "Authorization" sarlavhasidan token olish
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // "Bearer " ni olib tashlab, tokaning o'zini olish
            username = jwtService.extractUsername(token); // Tokendan foydalanuvchi nomini olish
        }

        // Agar foydalanuvchi nomi mavjud bo'lsa va hozirgi xavfsizlik konteksti foydalanuvchiga o'rnatilmagan bo'lsa
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Foydalanuvchi ma'lumotlarini olish
            UserDetails userDetails = userDetailsService.   loadUserByUsername(username);
            // Agar token yaroqli bo'lsa
            if (jwtService.validateToken(token, userDetails)) {
                // Foydalanuvchining autentifikatsiyasini yaratish
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // So'rov bilan bog'liq autentifikatsiya detallari qo'shiladi
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Keyingi filtr yoki resursga so'rovni o'tkazish
        filterChain.doFilter(request, response);
    }
}
