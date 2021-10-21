package upc.edu.pe.restcodebackend.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.restcodebackend.domain.model.Owner;

public interface OwnerService {
    Page<Owner> getAllOwners(Pageable pageable);
    Owner getByEmail(String email);
    Owner getOwnerById(Long ownerId);
    Owner createOwner(Owner owner);
    Owner updateOwner(Long ownerId, Owner ownerRequest);
    ResponseEntity<?> deleteOwner(Long ownerId);
}
