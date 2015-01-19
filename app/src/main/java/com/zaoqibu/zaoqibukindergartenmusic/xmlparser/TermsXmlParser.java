package com.zaoqibu.zaoqibukindergartenmusic.xmlparser;

import com.zaoqibu.zaoqibukindergartenmusic.R;
import com.zaoqibu.zaoqibukindergartenmusic.domain.Playlist;
import com.zaoqibu.zaoqibukindergartenmusic.domain.Term;
import com.zaoqibu.zaoqibukindergartenmusic.domain.Terms;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * Created by vwarship on 2015/1/15.
 */
public class TermsXmlParser {
    public Terms parse(InputStream inputStream) {
        Terms terms = new Terms();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser.getName().equals("term")) {
                    terms.addTerm(readTerm(parser));
                }

                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return terms;
    }

    private Term readTerm(XmlPullParser parser) throws IOException, XmlPullParserException {
        String name = parser.getAttributeValue(null, "name");
        String dir = parser.getAttributeValue(null, "dir");
        String backgroundColor = parser.getAttributeValue(null, "backgroundColor");
        String icon = parser.getAttributeValue(null, "icon");

        Term term = new Term(name, getDrawableIdWithResource(backgroundColor), getDrawableIdWithResource(icon));
        term.setPlaylist(new Playlist());
        return term;
    }

    private static int getDrawableIdWithResource(String name) {
        try {
            Field field = R.drawable.class.getField(name);
            return Integer.parseInt(field.get(null).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


}
