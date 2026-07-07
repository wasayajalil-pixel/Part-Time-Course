package com.axsos.expenses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.axsos.expenses.models.Expense;
import com.axsos.expenses.services.ExpensesServices;
import jakarta.validation.Valid;



@Controller
public class expensesController {
	public final ExpensesServices expenseServices;
	// Dependency Injection
	public expensesController(ExpensesServices expenseServices) {
		this.expenseServices = expenseServices;
	}
    // Main page: show table + form
    @GetMapping("/")
    public String index(@ModelAttribute("expense") Expense expense, Model model) {
        model.addAttribute("expenses", expenseServices.allExpenses());
        return "index.jsp";
    }

    // Create expense
    @PostMapping("/expenses")
    public String createExpense(
            @Valid @ModelAttribute("expense") Expense expense,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("expenses", expenseServices.allExpenses());
            return "index.jsp";
        }

        expenseServices.saveExpense(expense);
        return "redirect:/";
    }

    // Show one expense details
    @GetMapping("/expenses/{id}")
    public String showExpense(@PathVariable("id") Long id, Model model) {
        Expense expense = expenseServices.findExpense(id);
        model.addAttribute("expense", expense);
        return "show.jsp";
    }

    // Edit page
    @GetMapping("/expenses/{id}/edit")
    public String editExpense(@PathVariable("id") Long id, Model model) {
        Expense expense = expenseServices.findExpense(id);
        model.addAttribute("expense", expense);
        return "edit.jsp";
    }

    // Update expense
    @PutMapping("/expenses/{id}")
    public String updateExpense(
            @Valid @ModelAttribute("expense") Expense expense,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "edit.jsp";
        }

        expenseServices.saveExpense(expense);
        return "redirect:/";
    }

    // Delete expense
    @DeleteMapping("/expenses/{id}")
    public String deleteExpense(@PathVariable("id") Long id) {
        expenseServices.deleteExpense(id);
        return "redirect:/";
    }


}
