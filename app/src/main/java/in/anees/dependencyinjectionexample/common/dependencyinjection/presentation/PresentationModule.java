package in.anees.dependencyinjectionexample.common.dependencyinjection.presentation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import in.anees.dependencyinjectionexample.networking.StackoverflowApi;
import in.anees.dependencyinjectionexample.questions.FetchQuestionDetailsUseCase;
import in.anees.dependencyinjectionexample.screens.common.ImageLoader;
import in.anees.dependencyinjectionexample.screens.common.dialogs.DialogsManager;
import in.anees.dependencyinjectionexample.screens.common.mvcviews.ViewMvcFactory;

@Module
public class PresentationModule {
    final FragmentActivity mActivity;

    public PresentationModule(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    @Provides
    LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    @Provides
    Activity getActivity() {
        return mActivity;
    }

    @Provides
    Context context(Activity activity) {
        return activity;
    }

    @Provides
    FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase(StackoverflowApi stackoverflowApi) {
        return new FetchQuestionDetailsUseCase(stackoverflowApi);
    }

    @Provides
    DialogsManager getDialogsManager() {
        return new DialogsManager(getFragmentManager());
    }

    @Provides
    ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater(), getImageLoader());
    }

    @Provides
    ImageLoader getImageLoader() {
        return new ImageLoader(getActivity());
    }
}
