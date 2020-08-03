package in.anees.dependencyinjectionexample.screens.questiondetails;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import in.anees.dependencyinjectionexample.R;
import in.anees.dependencyinjectionexample.questions.QuestionDetails;
import in.anees.dependencyinjectionexample.screens.common.ImageLoader;
import in.anees.dependencyinjectionexample.screens.common.mvcviews.BaseViewMvc;

public class QuestionDetailsViewMvcImpl extends BaseViewMvc<QuestionDetailsViewMvc.Listener>
        implements QuestionDetailsViewMvc {

    private final ImageLoader mImageLoader;

    private final TextView mTxtQuestionBody;

    private final TextView mTxtUserDisplayName;
    private final ImageView mImgUserAvatar;

    public QuestionDetailsViewMvcImpl(LayoutInflater inflater, ViewGroup container, ImageLoader imageLoader) {
        mImageLoader = imageLoader;
        setRootView(inflater.inflate(R.layout.layout_question_details, container, false));

        mTxtQuestionBody = findViewById(R.id.txt_question_body);
        mTxtUserDisplayName = findViewById(R.id.txt_user_display_name);
        mImgUserAvatar = findViewById(R.id.img_user_avatar);
    }

    @Override
    public void bindQuestion(QuestionDetails question) {
        String questionBody = question.getBody();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mTxtQuestionBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
        } else {
            mTxtQuestionBody.setText(Html.fromHtml(questionBody));
        }

        mTxtUserDisplayName.setText(question.getUserDisplayName());
        mImageLoader.loadImage(question.getUserAvatarUrl(), mImgUserAvatar);
    }
}
