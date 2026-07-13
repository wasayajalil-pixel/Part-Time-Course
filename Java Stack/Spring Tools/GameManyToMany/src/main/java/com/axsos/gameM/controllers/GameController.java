package com.axsos.gameM.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.axsos.gameM.models.Game;
import com.axsos.gameM.models.User;
import com.axsos.gameM.services.GameService;
import com.axsos.gameM.services.RatingService;
import com.axsos.gameM.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class GameController {

    private final GameService gameService;
    private final UserService userService;
    private final RatingService ratingService;

    public GameController(GameService gameService, UserService userService, RatingService ratingService) {
        this.gameService = gameService;
        this.userService = userService;
        this.ratingService = ratingService;
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(
            @RequestParam(value = "sort", required = false) String sort,
            Model model,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        User loggedUser = userService.findUser(userId);

        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("newGame", new Game());
        model.addAttribute("games", gameService.sortedGames(sort));

        return "dashboard.jsp";
    }

    // Create game
    @PostMapping("/games")
    public String createGame(
            @Valid @ModelAttribute("newGame") Game newGame,
            BindingResult result,
            Model model,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        User loggedUser = userService.findUser(userId);

        if (result.hasErrors()) {
            model.addAttribute("loggedUser", loggedUser);
            model.addAttribute("games", gameService.allGames());
            return "dashboard.jsp";
        }

        newGame.setCreator(loggedUser);
        gameService.saveGame(newGame);

        return "redirect:/dashboard";
    }

    // Show one game
    @GetMapping("/game/{id}")
    public String showGame(
            @PathVariable("id") Long id,
            Model model,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        User loggedUser = userService.findUser(userId);
        Game game = gameService.findGame(id);

        if (game == null) {
            return "redirect:/dashboard";
        }

        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("game", game);

        return "show.jsp";
    }

    // Edit page
    @GetMapping("/edit/game/{id}")
    public String editGamePage(
            @PathVariable("id") Long id,
            Model model,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        Game game = gameService.findGame(id);

        if (game == null || !game.getCreator().getId().equals(userId)) {
            return "redirect:/dashboard";
        }

        model.addAttribute("game", game);

        return "edit.jsp";
    }

    // Update game
    @PutMapping("/games/{id}")
    public String updateGame(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("game") Game editedGame,
            BindingResult result,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        Game oldGame = gameService.findGame(id);

        if (oldGame == null || !oldGame.getCreator().getId().equals(userId)) {
            return "redirect:/dashboard";
        }

        if (result.hasErrors()) {
            return "edit.jsp";
        }

        editedGame.setCreator(oldGame.getCreator());
        gameService.saveGame(editedGame);

        return "redirect:/game/" + id;
    }

    // Delete game
    @DeleteMapping("/games/{id}")
    public String deleteGame(
            @PathVariable("id") Long id,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        Game game = gameService.findGame(id);

        if (game != null && game.getCreator().getId().equals(userId)) {
            gameService.deleteGame(id);
        }

        return "redirect:/dashboard";
    }

    // Add game to favorite
    @PostMapping("/game/{id}/favorite")
    public String favoriteGame(
            @PathVariable("id") Long id,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        User user = userService.findUser(userId);
        Game game = gameService.findGame(id);

        if (game == null) {
            return "redirect:/dashboard";
        }

        if (!user.getFavoriteGames().contains(game)) {
            user.getFavoriteGames().add(game);
            userService.saveUser(user);
        }

        return "redirect:/game/" + id;
    }

    // Rate game
    @PostMapping("/game/{id}/rate")
    public String rateGame(
            @PathVariable("id") Long id,
            @RequestParam("rate") int rate,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        User user = userService.findUser(userId);
        Game game = gameService.findGame(id);

        if (game == null) {
            return "redirect:/dashboard";
        }

        ratingService.rateGame(user, game, rate);

        return "redirect:/game/" + id;
    }
}
