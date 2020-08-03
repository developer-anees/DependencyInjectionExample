package in.anees.dependencyinjectionexample.networking;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class SingleQuestionResponseSchema {

    @SerializedName("items")
    private final List<QuestionSchema> mQuestions;

    public SingleQuestionResponseSchema(QuestionSchema question) {
        mQuestions = Collections.singletonList(question);
    }

    public QuestionSchema getQuestion() {
        return mQuestions.get(0);
    }
}
