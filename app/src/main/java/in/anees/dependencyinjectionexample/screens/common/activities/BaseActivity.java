package in.anees.dependencyinjectionexample.screens.common.activities;


import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import in.anees.dependencyinjectionexample.MyApplication;
import in.anees.dependencyinjectionexample.common.dependencyinjection.CompositionRoot;
import in.anees.dependencyinjectionexample.common.dependencyinjection.Injector;
import in.anees.dependencyinjectionexample.common.dependencyinjection.PresentationCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private boolean mIsInjectorUsed;

    @UiThread
    protected Injector getInjector() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("there is no need to use injector more than once");
        }
        mIsInjectorUsed = true;
        return new Injector(getCompositionRoot());
    }

    private PresentationCompositionRoot getCompositionRoot() {
        return new PresentationCompositionRoot(getAppCompositionRoot(),this);
    }

    private CompositionRoot getAppCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}
