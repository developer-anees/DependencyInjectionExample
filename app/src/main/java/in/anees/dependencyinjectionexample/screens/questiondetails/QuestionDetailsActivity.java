 package in.anees.dependencyinjectionexample.screens.questiondetails;

 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;

 import androidx.lifecycle.ViewModelProvider;

 import javax.inject.Inject;

 import in.anees.dependencyinjectionexample.questions.QuestionDetails;
 import in.anees.dependencyinjectionexample.screens.common.activities.BaseActivity;
 import in.anees.dependencyinjectionexample.screens.common.dialogs.DialogsManager;
 import in.anees.dependencyinjectionexample.screens.common.dialogs.ServerErrorDialogFragment;
 import in.anees.dependencyinjectionexample.screens.common.mvcviews.ViewMvcFactory;
 import in.anees.dependencyinjectionexample.screens.common.viewmodel.ViewModelFactory;


 public class QuestionDetailsActivity extends BaseActivity implements
         QuestionDetailsViewMvc.Listener, QuestionDetailsViewModel.Listener {

     public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

     public static void start(Context context, String questionId) {
         Intent intent = new Intent(context, QuestionDetailsActivity.class);
         intent.putExtra(EXTRA_QUESTION_ID, questionId);
         context.startActivity(intent);
     }

     @Inject
     DialogsManager mDialogsManager;
     @Inject
     ViewMvcFactory mViewMvcFactory;
     @Inject
     ViewModelFactory mViewModelFactory;

     private String mQuestionId;

     private QuestionDetailsViewMvc mViewMvc;

     private QuestionDetailsViewModel viewModel;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         getInjector().inject(this);

         mViewMvc = mViewMvcFactory.newInstance(QuestionDetailsViewMvc.class, null);

         setContentView(mViewMvc.getRootView());

         viewModel = new ViewModelProvider(this, mViewModelFactory).get(QuestionDetailsViewModel.class);

         //noinspection ConstantConditions
         mQuestionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);
     }

     @Override
     protected void onStart() {
         super.onStart();
         mViewMvc.registerListener(this);
         viewModel.registerListener(this);

         viewModel.fetchQuestionDetailsAndNotify(mQuestionId);
     }

     @Override
     protected void onStop() {
         super.onStop();
         mViewMvc.unregisterListener(this);
         viewModel.unregisterListener(this);
     }

     @Override
     public void onQuestionDetailsFetched(QuestionDetails questionDetails) {
         mViewMvc.bindQuestion(questionDetails);
     }

     @Override
     public void onQuestionDetailsFetchFailed() {
         mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "");
     }
 }
