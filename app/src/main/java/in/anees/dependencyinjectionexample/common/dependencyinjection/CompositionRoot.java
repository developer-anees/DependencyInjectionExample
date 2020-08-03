package in.anees.dependencyinjectionexample.common.dependencyinjection;

import androidx.annotation.UiThread;

import in.anees.dependencyinjectionexample.Constants;

import in.anees.dependencyinjectionexample.networking.StackoverflowApi;
import in.anees.dependencyinjectionexample.questions.FetchQuestionDetailsUseCase;
import in.anees.dependencyinjectionexample.questions.FetchQuestionsListUseCase;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@UiThread
public class CompositionRoot {

    private Retrofit mRetrofit;
    private StackoverflowApi mStackoverflowApi;

    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    private StackoverflowApi getStackoverflowApi() {
        if (mStackoverflowApi == null) {
            mStackoverflowApi = getRetrofit().create(StackoverflowApi.class);
        }
        return mStackoverflowApi;
    }

    public FetchQuestionsListUseCase getFetchQuestionsListUseCase() {
        return new FetchQuestionsListUseCase(getStackoverflowApi());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase() {
        return new FetchQuestionDetailsUseCase(getStackoverflowApi());
    }

}
