package in.anees.dependencyinjectionexample.common.dependencyinjection.application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.anees.dependencyinjectionexample.Constants;
import in.anees.dependencyinjectionexample.networking.StackoverflowApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkingModule {

    @Singleton
    @Provides
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    StackoverflowApi getStackoverflowApi(Retrofit retrofit) {
        return retrofit.create(StackoverflowApi.class);
    }
}
