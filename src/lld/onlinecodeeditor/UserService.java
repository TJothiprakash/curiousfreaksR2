package lld.onlinecodeeditor;
interface UserService {
    User register(String username, String email, String password);
    User login(String username, String password);
}

