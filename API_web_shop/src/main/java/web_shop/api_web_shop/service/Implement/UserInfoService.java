package web_shop.api_web_shop.service.Implement;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web_shop.api_web_shop.entity.TaiKhoan;
import web_shop.api_web_shop.repository.TaiKhoanRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService implements UserDetailsService {

    private final TaiKhoanRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<TaiKhoan> userDetail = repository.findByEmail(username);
        return userDetail.orElseThrow(() -> new RuntimeException("Not found user " + username));
    }
}
