package in.anees.dependencyinjectionexample.screens.common;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoader {

    private final Activity mActivity;

    private final RequestOptions mDefaultRequestOptions;

    public ImageLoader(Activity activity) {
        mActivity = activity;

        mDefaultRequestOptions = new RequestOptions()
                .centerCrop();
    }

    public void loadImage(String uri, ImageView target) {
        Glide.with(mActivity).load(uri).apply(mDefaultRequestOptions).into(target);
    }

}
