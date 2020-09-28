package in.anees.dependencyinjectionexample.screens.questiondetails;

import androidx.lifecycle.ViewModel;

import java.util.HashSet;
import java.util.Set;

import in.anees.dependencyinjectionexample.questions.FetchQuestionDetailsUseCase;
import in.anees.dependencyinjectionexample.questions.QuestionDetails;

public class QuestionDetailsViewModel extends ViewModel implements FetchQuestionDetailsUseCase.Listener {

    public interface Listener {
        void onQuestionDetailsFetched(QuestionDetails questionDetails);
        void onQuestionDetailsFetchFailed();
    }

    private final FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;
    private QuestionDetails mQuestionDetails;

    private Set<Listener> listeners = new HashSet<>();

    public QuestionDetailsViewModel(FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase) {
        this.mFetchQuestionDetailsUseCase = mFetchQuestionDetailsUseCase;
        this.mFetchQuestionDetailsUseCase.registerListener(this);
    }

    public void fetchQuestionDetailsAndNotify(String questionId) {
        if (mQuestionDetails == null) {
            mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId);
        } else {
            notifySuccess(mQuestionDetails);
        }
    }

    private void notifySuccess(QuestionDetails questionDetails) {
        for (Listener listener : listeners) {
            listener.onQuestionDetailsFetched(questionDetails);
        }
    }

    private void notifyFailure() {
        for (Listener listener : listeners) {
            listener.onQuestionDetailsFetchFailed();
        }
    }

    @Override
    public void onFetchOfQuestionDetailsSucceeded(QuestionDetails question) {
        mQuestionDetails = question;
        notifySuccess(question);
    }

    @Override
    public void onFetchOfQuestionDetailsFailed() {
        notifyFailure();
    }

    public void registerListener(Listener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mFetchQuestionDetailsUseCase.unregisterListener(this);
    }
}
