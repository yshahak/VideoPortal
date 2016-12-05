package com.belgroup.videoportal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by B.E.L on 10/10/2016.
 */

public class FragmentVuclipWebView extends Fragment {

    private static final String DATA_SAVED = "dataSaved";
    private WebView mWebView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mWebView = (WebView) inflater.inflate(R.layout.fragment_web_view, container, false);
        mWebView.getSettings().setJavaScriptEnabled(true);
        if (savedInstanceState != null && savedInstanceState.getString(DATA_SAVED) != null){
            mWebView.loadUrl(savedInstanceState.getString(DATA_SAVED));
        } else {
            mWebView.loadUrl("http://vuclip.com");
        }
        mWebView.setWebViewClient(new MyWebClient());
        return mWebView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mWebView.destroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DATA_SAVED, mWebView.getUrl());
    }


    class MyWebClient extends WebViewClient {


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);

        }



    }


}
