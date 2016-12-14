package myapp.com.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.odoo.App;
import com.odoo.R;

/**
 * Created by Pham King on 12/4/2016.
 */

public class Register extends AppCompatActivity implements View.OnClickListener,
        View.OnFocusChangeListener{

    private App mApp;
    private Boolean mSelfHostedURL = false;
    private EditText edtUsername, edtPassword, edtSelfHosted;
    private TextView mLoginProcessStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myapp_login);
        mApp = (App) getApplicationContext();
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_myapp_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txvAddSelfHosted:
                toggleSelfHostedURL();
                break;
            case R.id.btnLogin:
                //loginUser();
                startOdooActivity();
                break;
        }
    }

    private void toggleSelfHostedURL() {
        TextView txvAddSelfHosted = (TextView) findViewById(R.id.txvAddSelfHosted);
        if (!mSelfHostedURL) {
            mSelfHostedURL = true;
            findViewById(R.id.layoutSelfHosted).setVisibility(View.VISIBLE);
            edtSelfHosted.setOnFocusChangeListener(this);
            edtSelfHosted.requestFocus();
            txvAddSelfHosted.setText(R.string.label_login_with_odoo);
        } else {
            findViewById(R.id.layoutBorderDB).setVisibility(View.GONE);
            findViewById(R.id.layoutDatabase).setVisibility(View.GONE);
            findViewById(R.id.layoutSelfHosted).setVisibility(View.GONE);
            mSelfHostedURL = false;
            txvAddSelfHosted.setText(R.string.label_add_self_hosted_url);
            edtSelfHosted.setText("");
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }

    private void init() {
        mLoginProcessStatus = (TextView) this.findViewById(R.id.login_process_status);
        TextView mTermsCondition = (TextView) this.findViewById(R.id.termsCondition);
        mTermsCondition.setMovementMethod(LinkMovementMethod.getInstance());
        this.findViewById(R.id.btnLogin).setOnClickListener(this);
        this.findViewById(R.id.txvAddSelfHosted).setOnClickListener(this);
        edtSelfHosted = (EditText) this.findViewById(R.id.edtSelfHostedURL);
    }

    private void startOdooActivity() {
        startActivity(new Intent(this, ShippingActivity.class));
        finish();
    }
}
