package com.axsos.expenses.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.axsos.expenses.models.Expense;
@Repository
public interface ExpensesRepository extends CrudRepository<Expense, Long>{

}
