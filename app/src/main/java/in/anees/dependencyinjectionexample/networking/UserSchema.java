package in.anees.dependencyinjectionexample.networking;

import com.google.gson.annotations.SerializedName;

public class UserSchema {

    @SerializedName("display_name")
    private final String mUserDisplayName;

    public UserSchema(String userDisplayName, String userAvatarUrl) {
        mUserDisplayName = userDisplayName;
        mUserAvatarUrl = userAvatarUrl;
    }

    @SerializedName("profile_image")
    private final String mUserAvatarUrl;

    public String getUserAvatarUrl() {
        return mUserAvatarUrl;
    }

    public String getUserDisplayName() {
        return mUserDisplayName;
    }
}
