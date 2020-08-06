package in.anees.dependencyinjectionexample.common.dependencyinjection.presentation;

import dagger.Subcomponent;
import in.anees.dependencyinjectionexample.screens.questiondetails.QuestionDetailsActivity;
import in.anees.dependencyinjectionexample.screens.questionslist.QuestionsListActivity;

@Subcomponent(modules = PresentationModule.class)
public interface PresentationComponent {
    void inject(QuestionsListActivity questionsListActivity);
    void inject(QuestionDetailsActivity questionDetailsActivity);
}
