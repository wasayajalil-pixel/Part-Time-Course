package com.axsos.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.axsos.game.models.Game;
import com.axsos.game.models.User;
import com.axsos.game.services.GameService;
import com.axsos.game.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class GameController {
	private final UserService userService;
	private final GameService gameService;
	
	public GameController(UserService userService, GameService gameService) {
		this.userService = userService;
		this.gameService = gameService;
	}
    // Dashboard + sorting
	//The Model sends data from the Controller to the JSP.
    @GetMapping("/dashboard")
    public String dashboard(
            @RequestParam(value = "sort", required = false) String sort,
            HttpSession session,
            Model model) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        User loggedUser = userService.findUser(userId);

        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("games", gameService.sortGames(sort));
        model.addAttribute("newGame", new Game());

        return "dashboard.jsp";
    }
    // Create game
    @PostMapping("/games/create")
    public String createGame(
            @Valid @ModelAttribute("newGame") Game newGame,
            BindingResult result,
            HttpSession session,
            Model model) {

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

        // Set creator before saving
        newGame.setCreator(loggedUser);

        gameService.saveGame(newGame);

        return "redirect:/dashboard";
    }
    // Show one game
    @GetMapping("/game/{id}")
    public String showGame(
            @PathVariable("id") Long id,
            HttpSession session,
            Model model) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        Game game = gameService.findGame(id);
        User loggedUser = userService.findUser(userId);

        model.addAttribute("game", game);
        model.addAttribute("loggedUser", loggedUser);

        return "show.jsp";
    }

    // Edit page
    @GetMapping("/edit/game/{id}")
    public String editGame(
            @PathVariable("id") Long id,
            HttpSession session,
            Model model) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        Game game = gameService.findGame(id);

        // Only creator can edit
        if (!game.getCreator().getId().equals(userId)) {
            return "redirect:/dashboard";
        }

        model.addAttribute("game", game);

        return "edit.jsp";
    }

    // Update game
    @PostMapping("/update/game/{id}")
    public String updateGame(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("game") Game game,
            BindingResult result,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        Game oldGame = gameService.findGame(id);

        if (!oldGame.getCreator().getId().equals(userId)) {
            return "redirect:/dashboard";
        }

        if (result.hasErrors()) {
            return "edit.jsp";
        }

        // Keep same id and same creator
        game.setId(id); // add setId() in Game model
        game.setCreator(oldGame.getCreator());

        gameService.saveGame(game);

        return "redirect:/game/" + id;
    }

    // Delete game
    @GetMapping("/delete/game/{id}")
    public String deleteGame(
            @PathVariable("id") Long id,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        Game game = gameService.findGame(id);

        // Only creator can delete
        if (game.getCreator().getId().equals(userId)) {
            gameService.deleteGame(id);
        }

        return "redirect:/dashboard";
    }

}
