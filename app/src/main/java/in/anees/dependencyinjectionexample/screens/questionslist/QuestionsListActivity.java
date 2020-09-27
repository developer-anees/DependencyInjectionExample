 package in.anees.dependencyinjectionexample.screens.questionslist;

 import android.os.Bundle;

 import androidx.lifecycle.ViewModelProvider;

 import java.util.List;

 import javax.inject.Inject;

 import in.anees.dependencyinjectionexample.questions.FetchQuestionsListUseCase;
 import in.anees.dependencyinjectionexample.questions.Question;
 import in.anees.dependencyinjectionexample.screens.common.activities.BaseActivity;
 import in.anees.dependencyinjectionexample.screens.common.dialogs.DialogsManager;
 import in.anees.dependencyinjectionexample.screens.common.dialogs.ServerErrorDialogFragment;
 import in.anees.dependencyinjectionexample.screens.common.mvcviews.ViewMvcFactory;
 import in.anees.dependencyinjectionexample.screens.common.viewmodel.ViewModelFactory;
 import in.anees.dependencyinjectionexample.screens.questiondetails.QuestionDetailsActivity;

 public class QuestionsListActivity extends BaseActivity implements
         QuestionsListViewMvc.Listener, FetchQuestionsListUseCase.Listener {

     private static final int NUM_OF_QUESTIONS_TO_FETCH = 20;

     @Inject
     FetchQuestionsListUseCase mFetchQuestionsListUseCase;
     @Inject
     DialogsManager mDialogsManager;
     @Inject
     ViewMvcFactory mViewMvcFactory;
     @Inject
     ViewModelFactory viewModelFactory;

     private QuestionsListViewMvc mViewMvc;

     private QuestionsListViewModel mQuestionsListViewModel;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         getInjector().inject(this);

         mViewMvc = mViewMvcFactory.newInstance(QuestionsListViewMvc.class, null);

         mQuestionsListViewModel = new ViewModelProvider(this, viewModelFactory).get(QuestionsListViewModel.class);

         setContentView(mViewMvc.getRootView());

     }

     @Override
     protected void onStart() {
         super.onStart();
         mViewMvc.registerListener(this);
         mFetchQuestionsListUseCase.registerListener(this);

         if (mQuestionsListViewModel.getQuestions().isEmpty()) {
             mFetchQuestionsListUseCase.fetchLastActiveQuestionsAndNotify(NUM_OF_QUESTIONS_TO_FETCH);
         } else {
             mViewMvc.bindQuestions(mQuestionsListViewModel.getQuestions());
         }
     }

     @Override
     protected void onStop() {
         super.onStop();
         mViewMvc.unregisterListener(this);
         mFetchQuestionsListUseCase.unregisterListener(this);
     }

     @Override
     public void onFetchOfQuestionsSucceeded(List<Question> questions) {
         mQuestionsListViewModel.setQuestions(questions);
         mViewMvc.bindQuestions(questions);
     }

     @Override
     public void onFetchOfQuestionsFailed() {
         mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "");
     }

     @Override
     public void onQuestionClicked(Question question) {
         QuestionDetailsActivity.start(QuestionsListActivity.this, question.getId());
     }
 }
