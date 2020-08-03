package in.anees.dependencyinjectionexample;

import android.app.Application;

import in.anees.dependencyinjectionexample.common.dependencyinjection.CompositionRoot;

public class MyApplication extends Application {

    private CompositionRoot mCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}
