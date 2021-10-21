package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.Owner;
import upc.edu.pe.restcodebackend.domain.repository.OwnerRepository;
import upc.edu.pe.restcodebackend.domain.service.OwnerService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;
@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Page<Owner> getAllOwners(Pageable pageable) {
        return ownerRepository.findAll(pageable);
    }

    @Override
    public Owner getByEmail(String email) {
        return ownerRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Owner","Email",email));
    }


    @Override
    public Owner getOwnerById(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(()-> new ResourceNotFoundException("Owner","Id",ownerId));
    }

    @Override
    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner updateOwner(Long ownerId, Owner ownerRequest) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(()-> new ResourceNotFoundException("Owner","Id",ownerId));

        owner.setUserName(ownerRequest.getUserName());
        owner.setPassword(ownerRequest.getPassword());
        owner.setFirstName(ownerRequest.getFirstName());
        owner.setLastName(ownerRequest.getLastName());
        owner.setCellphone(ownerRequest.getCellphone());
        owner.setEmail(ownerRequest.getEmail());
        owner.setRuc(ownerRequest.getRuc());

        return ownerRepository.save(owner);
    }

    @Override
    public ResponseEntity<?> deleteOwner(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(()-> new ResourceNotFoundException("Owner","Id",ownerId));
        ownerRepository.delete(owner);
        return ResponseEntity.ok().build();
    }
}
