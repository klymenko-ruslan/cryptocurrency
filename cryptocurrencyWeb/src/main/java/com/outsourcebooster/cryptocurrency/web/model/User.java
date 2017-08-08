package com.outsourcebooster.cryptocurrency.web.model;

import com.outsourcebooster.cryptocurrency.web.config.security.constant.SecurityRole;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.Date;

/**
 * Created by rklimemnko on 29.05.2016.
 */
public class User {
    @Id
    private String id;
    private Date createdDate;
    private String password;
    private String firstName;
    private String lastName;
    private String profession;
    private String email;
    private boolean isActive;
    private UserCurrency userCurrency;
    private boolean enableNotification;
    private String imageFileName;
    private Collection<NotificationRule> notificationRules;
    private Collection<String> roles;
    private Wallet wallet;
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profession='" + profession + '\'' +
                ", enableNotification=" + enableNotification +
                ", roles=" + roles +
                ", wallet=" + wallet +
                '}';
    }

    public User() {}

    public User(String username, String password, Collection<String> roles) {
        this.password = password;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public boolean isEnableNotification() {
        return enableNotification;
    }

    public void setEnableNotification(boolean enableNotification) {
        this.enableNotification = enableNotification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Collection<NotificationRule> getNotificationRules() {
        return notificationRules;
    }

    public void setNotificationRules(Collection<NotificationRule> notificationRules) {
        this.notificationRules = notificationRules;
    }

    public UserCurrency getUserCurrency() {
        return userCurrency;
    }

    public void setUserCurrency(UserCurrency userCurrency) {
        this.userCurrency = userCurrency;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
