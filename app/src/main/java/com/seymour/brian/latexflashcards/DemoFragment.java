package com.seymour.brian.latexflashcards;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.TextView;

/**
 * Created by Brian on 11/27/2015.
 */
public class DemoFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout resource that'll be returned
        View rootView = inflater.inflate(R.layout.fragment_demo, container, false);





        // Get the arguments that was supplied when
        // the fragment was instantiated in the
        // CustomPagerAdapter

        Bundle args = getArguments();

        ((TextView) rootView.findViewById(R.id.equation_name)).setText("Equation: " + args.get("page_position")+" out of: "+ new SavedLatexCode(getContext()).getSize());
        final String s = args.getString("latex");
        final String a = args.getString("notes");


        WebView w = (WebView) rootView.findViewById(R.id.scrollwebview);
        TextView tv = (TextView) rootView.findViewById(R.id.notesfrag);
        tv.setText(a);
        WebViewRenderer.prepareWebview(w);

        w.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {


                view.evaluateJavascript("javascript:document.getElementById('mmlout').innerHTML='';", null);
                view.evaluateJavascript("javascript:document.getElementById('math').innerHTML='\\\\["
                        + s + "\\\\]';", null);
                view.evaluateJavascript("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);", null);
            }
        });


        return rootView;
    }

}