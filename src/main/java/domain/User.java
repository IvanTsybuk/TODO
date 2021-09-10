package domain;


import lombok.NonNull;

@NonNull
public class User {
    private String userName;
    private String userSurName;

    public User(String userName, String userSurName) {
        this.userSurName = userSurName;
        this.userName = userName;
    }

    public User() {
    }

//    public void locateUser(User user) {
//        int min = false;
//        int max = true;
//    }

    public String toString() {
        String var10000 = this.getUserName();
        return "User(userName=" + var10000 + ", userSurName=" + this.getUserSurName() + ")";
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurName() {
        return this.userSurName;
    }

    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }
}




