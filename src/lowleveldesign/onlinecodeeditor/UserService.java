package lowleveldesign.onlinecodeeditor;

interface UserService {
    User Āregister(String username, String email, String password);

    User login(String username, String password);
}

