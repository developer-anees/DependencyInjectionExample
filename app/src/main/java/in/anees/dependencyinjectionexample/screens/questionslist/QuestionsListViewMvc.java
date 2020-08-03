package in.anees.dependencyinjectionexample.screens.questionslist;

import java.util.List;

import in.anees.dependencyinjectionexample.questions.Question;
import in.anees.dependencyinjectionexample.screens.common.mvcviews.ObservableViewMvc;

public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {

    interface Listener {
        void onQuestionClicked(Question question);
    }

    void bindQuestions(List<Question> questions);
}
