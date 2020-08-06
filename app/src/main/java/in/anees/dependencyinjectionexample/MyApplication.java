package in.anees.dependencyinjectionexample;

import android.app.Application;

import in.anees.dependencyinjectionexample.common.dependencyinjection.application.ApplicationComponent;
import in.anees.dependencyinjectionexample.common.dependencyinjection.application.ApplicationModule;
import in.anees.dependencyinjectionexample.common.dependencyinjection.application.DaggerApplicationComponent;

public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
