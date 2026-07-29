package com.axsos.blogmanager.repositires;

import com.axsos.blogmanager.models.Blog;
import com.axsos.blogmanager.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface BlogRepo extends CrudRepository<Blog,Long> {
    List<Blog> findAll();
    Optional<Blog> findById(Long project_id);
    Blog save(Blog project);
    void deleteById(Long project_id);
    // Books that are either unborrowed (available to anyone) OR
// owned by this specific user (owner always sees their own book,
// even while someone else is borrowing it).
    List<Blog> findByBorrowerIsNullOrUser(User user);


    List<Blog> findByBorrower(User borrower);

    List<Blog> findByBorrowerIsNullAndUserNot(User user);

    List<Blog> findByUserNot(User user);
    List<Blog> findByUser(User user);

}
