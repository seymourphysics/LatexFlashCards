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
 * These methods were made by Tom Leathrum and posted on his blog on May 1, 2014.
 * Here is the url: http://cs.jsu.edu/wordpress/index.php/2014/05/01/mathjax-standalone-android-app-updated-links-with-kitkat-fix/
 * This class handles rendering latesx
 */
public class WebViewRenderer extends Activity {
    public static void prepareWebview(WebView w) {
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
    }

    /**
     * fixes back slashes problems when rendering
     *
     * @param s String to fix
     * @return fixed string
     */
    public static String doubleEscapeTeX(String s) {
        String t = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\'') t += '\\';
            if (s.charAt(i) != '\n') t += s.charAt(i);
            if (s.charAt(i) == '\\') t += "\\";
        }
        return t;
    }

    public static void renderLatex(WebView w,String s) {
        w.evaluateJavascript("javascript:document.getElementById('mmlout').innerHTML='';", null);
        w.evaluateJavascript("javascript:document.getElementById('math').innerHTML='\\\\["
                + doubleEscapeTeX(s) + "\\\\]';", null);
        w.evaluateJavascript("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);", null);
    }
}
