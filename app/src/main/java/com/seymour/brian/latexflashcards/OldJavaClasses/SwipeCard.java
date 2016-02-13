package com.seymour.brian.latexflashcards.OldJavaClasses;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.seymour.brian.latexflashcards.LatexStuff;
import com.seymour.brian.latexflashcards.R;

import java.util.Random;

/**
 * Created by Brian on 11/23/2015.
 */
public class SwipeCard extends Activity implements View.OnClickListener {

    Button back;
    Button next;
    int lastIndex;
    int currentIndex;
    Random random;
    WebView w;
    LatexStuff latexStuff;

   /* private String doubleEscapeTeX(String s) {
        String t = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\'') t += '\\';
            if (s.charAt(i) != '\n') t += s.charAt(i);
            if (s.charAt(i) == '\\') t += "\\";
        }
        return t;
    }

    private int exampleIndex = 0;
    private boolean mmltoggle = false;

    private String getExample(int index) {
        return getResources().getStringArray(R.array.tex_examples)[index];
    }
*/

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_card);
        back = (Button) findViewById(R.id.back);
        next = (Button) findViewById(R.id.random);
        back.setOnClickListener(this);
        next.setOnClickListener(this);
        random = new Random();
        lastIndex = 0;
        latexStuff = new LatexStuff(this);
        w = (WebView) findViewById(R.id.webview2);
        w.getSettings().setJavaScriptEnabled(true);
        w.getSettings().setBuiltInZoomControls(true);
        w.loadDataWithBaseURL("http://bar/", "<script type='text/x-mathjax-config'>"
                + "MathJax.Hub.Config({ "
                + "showMathMenu: false, "
                + "jax: ['input/TeX','output/HTML-CSS'], " // output/SVG
                + "extensions: ['tex2jax.js','toMathML.js'], "
                + "TeX: { extensions: ['AMSmath.js','AMSsymbols.js',"
                + "'noErrors.js','noUndefined.js'] }, "
                //+"'SVG' : { blacker: 30, "
                // +"styles: { path: { 'shape-rendering': 'crispEdges' } } } "
                + "});</script>"
                + "<script type='text/javascript' "
                + "src='file:///android_asset/MathJax/MathJax.js'"
                + "></script>"
                + "<script type='text/javascript'>getLiteralMML = function() {"
                + "math=MathJax.Hub.getAllJax('math')[0];"
                // below, toMathML() rerurns literal MathML string
                + "mml=math.root.toMathML(''); return mml;"
                + "}; getEscapedMML = function() {"
                + "math=MathJax.Hub.getAllJax('math')[0];"
                // below, toMathMLquote() applies &-escaping to MathML string input
                + "mml=math.root.toMathMLquote(getLiteralMML()); return mml;}"
                + "</script>"
                + "<span id='math'></span><pre><span id='mmlout'></span></pre>", "text/html", "utf-8", "");
        w.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {

                String s = latexStuff.getRandomExample(getApplication());
                view.evaluateJavascript("javascript:document.getElementById('mmlout').innerHTML='';", null);
                view.evaluateJavascript("javascript:document.getElementById('math').innerHTML='\\\\["
                        + s + "\\\\]';", null);
                view.evaluateJavascript("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);", null);
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.equals(back)) {
            if(latexStuff.getCurrentIndex()==latexStuff.getLastIndex()){
                Toast.makeText(SwipeCard.this, "Sorry, can only go back once", Toast.LENGTH_SHORT).show();
            }

            String s = latexStuff.getLast(getApplication());

            w.evaluateJavascript("javascript:document.getElementById('mmlout').innerHTML='';", null);
            w.evaluateJavascript("javascript:document.getElementById('math').innerHTML='\\\\["
                    + s + "\\\\]';", null);
            w.evaluateJavascript("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);", null);
        }
        if (v.equals(next)) {

            String s = latexStuff.getRandomExample(getApplication());
            w.evaluateJavascript("javascript:document.getElementById('mmlout').innerHTML='';", null);
            w.evaluateJavascript("javascript:document.getElementById('math').innerHTML='\\\\["
                    + s + "\\\\]';", null);
            w.evaluateJavascript("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);", null);
        }
    }
}
