package in.anees.dependencyinjectionexample.screens.common.fragments;

import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;

import in.anees.dependencyinjectionexample.MyApplication;
import in.anees.dependencyinjectionexample.common.dependencyinjection.application.ApplicationComponent;
import in.anees.dependencyinjectionexample.common.dependencyinjection.presentation.PresentationComponent;
import in.anees.dependencyinjectionexample.common.dependencyinjection.presentation.PresentationModule;

public class BaseFragment extends Fragment {
    private boolean mIsInjectorUsed;

    @UiThread
    protected PresentationComponent getInjector() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        mIsInjectorUsed = true;
        return getApplicationComponent().newPresentationComponent(new PresentationModule(getActivity()));
    }

    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getActivity().getApplication()).getApplicationComponent();
    }
}
