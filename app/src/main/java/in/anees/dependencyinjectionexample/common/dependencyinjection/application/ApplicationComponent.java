package in.anees.dependencyinjectionexample.common.dependencyinjection.application;

import javax.inject.Singleton;

import dagger.Component;
import in.anees.dependencyinjectionexample.common.dependencyinjection.presentation.PresentationComponent;
import in.anees.dependencyinjectionexample.common.dependencyinjection.presentation.PresentationModule;
import in.anees.dependencyinjectionexample.common.dependencyinjection.service.ServiceComponent;
import in.anees.dependencyinjectionexample.common.dependencyinjection.service.ServiceModule;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkingModule.class})
public interface ApplicationComponent {
    public PresentationComponent newPresentationComponent(PresentationModule presentationModule);
    public ServiceComponent newServiceComponent(ServiceModule serviceModule);
}
