package gt.app.service.user;

import gt.app.domain.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    final AuthorityRepository authorityRepository;

    public void save(Authority auth) {
        authorityRepository.save(auth);
    }

    public Set<Authority> findByNameIn(String... roles) {
        return authorityRepository.findByNameIn(roles);
    }
}