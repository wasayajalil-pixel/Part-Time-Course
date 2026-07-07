package com.axsos.expenses.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.axsos.expenses.models.Expense;
import com.axsos.expenses.repositories.ExpensesRepository;

@Service
public class ExpensesServices {
	public final ExpensesRepository expensesRepo ;
	
	// Dependency Injection
	public ExpensesServices(ExpensesRepository expensesRepo) {
		this.expensesRepo = expensesRepo;
	}
	 // Read all expenses
    public List<Expense> allExpenses() {
    	return (List<Expense>) expensesRepo.findAll();
    }
     // Create or update expense
    public Expense saveExpense(Expense expense) {
    	return expensesRepo.save(expense);
    }
    // Find one expense by id
    public Expense findExpense(Long id) {
        Optional<Expense> optionalExpense = expensesRepo.findById(id);

        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        } else {
            return null;
        }
    }
    // Delete expense
    public void deleteExpense(Long id) {
    	expensesRepo.deleteById(id);
    }
}