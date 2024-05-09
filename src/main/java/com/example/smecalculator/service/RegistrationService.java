package com.example.smecalculator.service;

import com.example.smecalculator.entity.RegistrationEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.smecalculator.repository.RegistrationRepository;

@Service
public class RegistrationService implements IRegistrationService {

    private final RegistrationRepository repository;

    public RegistrationService(RegistrationRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public String addUser(RegistrationEntity registration) {
        repository.save(registration);
        return "success";
    }

    @Override
    @Transactional
    public String updateUser(RegistrationEntity registration) {
        repository.save(registration); // TODO: Посмотреть нужно ли удалять при этом элемент??
        return "success";
    }

    @Override
    @Transactional
    public RegistrationEntity login(RegistrationEntity registration) {
        return repository.findAllByLoginAndPassword(registration.getLogin(), registration.getPassword());
    }

    @Override
    @Transactional
    public RegistrationEntity findUser(String login) {
        return repository.findAllByLogin(login);
    }

    @Override
    public RegistrationEntity returnUserInfo(String login) {
        var clientInfo = repository.findAllByLogin(login);

        return RegistrationEntity.builder()
                .name(clientInfo.getName())
                .surname(clientInfo.getSurname())
                .companyName(clientInfo.getCompanyName())
                .yield(clientInfo.getYield())
                .build();
    }

    @Override
    @Transactional
    public RegistrationEntity addUserInfo(RegistrationEntity clientInfo) {
        var prev = repository.findAllByLogin(clientInfo.getLogin());
        prev.setName(clientInfo.getName());
        prev.setSurname(clientInfo.getSurname());
        prev.setCompanyName(clientInfo.getCompanyName());
        prev.setYield(clientInfo.getYield());
        repository.save(prev);
        return null;
    }

    @Override
    public void updatePassword(String login, String newPassword) {
        var user= repository.findAllByLogin(login);
        user.setPassword(newPassword);
        repository.save(user);
    }

}
