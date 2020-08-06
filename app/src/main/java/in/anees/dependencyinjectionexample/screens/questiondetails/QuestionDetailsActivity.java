 package in.anees.dependencyinjectionexample.screens.questiondetails;

 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;

 import javax.inject.Inject;

 import in.anees.dependencyinjectionexample.questions.FetchQuestionDetailsUseCase;
 import in.anees.dependencyinjectionexample.questions.QuestionDetails;
 import in.anees.dependencyinjectionexample.screens.common.activities.BaseActivity;
 import in.anees.dependencyinjectionexample.screens.common.dialogs.DialogsManager;
 import in.anees.dependencyinjectionexample.screens.common.dialogs.ServerErrorDialogFragment;
 import in.anees.dependencyinjectionexample.screens.common.mvcviews.ViewMvcFactory;


 public class QuestionDetailsActivity extends BaseActivity implements
         QuestionDetailsViewMvc.Listener, FetchQuestionDetailsUseCase.Listener {

     public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

     public static void start(Context context, String questionId) {
         Intent intent = new Intent(context, QuestionDetailsActivity.class);
         intent.putExtra(EXTRA_QUESTION_ID, questionId);
         context.startActivity(intent);
     }

     @Inject
     FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;
     @Inject
     DialogsManager mDialogsManager;
     @Inject
     ViewMvcFactory mViewMvcFactory;

     private String mQuestionId;

     private QuestionDetailsViewMvc mViewMvc;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         getInjector().inject(this);

         mViewMvc = mViewMvcFactory.newInstance(QuestionDetailsViewMvc.class, null);

         setContentView(mViewMvc.getRootView());

         //noinspection ConstantConditions
         mQuestionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);
     }

     @Override
     protected void onStart() {
         super.onStart();
         mViewMvc.registerListener(this);
         mFetchQuestionDetailsUseCase.registerListener(this);

         mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(mQuestionId);
     }

     @Override
     protected void onStop() {
         super.onStop();
         mViewMvc.unregisterListener(this);
         mFetchQuestionDetailsUseCase.unregisterListener(this);
     }

     @Override
     public void onFetchOfQuestionDetailsSucceeded(QuestionDetails question) {
         mViewMvc.bindQuestion(question);
     }

     @Override
     public void onFetchOfQuestionDetailsFailed() {
         mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "");
     }
 }
