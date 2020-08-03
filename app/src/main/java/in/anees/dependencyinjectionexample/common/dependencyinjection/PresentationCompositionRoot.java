package in.anees.dependencyinjectionexample.common.dependencyinjection;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import in.anees.dependencyinjectionexample.questions.FetchQuestionDetailsUseCase;
import in.anees.dependencyinjectionexample.questions.FetchQuestionsListUseCase;
import in.anees.dependencyinjectionexample.screens.common.ImageLoader;
import in.anees.dependencyinjectionexample.screens.common.dialogs.DialogsManager;
import in.anees.dependencyinjectionexample.screens.common.mvcviews.ViewMvcFactory;


public class PresentationCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final AppCompatActivity mActivity;

    public PresentationCompositionRoot(CompositionRoot compositionRoot,
                                       AppCompatActivity activity) {
        mCompositionRoot = compositionRoot;
        mActivity = activity;
    }

    private FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    private Activity getActivity() {
        return mActivity;
    }

    public DialogsManager getDialogsManager() {
        return new DialogsManager(getFragmentManager());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase() {
        return mCompositionRoot.getFetchQuestionDetailsUseCase();
    }

    public FetchQuestionsListUseCase getFetchQuestionsListUseCase() {
        return mCompositionRoot.getFetchQuestionsListUseCase();
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater(), getImageLoader());
    }

    private ImageLoader getImageLoader() {
        return new ImageLoader(getActivity());
    }
}
