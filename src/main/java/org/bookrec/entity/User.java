package org.bookrec.entity;

/**
 * 用户类
 *
 * @author a1311
 */
public class User {
    /**
     * id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户类型
     */
    private Integer type;
    /**
     * 是否为已激活用户
     */
    private Integer isActive;

    /**
     * constructors
     */

    public User() {

    }

    public User(String username, String password, Integer type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(String username, String password, Integer type, Integer isActive) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.isActive = isActive;
    }

    /**
     * getters and setters
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", isActive=" + isActive +
                '}';
    }
}
