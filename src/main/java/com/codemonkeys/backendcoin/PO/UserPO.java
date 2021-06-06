package com.codemonkeys.backendcoin.PO;

import com.codemonkeys.backendcoin.Enum.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public  class UserPO implements UserDetails {
        private String username;
        private String password;
        private int id;
        private Roles role;

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            if(role==null){
                return AuthorityUtils.commaSeparatedStringToAuthorityList("");
            }
            return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+role.getRole());

        }

        @Override
        public String getPassword() {
            return this.password;
        }

        @Override
        public String getUsername() {
            return this.username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }


    public void setUsername(String username) {
        this.username = username;
    }
}

