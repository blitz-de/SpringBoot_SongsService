package htwb.ai.model;

public class User {


    private int id;
    @Override
    public String toString() {
        return "User [id=" + id + ", userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + "]";
    }

    private String userId;
    private String firstname;
    private String lastname;
    private String password;

    private User(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.password = builder.password;
    }

    public User() {}

    public User(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userid) {
        this.userId = userid;
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
        private String userId;
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
            this.userId = userId;
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
