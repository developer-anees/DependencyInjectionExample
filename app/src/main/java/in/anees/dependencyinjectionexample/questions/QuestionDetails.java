package in.anees.dependencyinjectionexample.questions;

public class QuestionDetails {

    private final String mId;

    private final String mTitle;

    private final String mBody;

    private final String mUserDisplayName;

    private final String mUserAvatarUrl;

    public QuestionDetails(String id, String title, String body, String userDisplayName, String userAvatarUrl) {
        mId = id;
        mTitle = title;
        mBody = body;
        mUserDisplayName = userDisplayName;
        mUserAvatarUrl = userAvatarUrl;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public String getUserDisplayName() {
        return mUserDisplayName;
    }

    public String getUserAvatarUrl() {
        return mUserAvatarUrl;
    }
}
