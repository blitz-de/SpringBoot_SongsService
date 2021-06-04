package htwb.ai.model;

import java.io.Serializable;

import javax.persistence.*;
@Table(name="users")
@Entity
public class User implements Serializable {
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique =true)
    private int id;
    @Override
    public String toString() {
        return "User [id=" + id + ", userId=" + username + ", firstname=" + firstname + ", lastname=" + lastname + "]";
    }
    
    @Column(name = "username", length = 100, nullable = false)
    private String username;
    @Column(name = "firstname", length = 100, nullable = false)
    private String firstname;
    @Column(name = "lastname", length = 100, nullable = false)
    private String lastname;
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.password = builder.password;
    }

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userid) {
        this.username = userid;
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
        private int id;
        private String username;
        private String firstname;
        private String lastname;
        private String password;

        private Builder() {
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withUserId(String userId) {
            this.username = userId;
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

        public User build() {
            return new User(this);
        }
        
    }
}
