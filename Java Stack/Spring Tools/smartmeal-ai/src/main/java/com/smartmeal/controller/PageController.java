package com.smartmeal.controller;
import jakarta.servlet.http.HttpSession; import org.springframework.stereotype.Controller; import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PageController {
 @GetMapping("/") String landing(){return "landing";} @GetMapping("/login") String login(){return "login";} @GetMapping("/register") String register(){return "register";}
 @GetMapping("/dashboard") String dashboard(HttpSession s){return guard(s,"dashboard");} @GetMapping("/history") String history(HttpSession s){return guard(s,"food-history");}
 @GetMapping("/analysis") String analysis(HttpSession s){return guard(s,"ai-analysis");} @GetMapping("/recommendations") String rec(HttpSession s){return guard(s,"ai-recommendations");}
 @GetMapping("/restaurants") String restaurants(HttpSession s){return guard(s,"restaurants");} @GetMapping("/meal-details") String details(HttpSession s){return guard(s,"meal-details");}
 @GetMapping("/profile") String profile(HttpSession s){return guard(s,"profile");}
 private String guard(HttpSession s,String page){return s.getAttribute("userId")==null?"redirect:/login":page;}
}
