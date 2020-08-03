package in.anees.dependencyinjectionexample.common.dependencyinjection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import in.anees.dependencyinjectionexample.questions.FetchQuestionDetailsUseCase;
import in.anees.dependencyinjectionexample.questions.FetchQuestionsListUseCase;
import in.anees.dependencyinjectionexample.screens.common.dialogs.DialogsManager;
import in.anees.dependencyinjectionexample.screens.common.mvcviews.ViewMvcFactory;

public class Injector {

    private final PresentationCompositionRoot mPresentationCompositionRoot;

    public Injector(PresentationCompositionRoot presentationCompositionRoot) {
        mPresentationCompositionRoot = presentationCompositionRoot;
    }

    public void inject(Object client) {
        Class clazz = client.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field);
            }
        }
    }

    private boolean isAnnotatedForInjection(Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();

        for(Annotation annotation : annotations){
            if(annotation instanceof Service){
                return true;
            }
        }

        return false;
    }

    private void injectField(Object client, Field field) {
        try {
            boolean isAccessibleInitially = field.isAccessible();
            field.setAccessible(true);
            field.set(client, getServiceForClass(field.getType()));
            field.setAccessible(isAccessibleInitially);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getServiceForClass(Class<?> type) {
        if (type.equals(DialogsManager.class)) {
            return mPresentationCompositionRoot.getDialogsManager();
        }
        else if (type.equals(ViewMvcFactory.class)) {
            return mPresentationCompositionRoot.getViewMvcFactory();
        }
        else if (type.equals(FetchQuestionsListUseCase.class)) {
            return mPresentationCompositionRoot.getFetchQuestionsListUseCase();
        }
        else if (type.equals(FetchQuestionDetailsUseCase.class)) {
            return mPresentationCompositionRoot.getFetchQuestionDetailsUseCase();
        }
        else {
            throw new RuntimeException("unsupported service type class: " + type);
        }
    }


}
