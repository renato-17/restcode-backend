package upc.edu.pe.restcodebackend.domain.service;

import upc.edu.pe.restcodebackend.domain.model.Profile;

public interface UserService {
    Profile authentication(String email, String password);
}
