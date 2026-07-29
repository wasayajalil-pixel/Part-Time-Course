package com.axsos.blogmanager.repositires;

import com.axsos.blogmanager.models.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends CrudRepository<Address,Long> {
    List<Address> findAll();
}
