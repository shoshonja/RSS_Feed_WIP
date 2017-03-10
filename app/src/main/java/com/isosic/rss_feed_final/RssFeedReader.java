package com.isosic.rss_feed_final;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class RssFeedReader extends AsyncTask<URL, Integer, Integer> {

    private Context context;
    private URL feedAdress;
    private ProgressDialog progressDialog;
    public ArrayList<RssFeedItems> feedItemList;

    public RssFeedReader(Context context, URL feedAdress) {
        this.context = context;
        this.feedAdress = feedAdress;
        progressDialog = new ProgressDialog(context);

    }

    public void generateFeedsFromXML(Document xmlDocument) {

        feedItemList = new ArrayList<>();

        Element root = xmlDocument.getDocumentElement();
        Node channel = root.getChildNodes().item(1);
        NodeList items = channel.getChildNodes();

        for (int i = 0; i < items.getLength(); i++) {
            Node currentChild = items.item(i);
            if (currentChild.getNodeName().equalsIgnoreCase("item")) {
                RssFeedItems RSSitem = new RssFeedItems();
                NodeList childItems = currentChild.getChildNodes();

                for (int j = 0; j < childItems.getLength(); j++) {
                    Node currentItem = childItems.item(j);

                    if (currentItem.getNodeName().equalsIgnoreCase("title")) {
                        RSSitem.setTitle(currentItem.getTextContent());
                    } else if (currentItem.getNodeName().equalsIgnoreCase("link")) {
                        RSSitem.setLink(currentItem.getTextContent());
                    } else if (currentItem.getNodeName().equalsIgnoreCase("description")) {
                        RSSitem.setDescription(StringUtils.substringBetween(currentItem.getTextContent(), "/><br /><br />", "<br />("));
                        RSSitem.setThumbnail(StringUtils.substringBetween(currentItem.getTextContent(), "src=\"", "\" alt"));
                    } else if (currentItem.getNodeName().equalsIgnoreCase("pubDate")) {
                        RSSitem.setPubDate(currentItem.getTextContent());
                    }
                }
                feedItemList.add(RSSitem);
                Log.d("itemTitle", RSSitem.getTitle());
                Log.d("itemLink", RSSitem.getLink());
                Log.d("itemDescription", RSSitem.getDescription());
                Log.d("itemPubDate", RSSitem.getPubDate());
                Log.d("itemThumbnail", RSSitem.getThumbnail());
            }
        }
    }

    public Document fetchXMLData() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) feedAdress.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = httpURLConnection.getInputStream();

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(inputStream);

            return xmlDocument;

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("INPUT", "Error with input stream");
            return null;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            Log.d("BUILDER", "Error with creating document");
            return null;
        } catch (SAXException e) {
            e.printStackTrace();
            Log.d("PARSING", "Error parsing input stream");
            return null;
        }
    }


    @Override
    protected Integer doInBackground(URL... urls) {
        generateFeedsFromXML(fetchXMLData());
        return null;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Loading");
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        progressDialog.dismiss();
    }
}
