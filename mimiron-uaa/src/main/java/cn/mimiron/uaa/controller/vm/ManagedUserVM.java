package cn.mimiron.uaa.controller.vm;

import cn.mimiron.uaa.service.dto.UserDTO;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 *
 * @author zhangxd
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public ManagedUserVM(Long id, String login, String password, String firstName, String lastName,
                         String email, boolean activated, String imageUrl,
                         Date gmtCreate, Date gmtModified, Set<String> authorities) {

        super(id, login, firstName, lastName, email, activated, imageUrl,
            gmtCreate, gmtModified, authorities);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{" +
            "} " + super.toString();
    }
}
