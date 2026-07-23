package com.smartmeal.service;
import com.smartmeal.model.*; import com.smartmeal.repository.MealRepository; import org.springframework.stereotype.Service;
import java.time.*; import java.util.*;
@Service
public class RecommendationService {
 private final MealRepository meals; public RecommendationService(MealRepository meals){this.meals=meals;}
 public Map<String,Object> analysis(User user){ var list=meals.findByUserAndEatenAtBetweenOrderByEatenAtDesc(user,LocalDate.now().minusDays(7).atStartOfDay(),LocalDateTime.now()); int cal=list.stream().mapToInt(m->Optional.ofNullable(m.getCalories()).orElse(0)).sum(); int veg=(int)list.stream().filter(m->m.getName().toLowerCase().matches(".*(salad|vegetable|veggie|broccoli|spinach).*" )).count(); int fast=(int)list.stream().filter(m->m.getName().toLowerCase().matches(".*(pizza|burger|fried|shawarma).*" )).count(); int score=Math.max(20,Math.min(95,75+veg*4-fast*6)); return Map.of("score",score,"weeklyCalories",cal,"mealsLogged",list.size(),"vegetableMeals",veg,"fastFoodMeals",fast,"summary", fast>veg?"Try replacing one fast-food meal with vegetables or grilled protein.":"Good balance this week. Keep logging your meals."); }
 public List<Map<String,Object>> recommendations(User u){ return List.of(
  Map.of("id",1,"name","Grilled Chicken Bowl","calories",520,"protein",38,"carbs",45,"fat",16,"fiber",6,"tags",List.of("High Protein","Halal")),
  Map.of("id",2,"name","Salmon with Quinoa","calories",560,"protein",36,"carbs",42,"fat",20,"fiber",7,"tags",List.of("Omega-3","Healthy")),
  Map.of("id",3,"name","Chicken Avocado Salad","calories",480,"protein",34,"carbs",28,"fat",22,"fiber",10,"tags",List.of("Low Carb","High Protein")),
  Map.of("id",4,"name","Vegetable Stir Fry","calories",420,"protein",16,"carbs",58,"fat",14,"fiber",12,"tags",List.of("Vegan","Fiber Rich")) ); }
}
