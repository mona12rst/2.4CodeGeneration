//package com.example.demo.security;
//
//import com.example.demo.accounts.Account;
//import com.example.demo.user.User;
//import com.example.demo.user.UserRepository;
//import com.example.demo.utils.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.HandlerMapping;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Map;
//import java.util.Optional;
//
//@Component
//public class RequestFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//
//        String token = request.getHeader("Authorization");
//        String username = jwtUtil.extractUsername(token.substring(7));
//        final Map<String, String> pathVariables = (Map<String, String>) request
//                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//
//        Optional<User> user = userRepository.findByUserName(username);
//        if (user.isPresent()) {
//            for (Account account : user.get().getAccounts()) {
//                if (account.getIBANNo().equals(pathVariables.get("IBAN"))) {
//                    filterChain.doFilter(request, response);
//                }
//            }
//        }
//    }
//}
