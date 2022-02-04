package contentsite;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Video implements Content {

    private static final int PREMIUM_LIMIT = 15;
    private final int length;
    private final String title;
    private List<User> clickedBy = new ArrayList<>();


    public Video(String title, int length) {
        this.length = length;
        this.title = title;
    }


    @Override
    public boolean isPremiumContent() {
        return this.length > PREMIUM_LIMIT;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<User> clickedBy() {
        return new ArrayList<>(clickedBy);
    }

    @Override
    public void click(User user) {
        clickedBy.add(user);
    }
}
