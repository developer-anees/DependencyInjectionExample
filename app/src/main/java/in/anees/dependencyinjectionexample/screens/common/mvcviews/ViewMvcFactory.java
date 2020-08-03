package in.anees.dependencyinjectionexample.screens.common.mvcviews;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import in.anees.dependencyinjectionexample.screens.common.ImageLoader;
import in.anees.dependencyinjectionexample.screens.questiondetails.QuestionDetailsViewMvc;
import in.anees.dependencyinjectionexample.screens.questiondetails.QuestionDetailsViewMvcImpl;
import in.anees.dependencyinjectionexample.screens.questionslist.QuestionsListViewMvc;
import in.anees.dependencyinjectionexample.screens.questionslist.QuestionsListViewMvcImpl;


public class ViewMvcFactory {

    private final LayoutInflater mLayoutInflater;
    private ImageLoader mImageLoader;

    public ViewMvcFactory(LayoutInflater layoutInflater, ImageLoader imageLoader) {
        mLayoutInflater = layoutInflater;
        mImageLoader = imageLoader;
    }

    /**
     * Instantiate a new implementation of MVC view. The returned instance will be casted to MVC view
     * type inferred by java's automatic type inference.
     * @param mvcViewClass the class of the required MVC view
     * @param container this container will be used as MVC view's parent. See {@link LayoutInflater#inflate(int, ViewGroup)}
     * @param <T> the type of the required MVC view
     * @return new instance of MVC view
     */
    public <T extends ViewMvc> T newInstance(Class<T> mvcViewClass, @Nullable ViewGroup container) {

        ViewMvc viewMvc;

        if (mvcViewClass == QuestionsListViewMvc.class) {
            viewMvc = new QuestionsListViewMvcImpl(mLayoutInflater, container);
        }
        else if (mvcViewClass == QuestionDetailsViewMvc.class) {
            viewMvc = new QuestionDetailsViewMvcImpl(mLayoutInflater, container, mImageLoader);
        }
        else {
            throw new IllegalArgumentException("unsupported MVC view class " + mvcViewClass);
        }

        //noinspection unchecked
        return (T) viewMvc;
    }

}
