package com.seymour.brian.latexflashcards;

import android.app.Activity;
import android.content.DialogInterface;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.graphics.*;
import android.content.res.*;
import android.webkit.*;
import android.text.method.*;
import android.text.*;
import android.content.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.graphics.*;
import android.content.res.*;
import android.webkit.*;
import android.text.method.*;
import android.text.*;
import android.content.*;


/**
 * Created by Brian on 11/21/2015.
 */
public class ViewEquations extends Activity implements View.OnClickListener {


    private EditText e;
    private EditText n;
    private WebView w;
    private int exampleIndex = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_equations);
        w = (WebView) findViewById(R.id.webview);
        WebViewRenderer.prepareWebview(w);


        /**
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
         **/



       /* w.addJavascriptInterface(new Object() {
            public void clipMML(String s) {
                WebView ww = (WebView) findViewById(R.id.webview);
                //uses android.text.ClipboardManager for compatibility with pre-Honeycomb
                //for HC or later, use android.content.ClipboardManager
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                //next 2 comment lines have HC or later code, can also try newHtmlText()
                //ClipData clip = ClipData.newPlainText("MJ MathML text",s);//,s);
                //clipboard.setPrimaryClip(clip);
                // literal MathML (in parameter s) placed on system clipboard
                clipboard.setText(s);
                Toast.makeText(getApplicationContext(), "MathML copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        }, "injectedObject");*/
        e = (EditText) findViewById(R.id.edit);
        n = (EditText) findViewById(R.id.editnotes);
        e.setBackgroundColor(Color.LTGRAY);
        e.setTextColor(Color.BLACK);
        n.setBackgroundColor(Color.LTGRAY);
        n.setTextColor(Color.BLACK);
        e.setText("");

        //getting all the buttons ready
        Button b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button3);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button4);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.bSave);
        b.setOnClickListener(this);


    }


    public void onClick(View v) {
        if (v == findViewById(R.id.button2)) {
            EditText e = (EditText) findViewById(R.id.edit);
            WebViewRenderer.renderLatex(w, e.getText().toString());
            /**
             w.evaluateJavascript("javascript:document.getElementById('mmlout').innerHTML='';", null);
             w.evaluateJavascript("javascript:document.getElementById('math').innerHTML='\\\\["
             + doubleEscapeTeX(e.getText().toString()) + "\\\\]';", null);
             w.evaluateJavascript("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);", null);**/

        } else if (v == findViewById(R.id.button3)) {

            EditText e = (EditText) findViewById(R.id.edit);
            e.setText("");
            WebViewRenderer.renderLatex(w, e.getText().toString());
            /**
             w.evaluateJavascript("javascript:document.getElementById('mmlout').innerHTML='';", null);
             w.evaluateJavascript("javascript:document.getElementById('math').innerHTML='';", null);**/
        } else if (v == findViewById(R.id.button4)) {

            EditText e = (EditText) findViewById(R.id.edit);
            e.setText(getExample(exampleIndex++));
            if (exampleIndex > getResources().getStringArray(R.array.tex_examples).length - 1)
                exampleIndex = 0;
            WebViewRenderer.renderLatex(w, e.getText().toString());
            /**
             w.evaluateJavascript("javascript:document.getElementById('mmlout').innerHTML='';", null);
             w.evaluateJavascript("javascript:document.getElementById('math').innerHTML='\\\\["
             + doubleEscapeTeX(e.getText().toString())
             + "\\\\]';", null);
             w.evaluateJavascript("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);", null);**/
        } else if (v == findViewById(R.id.bSave)) {
            String latexCode = WebViewRenderer.doubleEscapeTeX(e.getText().toString());
            String latexNotes = n.getText().toString();
            SavedLatexCode slc = new SavedLatexCode(this);
            slc.addElement(latexCode, latexNotes);
            Toast.makeText(ViewEquations.this, "You have sucessfully made an equation!", Toast.LENGTH_SHORT).show();
        }

    }

    private String getExample(int index) {
        return getResources().getStringArray(R.array.tex_examples)[index];
    }
    /**    private String doubleEscapeTeX(String s) {
     String t = "";
     for (int i = 0; i < s.length(); i++) {
     if (s.charAt(i) == '\'') t += '\\';
     if (s.charAt(i) != '\n') t += s.charAt(i);
     if (s.charAt(i) == '\\') t += "\\";
     }
     return t;
     }**/
}
