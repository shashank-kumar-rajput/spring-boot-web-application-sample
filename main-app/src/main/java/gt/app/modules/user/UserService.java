package gt.app.modules.user;

import gt.app.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User save(User u) {
        return userRepository.save(u);
    }

    public void updateUserIfNeeded(User user) {

        //TODO: cache

        if (!userRepository.existsById(user.getId())) {
            save(user);
        }


    }

    public User getReference(UUID id) {
        return userRepository.getOne(id);
    }

    public String userSummary(){
        return "";
    }
}