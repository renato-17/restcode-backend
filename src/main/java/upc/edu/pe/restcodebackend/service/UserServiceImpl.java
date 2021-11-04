package upc.edu.pe.restcodebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.restcodebackend.domain.model.Profile;
import upc.edu.pe.restcodebackend.domain.repository.ProfileRepository;
import upc.edu.pe.restcodebackend.domain.service.UserService;
import upc.edu.pe.restcodebackend.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile authentication(String email, String password) {
        Profile profile = profileRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Email does not exists"));

        if(!profile.getPassword().equals(password)){
            throw  new ResourceNotFoundException("Incorrect Password");
        }
        return profile;
    }
}
