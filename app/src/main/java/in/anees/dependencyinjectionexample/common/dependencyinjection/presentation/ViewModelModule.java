package in.anees.dependencyinjectionexample.common.dependencyinjection.presentation;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import in.anees.dependencyinjectionexample.questions.FetchQuestionDetailsUseCase;
import in.anees.dependencyinjectionexample.screens.common.viewmodel.ViewModelFactory;
import in.anees.dependencyinjectionexample.screens.questiondetails.QuestionDetailsViewModel;
import in.anees.dependencyinjectionexample.screens.questionslist.QuestionsListViewModel;

@Module
public class ViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(QuestionDetailsViewModel.class)
    ViewModel questionDetailsViewModel(FetchQuestionDetailsUseCase fetchQuestionDetailsUseCase) {
        return new QuestionDetailsViewModel(fetchQuestionDetailsUseCase);
    }

    @Provides
    @IntoMap
    @ViewModelKey(QuestionsListViewModel.class)
    ViewModel questionsListViewModel() {
        return new QuestionsListViewModel();
    }
}
