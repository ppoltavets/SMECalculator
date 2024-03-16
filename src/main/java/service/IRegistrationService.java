package service;

import com.example.smecalculator.entity.RegistrationEntity;
import org.springframework.stereotype.Service;

@Service
public interface IRegistrationService {

    public String addUser(RegistrationEntity registration);
    public String updateUser(RegistrationEntity registration);
    public RegistrationEntity login(RegistrationEntity registration);
    public RegistrationEntity findUser(String login);
}
