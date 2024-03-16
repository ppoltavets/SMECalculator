package repository;

import com.example.smecalculator.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Integer> {
    RegistrationEntity findAllByLoginAndPassword(String login, String password);

    RegistrationEntity findAllByLogin(String login);

}
