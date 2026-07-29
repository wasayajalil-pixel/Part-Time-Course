package com.axsos.blogmanager.services;

import com.axsos.blogmanager.models.Address;
import com.axsos.blogmanager.models.Blog;
import com.axsos.blogmanager.repositires.AddressRepo;
import com.axsos.blogmanager.repositires.BlogRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServices {
    private final AddressRepo addressRepo;

    public AddressServices(AddressRepo addressRepo){
        this.addressRepo = addressRepo;
    }
    public void createCallAddress(Address address){
        addressRepo.save(address);
    }
    public List<Address> getAllAddress(){
        return addressRepo.findAll();
    }
}
