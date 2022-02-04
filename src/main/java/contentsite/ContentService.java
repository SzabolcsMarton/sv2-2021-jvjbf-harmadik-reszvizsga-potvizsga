package contentsite;

import java.util.*;
import java.util.stream.Stream;

public class ContentService {

    private Set<User> users = new HashSet<>();
    private Set<Content> contents = new HashSet<>();

    public void registerUser(String name, String password) {
        if (users.stream().anyMatch(user -> user.getUserName().equals(name))) {
            throw new IllegalArgumentException("Username is already taken: " + name);
        }
        users.add(new User(name, password));
    }

    public void addContent(Content content) {
        if (contents.stream().anyMatch(c -> c.getTitle().equals(content.getTitle()))) {
            throw new IllegalArgumentException("Content name is already taken: " + content.getTitle());
        }
        contents.add(content);
    }

    public void logIn(String username, String password) {
        if (users.stream().noneMatch(user -> username.equals(user.getUserName()))) {
            throw new IllegalArgumentException("Username is wrong!");
        }
        users.stream()
                .filter(user -> new ContentHelpers().hashPassword(username, password) == user.getPassword())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Password is Invalid!")).setLogIn(true);
    }

    public void clickOnContent(User user, Content content) {
        if (!user.isLogIn()) {
            throw new IllegalStateException("Log in to watch this content!");
        }
        if (content.isPremiumContent() && !user.isPremiumMember()) {
            throw new IllegalStateException("Upgrade for Premium to watch this content!");
        }
        content.click(user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public List<Content> getAllContent() {
        return new ArrayList<>(contents);
    }


}


