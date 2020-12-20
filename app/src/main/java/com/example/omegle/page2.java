package com.example.omegle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class page2 extends AppCompatActivity {

    private WebView mainWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        mainWebView = findViewById(R.id.main_webview);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)  {
            Toast.makeText(this, "Please, grant the permission.", Toast.LENGTH_SHORT).show();
            //source : https://androidkennel.org/android-camera-access-tutorial/
//              :"not able to gain permission"
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO }, 1);
//            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA}, 1);
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Without permissions app won't work.", Toast.LENGTH_SHORT).show();
        }
        else{
            mainWebView.setWebViewClient(new page2.myWebClient());

            mainWebView.loadUrl("https://www.omegle.com");

            WebSettings websettings = mainWebView.getSettings();

            websettings.setJavaScriptEnabled(true);

            // source for following code
            //https://stackoverflow.com/questions/40659198/how-to-access-the-camera-from-within-a-webview
            websettings.setAllowFileAccessFromFileURLs(true);
            websettings.setAllowUniversalAccessFromFileURLs(true);
            websettings.setJavaScriptCanOpenWindowsAutomatically(true);

            websettings.setDomStorageEnabled(true);
            websettings.setJavaScriptCanOpenWindowsAutomatically(true);
            websettings.setBuiltInZoomControls(true);
            websettings.setAllowFileAccess(true);
            websettings.setSupportZoom(true);

            websettings.setMediaPlaybackRequiresUserGesture(false);


            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO }, 1);
            }



            //source : https://stackoverflow.com/questions/40659198/how-to-access-the-camera-from-within-a-webview
            mainWebView.setWebChromeClient(new WebChromeClient() {

                @Override
                public void onPermissionRequest(final PermissionRequest request) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        request.grant(request.getResources());
                    }
                }

            });

//            source:https://stackoverflow.com/questions/22376202/camera-not-opening-in-webview
//            val permission = Manifest.permission.CAMERA;
//            val grant = ContextCompat.checkSelfPermission(this@WebViewActivity, permission);
//            if (grant != PackageManager.PERMISSION_GRANTED) {
//                val permission_list = arrayOf(permission)
//                ActivityCompat.requestPermissions(this@WebViewActivity, permission_list, 1);
//            }


        }

    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;

        }


//            @Override
//            public void onBackPressed() {
//                if (mainWebView.canGoBack()) {
//                    mainWebView.goBack();
//                } else {
//                    super.onBackPressed();
//                }
//            }

    }
}