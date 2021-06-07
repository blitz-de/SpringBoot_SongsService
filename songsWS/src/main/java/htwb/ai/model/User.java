package htwb.ai.model;

import java.io.Serializable;

import javax.persistence.*;
@Table(name="users")
@Entity
public class User implements Serializable {
	
    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        return "User [userId=" + this.userId + ", firstname=" + this.firstName + ", lastname=" + this.lastName + "]";
    }
    @Id
    @Column(name = "userId", length = 50, nullable = false)
    private String userId;
    @Column(name = "firstName", length = 50, nullable = false)
    private String firstName;
    @Column(name = "lastName", length = 50, nullable = false)
    private String lastName;
    @Column(name = "password", length = 50, nullable = false)
    private String password;

    private User(Builder builder) {
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.password = builder.password;
    }

    public User() {}

    public User(String userId, String password, String firstName, String lastName) {
        this.userId = userId;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }



    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userid) {
        this.userId = userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Creates builder to build {@link User}.
     * @return created builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link User}.
     */
    public static final class Builder {

        private String userId;
        private String firstName;
        private String lastName;
        private String password;

        private Builder() {
        }



        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withFirstName(String firstname) {
            this.firstName = firstname;
            return this;
        }

        public Builder withLastName(String lastname) {
            this.lastName = lastname;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
        
    }
}
