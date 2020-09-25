import java.util.ArrayList;

public class Profile {
    private String username;
    private String password; //not sure if we want this...
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String profilePhoto; //TODO change when profile photo storage is figured out
    private ArrayList<Hive> hives;
    private ArrayList<Apiary> apiaries;
    private ArrayList<Profile> friends;

    public void addFriend(Profile user) {

    }

    public void addHive(Hive hive) {

    }

    public void addApiary(Apiary apiary) {

    }

    public void changePassword(String password) {
        //TODO not sure if we want this...
    }

    public void changePhone(String phone) {

    }

    public void deleteFriend(Profile user) {

    }

    public void deleteHive(Hive hive) {

    }

    public void deleteApiary(Apiary apiary) {}

}
