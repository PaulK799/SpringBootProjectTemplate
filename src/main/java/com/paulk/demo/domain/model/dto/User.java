package com.paulk.demo.domain.model.dto;

import com.paulk.demo.utils.StringBuilderUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity(name = "user")
@Table(name = "user")
public class User implements UserDetails, Serializable {
    private static final long serialVersionUID = 9L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String username;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;

    private static final boolean ACCOUNT_NON_EXPIRED = true;
    private static final boolean ACCOUNT_NON_LOCKED = true;
    private static final boolean CREDENTIALS_NON_EXPIRED = true;
    private static final boolean ENABLED = true;

    /**
     * Get a username {@link String} for the {@link User}.
     *
     * @return The {@link String} representing the username for the {@link User}.
     */
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get a password {@link String} for the {@link User}.
     *
     * @return The {@link String} representing the password for the {@link User}.
     */
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return ACCOUNT_NON_EXPIRED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return ACCOUNT_NON_LOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return CREDENTIALS_NON_EXPIRED;
    }

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

    /**
     * Implements object comparison for a {@link User}.
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link User}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        User that = (User) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.role, that.role);
    }

    /**
     * Returns the hashCode for the {@link Role} based on the 'name' and 'id'.
     *
     * @return The hashcode for the {@link Role}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.username, this.id, this.password, this.role);
    }

    /**
     * Implementation of toString for an {@link User}.
     *
     * @return The {@link String} representation for an {@link User}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        StringBuilderUtils.addFieldToBuilder(builder, "id", this.id, true);
        StringBuilderUtils.addFieldToBuilder(builder, "username", this.username, true);
        StringBuilderUtils.addFieldToBuilder(builder, "password", this.password, true);
        StringBuilderUtils.addFieldToBuilder(builder, "role", this.role, false);
        builder.append("}");
        return builder.toString();
    }
}
