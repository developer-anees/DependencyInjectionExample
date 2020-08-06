package in.anees.dependencyinjectionexample.screens.common.service;

import android.app.Service;

import androidx.annotation.UiThread;

import in.anees.dependencyinjectionexample.MyApplication;
import in.anees.dependencyinjectionexample.common.dependencyinjection.application.ApplicationComponent;
import in.anees.dependencyinjectionexample.common.dependencyinjection.service.ServiceComponent;
import in.anees.dependencyinjectionexample.common.dependencyinjection.service.ServiceModule;

public abstract class BaseService extends Service {
    private boolean mIsInjectorUsed;

    @UiThread
    protected ServiceComponent getInjector() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("There is no need to use injector more than once");
        }
        mIsInjectorUsed = true;
        return getApplicationComponent().newServiceComponent(new ServiceModule(this));
    }

    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }
}
