package com.belgroup.videoportal;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

/**
 * Created by B.E.L on 10/10/2016.
 */

public class FragmentVimeoWebView extends Fragment {

    private static final String DATA_SAVED = "dataSaved";
    private static boolean active;
    private WebView mWebView;
    public static boolean downloadEnabled;

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
            mWebView.loadUrl("https://vimeo.com");
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
            if (url != null && Uri.parse(url).getLastPathSegment() != null ) {
                checkString(Uri.parse(url).getLastPathSegment());
            }
            downloadEnabled = false;
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            List<String> list = Uri.parse(url).getPathSegments();
            int index = list.indexOf("video");
            if (index > 0) {
                checkString(list.get(index - 1));
            } else if (list.size() > 0) {
                checkString(list.get(0));
            }
        }

        private void checkString(String id){
            if (id.matches("[0-9]{9}")){
                active = true;
                downloadEnabled = false;
//                loadVimeoId(id);
            }
        }

    }

    private void loadVimeoId(String id){
//        VimeoExtractor.getInstance().fetchVideoWithIdentifier(id, null, new OnVimeoExtractionListener() {
//            @Override
//            public void onSuccess(VimeoVideo video) {
//                Log.d("TAG", "onSuccess: " + video.getTitle());
//                btnDownload.setTag(video);
//                btnDownload.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        downloadEnabled = true;
//                        btnDownload.setEnabled(true);
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                //Error handling here
//            }
//        });
    }
}
