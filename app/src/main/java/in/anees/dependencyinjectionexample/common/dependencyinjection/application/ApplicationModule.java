package in.anees.dependencyinjectionexample.common.dependencyinjection.application;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import in.anees.dependencyinjectionexample.networking.StackoverflowApi;
import in.anees.dependencyinjectionexample.questions.FetchQuestionsListUseCase;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    FetchQuestionsListUseCase getFetchQuestionsListUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionsListUseCase(stackoverflowApi);
    }
}
