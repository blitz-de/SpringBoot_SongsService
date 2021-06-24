package htwb.ai.model;

import javax.persistence.*;

@Table(name = "users")
@Entity
public class DAOUser {

//    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "username", length = 50, nullable = false)
    private String username;
    @Column(length = 50, nullable = false)
    private String firstname;
    @Column(length = 50, nullable = false)
    private String lastname;
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    private DAOUser(Builder builder) {
        this.username = builder.username;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.password = builder.password;
    }

    public DAOUser() {
    }

    public DAOUser(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public DAOUser(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }



    @Override
    public String toString() {
        return "User [username=" + this.username + ", firstname=" + this.firstname + ", lastname=" + this.lastname + "]";
    }

    /**
     * Creates builder to build {@link DAOUser}.
     *
     * @return created builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link DAOUser}.
     */
    public static final class Builder {

        private String username;
        private String firstname;
        private String lastname;
        private String password;

        private Builder() {
        }


        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public DAOUser build() {
            return new DAOUser(this);
        }

    }
}
