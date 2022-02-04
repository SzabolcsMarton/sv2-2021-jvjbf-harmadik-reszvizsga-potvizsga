package contentsite;

public class ContentHelpers {

    public int hashPassword(String name, String pass) {
        String passToHash = name + pass;
        return passToHash.hashCode();

    }
}
