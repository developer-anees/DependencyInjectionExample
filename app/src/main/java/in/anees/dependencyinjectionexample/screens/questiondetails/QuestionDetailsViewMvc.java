package in.anees.dependencyinjectionexample.screens.questiondetails;

import in.anees.dependencyinjectionexample.questions.QuestionDetails;
import in.anees.dependencyinjectionexample.screens.common.mvcviews.ObservableViewMvc;

public interface QuestionDetailsViewMvc extends ObservableViewMvc<QuestionDetailsViewMvc.Listener> {

    interface Listener {
        // currently no user actions
    }

    void bindQuestion(QuestionDetails question);
}
