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

    Profile() {
        username = null;
        password = null;
        firstname = null;
        lastname = null;
        phone = null;
        email = null;
        profilePhoto = null;
        hives = new ArrayList<>();
        apiaries = new ArrayList<>();
        friends = new ArrayList<>();

    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public ArrayList<Hive> getHives() {
        return hives;
    }

    public ArrayList<Apiary> getApiaries() {
        return apiaries;
    }

    public ArrayList<Profile> getFriends() {
        return friends;
    }

    Profile(String username, String password, String firstname, String lastname, String phone,
            String email, String profilePhoto, ArrayList<Hive> hives, ArrayList<Apiary> apiaries,
            ArrayList<Profile> friends) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.profilePhoto = profilePhoto;
        this.hives = hives;
        this.apiaries = apiaries;
        this.friends = friends;

    }

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

    public void deleteApiary(Apiary apiary) {
    }

}
