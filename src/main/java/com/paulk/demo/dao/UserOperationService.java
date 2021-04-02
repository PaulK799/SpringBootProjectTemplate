package com.paulk.demo.dao;

import com.paulk.demo.domain.model.UserOperationResponse;
import com.paulk.demo.domain.model.dto.Role;
import com.paulk.demo.domain.model.dto.User;
import com.paulk.demo.repository.RoleRepository;
import com.paulk.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

/**
 * A {@link Service} which implements {@link UserDetailsService}.
 */
@Service
public class UserOperationService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserOperationService.class);

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    /**
     * Override the {@link UserDetailsService#loadUserByUsername(String)} method.
     *
     * @param username - The {@link String} username of the {@link User}.
     * @return The {@link User} found.
     * @throws UsernameNotFoundException a {@link UsernameNotFoundException} exception is {@link User} not found.
     */
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityExistsException("User " + username + " doesn't exist"));
    }

    /**
     * Override the {@link UserDetailsService#loadUserByUsername(String)} method.
     *
     * @param name - The {@link String} name of the {@link Role}.
     * @return The {@link User} found.
     * @throws UsernameNotFoundException a {@link UsernameNotFoundException} exception is {@link Role} not found.
     */
    public Role loadRoleByRoleName(String name) throws UsernameNotFoundException {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new EntityExistsException("Role " + name + " doesn't exist"));
    }

    /**
     * Attempts to create a {@link User}.
     *
     * @param user - The {@link User} to be saved.
     * @return The {@link UserOperationResponse} indicating if the operation was successful.
     */
    public UserOperationResponse createUser(User user) {
        LOGGER.debug("Start Debugging (Create User) ---- ");
        UserOperationResponse createUserOperationResponse = new UserOperationResponse();
        UserOperationResponse getOperationResponse = get(user);
        if (!getOperationResponse.isSuccessfulOperation()) {
            Role retrievedRole = get(user.getRole());
            if (retrievedRole != null) {
                user.setRole(retrievedRole);
                try {
                    User savedUser = userRepository.save(user);
                    createUserOperationResponse.setUser(savedUser);
                    createUserOperationResponse.setSuccessfulOperation(true);
                } catch (Exception exception) {
                    LOGGER.error("Error saving User during the Create User Operation.");
                }
            } else {
                LOGGER.error("Role could not be resolved.");
            }
        }
        LOGGER.debug("End Debugging (Create User) ---- ");
        return createUserOperationResponse;
    }

    /**
     * Get a {@link User} from the data store.
     *
     * @param user - The {@link User} to be retrieved.
     * @return A {@link UserOperationResponse} with a boolean indicating if response returned was successful.
     */
    public UserOperationResponse get(User user) {
        LOGGER.debug("Start Debugging (Find User) ---- ");
        UserOperationResponse userOperationResponse = new UserOperationResponse();
        Optional<String> valueOptional = Optional.ofNullable(user)
                .map(User::getUsername);
        if (valueOptional.isPresent()) {
            try {
                User retrievedUser = loadUserByUsername(valueOptional.get());
                if (retrievedUser != null) {
                    userOperationResponse.setUser(retrievedUser);
                    userOperationResponse.setSuccessfulOperation(true);
                }
            } catch (Exception exception) {
                LOGGER.error("Unable to get User from database.");
            }
        }
        return userOperationResponse;
    }

    /**
     * Get a {@link Role} from the data store.
     *
     * @param role - The {@link Role} to be retrieved.
     * @return A {@link Role} retrieved from the database.
     */
    public Role get(Role role) {
        LOGGER.debug("Start Debugging (Find Role) ---- ");
        Optional<String> nameOptional = Optional.ofNullable(role)
                .map(Role::getName);
        if (nameOptional.isPresent()) {
            try {
                Role retrievedRole = loadRoleByRoleName(nameOptional.get());
                if (retrievedRole != null) {
                    return retrievedRole;
                }
            } catch (Exception exception) {
                LOGGER.error("Unable to get User from database.");
            }
        }
        return null;
    }
}
