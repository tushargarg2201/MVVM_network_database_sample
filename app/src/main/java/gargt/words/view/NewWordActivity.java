package gargt.words.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import gargt.words.R;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_MOBILE_NUMBER = "com.example.android.wordlistsql.MOBILE_NUMBER";

    private EditText mEditWordView;
    private EditText mEditMobileNumberView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);
        mEditMobileNumberView = findViewById(R.id.edit_mobile_number);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText()) || TextUtils.isEmpty(String.valueOf(mEditMobileNumberView.getText()))) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    String mobileNumber = mEditMobileNumberView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    replyIntent.putExtra(EXTRA_MOBILE_NUMBER, mobileNumber);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
