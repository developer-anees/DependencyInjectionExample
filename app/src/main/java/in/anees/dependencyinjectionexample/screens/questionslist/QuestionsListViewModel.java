package in.anees.dependencyinjectionexample.screens.questionslist;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import in.anees.dependencyinjectionexample.questions.Question;

public class QuestionsListViewModel extends ViewModel {
    private List<Question> mQuestions = new ArrayList<>();

    public List<Question> getQuestions() {
        return mQuestions;
    }

    public void setQuestions(List<Question> mQuestions) {
        this.mQuestions = mQuestions;
    }
}
