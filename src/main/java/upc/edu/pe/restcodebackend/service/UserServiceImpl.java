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
                .orElseThrow(()-> new ResourceNotFoundException("Profile","Email",email));

        if(!profile.getPassword().equals(password)){
            return null;
        }
        return profile;
    }
}
