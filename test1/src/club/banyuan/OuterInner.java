package club.banyuan;

public class OuterInner
{

    public static void main(String[] args) {
        check(((username, password) -> {
            return false;
        }));
    }

    public static boolean check(UserService userService){
        return false;
    }
}


interface UserService {

    public boolean checkLogin(String username, String password);

}