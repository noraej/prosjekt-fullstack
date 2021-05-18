package idatt2105.hamsterGroup.fullstackProject.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


/**
 * Entity class for storing information about authenticated user
 * UserDetails including userId
 */
public class UserSecurity implements UserDetails {
    private long userId;
    private String email;
    private String password;
    private boolean isCredentialsNonExpired;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isEnabled;
    private List<? extends GrantedAuthority> grantedAuthorities;


    public UserSecurity(long userId, String email, String password, List<? extends GrantedAuthority> grantedAuthorities) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isCredentialsNonExpired = true;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isEnabled = true;
    }

    public UserSecurity(){
        this.isCredentialsNonExpired = true;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isEnabled = true;
    }

    public long getUserId() {
        return userId;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(List<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }
}
