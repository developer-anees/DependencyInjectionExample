package in.anees.dependencyinjectionexample.screens.common.activities;


import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import in.anees.dependencyinjectionexample.MyApplication;
import in.anees.dependencyinjectionexample.common.dependencyinjection.application.ApplicationComponent;
import in.anees.dependencyinjectionexample.common.dependencyinjection.presentation.PresentationComponent;
import in.anees.dependencyinjectionexample.common.dependencyinjection.presentation.PresentationModule;

public class BaseActivity extends AppCompatActivity {

    private boolean mIsInjectorUsed;

    @UiThread
    protected PresentationComponent getInjector() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        mIsInjectorUsed = true;
        return getApplicationComponent().newPresentationComponent(new PresentationModule(this));
    }

    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }
}
