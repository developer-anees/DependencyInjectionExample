 package in.anees.dependencyinjectionexample.screens.questionslist;

 import android.os.Bundle;
 import java.util.List;

 import javax.inject.Inject;

 import in.anees.dependencyinjectionexample.questions.FetchQuestionsListUseCase;
 import in.anees.dependencyinjectionexample.questions.Question;
 import in.anees.dependencyinjectionexample.screens.common.activities.BaseActivity;
 import in.anees.dependencyinjectionexample.screens.common.dialogs.DialogsManager;
 import in.anees.dependencyinjectionexample.screens.common.dialogs.ServerErrorDialogFragment;
 import in.anees.dependencyinjectionexample.screens.common.mvcviews.ViewMvcFactory;
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

     private QuestionsListViewMvc mViewMvc;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         getInjector().inject(this);

         mViewMvc = mViewMvcFactory.newInstance(QuestionsListViewMvc.class, null);

         setContentView(mViewMvc.getRootView());

     }

     @Override
     protected void onStart() {
         super.onStart();
         mViewMvc.registerListener(this);
         mFetchQuestionsListUseCase.registerListener(this);

         mFetchQuestionsListUseCase.fetchLastActiveQuestionsAndNotify(NUM_OF_QUESTIONS_TO_FETCH);
     }

     @Override
     protected void onStop() {
         super.onStop();
         mViewMvc.unregisterListener(this);
         mFetchQuestionsListUseCase.unregisterListener(this);
     }

     @Override
     public void onFetchOfQuestionsSucceeded(List<Question> questions) {
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
