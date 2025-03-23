package bean.user;

public class User {
    private Integer id;
    private String account;
    private String password;
    private String level;
    private String phone;

    public User(String account, String level) {
        this.account = account;
        this.level = level;
    }

    public User(String account, String password, String phone) {
        this.account = account;
        this.password = password;
        this.phone = phone;
    }

    public User(String level) {
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}


