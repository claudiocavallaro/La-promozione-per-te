package com.example.claudiocavallaro.progettogad.persistenza;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.example.claudiocavallaro.progettogad.Activity.ResultActivity;
import com.example.claudiocavallaro.progettogad.R;
import com.example.claudiocavallaro.progettogad.modello.Gestore;
import com.example.claudiocavallaro.progettogad.modello.ListaGestori;
import com.example.claudiocavallaro.progettogad.modello.News;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.util.Objects;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 * Created by claudiocavallaro on 15/12/15.
 */
public class RestCallNews extends AsyncTask<Object, Void, Object> {

    private static String url = "";
    private ProgressDialog progressDialog;
    private static Context context;
    private ResultActivity mActivity;

    public RestCallNews(ResultActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        String nome = ResultActivity.getNome();
        nome = nome.toLowerCase();
        switch (nome) {
            case "tim": {
                url = "http://www.tariffando.it/tariffe-telefoniche/offerte-telefonia/tim/";
                break;
            }
            case "vodafone": {
                url = "http://www.tariffando.it/tariffe-telefoniche/offerte-telefonia/vodafone-2/";
                break;
            }
            case "wind": {
                url = "http://www.tariffando.it/tariffe-telefoniche/offerte-telefonia/wind/";
                break;
            }
            case "tre": {
                url = "http://www.tariffando.it/tariffe-telefoniche/offerte-telefonia/tre/";
                break;
            }
        }

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Caricamento news in corso");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        progressDialog.setMessage("Caricamento news in corso");
    }

    @Override
    protected Object doInBackground(Object... params) {
        try {
            String content = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get().toString();
            TagNode tag = new org.htmlcleaner.HtmlCleaner().clean(content);
            Document doc = null;
            doc = new DomSerializer(new CleanerProperties()).createDOM(tag);
            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression exprNome;
            XPathExpression exprLink;
            for (int i = 1; i < 12; i++) {
                for (Gestore g : ListaGestori.getListaGestori()) {
                    if (g.getNomeGestore().equals(ResultActivity.getNome())) {
                        String expressionNome = "(//div[@class='archive-text'])[" + i + "]/h2/a";
                        exprNome = xpath.compile(expressionNome);
                        String nome = exprNome.evaluate(doc);
                        nome = nome.replaceAll("&egrave;","è");
                        nome = nome.replaceAll("&agrave;","à");
                        nome = nome.replaceAll("&euro;","€");

                        String expressionLink = "(//div[@class='archive-text'])[" + i + "]/h2/a/@href";
                        exprLink = xpath.compile(expressionLink);
                        String link = exprLink.evaluate(doc);

                        News news = new News(nome);
                        news.setLink(link);
                        g.addNews(news);
                        System.out.println(nome);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String replaceAll(String nome) {
        nome = nome.replace("&euro;", "€").replace("&ldquo;", "\"").replace("&rdquo;", "\"").replace("&ugrave;", "ù").replace("&rsquo;", "\'");
        return nome;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
        mActivity.gestisciTabs();
    }

    public static void setContext(Context context) {
        RestCallNews.context = context;
    }
}
