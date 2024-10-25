package boraldan.vita.security;


import boraldan.vita.domen.Person;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Класс, реализующий интерфейс UserDetails для представления информации о пользователе в Spring Security.
 */
@AllArgsConstructor
public class PersonDetails implements UserDetails {

    private final Person person;

    /**
     * Возвращает коллекцию ролей пользователя.
     * @return коллекция ролей пользователя
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // если будет использовать роли
//        List<GrantedAuthority> authorityList = new ArrayList<>();
//        person.getRoles().stream()
//                .map(role -> authorityList.add(new SimpleGrantedAuthority(role.getName())))
//                .collect(Collectors.toList());
//        return authorityList;
        return Collections.emptyList();
    }

    /**
     * Возвращает пароль пользователя.
     * @return пароль пользователя
     */
    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    /**
     * Возвращает логин пользователя.
     * @return логин пользователя
     */
    @Override
    public String getUsername() {
        return this.person.getName();
    }

    /**
     * Проверяет, не истек ли срок действия аккаунта пользователя.
     * @return true, если срок действия аккаунта не истек, иначе - false
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверяет, не заблокирован ли аккаунт пользователя.
     * @return true, если аккаунт не заблокирован, иначе - false
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Проверяет, не истек ли срок действия учетных данных пользователя.
     * @return true, если срок действия учетных данных не истек, иначе - false
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверяет, активен ли аккаунт пользователя.
     * @return true, если аккаунт активен, иначе - false
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Возвращает объект класса Person, содержащий данные пользователя.
     * @return объект класса Person
     */
    public Person getPerson(){
        return this.person;
    }
}