package belows.com.tangshi.test;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import belows.com.tangshi.R;
import belows.com.tangshi.domain.AuthorInfo;
import belows.com.tangshi.domain.Poem;
import belows.com.tangshi.domain.Verse;

/**
 * Created by belows on 15/6/11.
 */
public class XmlParser {

    public static void XmlTangshi(Context pContext) {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "belows_tangshi");
            if (!file.exists()) {
                file.mkdir();
            }
            String dir = Environment.getExternalStorageDirectory() + File.separator + "belows_tangshi";
            String path = dir + File.separator + "author.txt";
            List<AuthorInfo> test = XmlParser.parseAuthorXml(pContext, R.xml.author);
            ObjectMapper objectMapper = new ObjectMapper();
            String t = objectMapper.writeValueAsString(test);
            writeFile(path, t);


            List<Integer> poemList = new ArrayList<Integer>();
            poemList.add(R.xml.qi_yan_gu_shi);
            poemList.add(R.xml.qi_yan_jue_ju);
            poemList.add(R.xml.qi_yan_lv_shi);
            poemList.add(R.xml.qi_yan_yue_fu);
            poemList.add(R.xml.qita_gushi);
            poemList.add(R.xml.wu_yan_gu_shi);
            poemList.add(R.xml.wu_yan_jue_ju);
            poemList.add(R.xml.wu_yan_lv_shi);
            poemList.add(R.xml.wu_yan_yue_fu);
            List<Poem> poems = XmlParser.parsePoems(pContext, poemList);
            path = dir + File.separator + "tangshi.txt";
            writeFile(path, objectMapper.writeValueAsString(poems));
        } catch (Exception e) {
            String s = e.getStackTrace().toString();
            e.printStackTrace();
        }
    }

    private static void writeFile(String pFileName, String pContent) {
        try {
            File _file = new File(pFileName);
            if (!_file.exists()) {
                _file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(_file);
            OutputStreamWriter pw = new OutputStreamWriter(fos, "gb2312");
            BufferedWriter bw = new BufferedWriter(pw);
            bw.write(pContent);
            bw.close();
            pw.close();
            ;
            fos.close();
        } catch (Exception e) {
        }
    }

    public static ArrayList<AuthorInfo> parseAuthorXml(Context paramContext, long paramLong)
            throws XmlPullParserException, IOException {
        ArrayList<AuthorInfo> localArrayList = null;
        XmlResourceParser parser = paramContext.getResources().getXml((int) paramLong);
        AuthorInfo authorInfo = null;

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    localArrayList = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("author")) {
                        authorInfo = new AuthorInfo();
                    } else if (parser.getName().equals("name")) {
                        eventType = parser.next();
                        authorInfo.name = parser.getText();
                    } else if (parser.getName().equals("p")) {
                        eventType = parser.next();
                        if (authorInfo.pList == null) {
                            authorInfo.pList = new ArrayList<String>();
                        }
                        authorInfo.pList.add(parser.getText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("author")) {
                        localArrayList.add(authorInfo);
                        authorInfo = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return localArrayList;
    }

    public static List<Poem> parsePoems(Context pContext, List<Integer> pIdList)
            throws XmlPullParserException, IOException {
        List<Poem> _list = new ArrayList<Poem>();
        for (int _id : pIdList) {
            String _category = "";
            switch (_id) {
                case R.xml.qi_yan_gu_shi:
                    _category = pContext.getString(R.string.qi_yan_gu_shi);
                    break;
                case R.xml.qi_yan_jue_ju:
                    _category = pContext.getString(R.string.qi_yan_jue_ju);
                    break;
                case R.xml.qi_yan_lv_shi:
                    _category = pContext.getString(R.string.qi_yan_lv_shi);
                    break;
                case R.xml.qi_yan_yue_fu:
                    _category = pContext.getString(R.string.qi_yan_yue_fu);
                    break;
                case R.xml.qita_gushi:
                    _category = pContext.getString(R.string.qi_ta_tang_shi);
                    break;
                case R.xml.wu_yan_gu_shi:
                    _category = pContext.getString(R.string.wu_yan_gu_shi);
                    break;
                case R.xml.wu_yan_jue_ju:
                    _category = pContext.getString(R.string.wu_yan_jue_ju);
                    break;
                case R.xml.wu_yan_lv_shi:
                    _category = pContext.getString(R.string.wu_yan_lv_shi);
                    break;
                case R.xml.wu_yan_yue_fu:
                    _category = pContext.getString(R.string.wu_yan_yue_fu);
                    break;
            }
            _list.addAll(parsePoem(pContext, _id, _category));
        }
        return _list;
    }

    public static List<Poem> parsePoem(Context pContext, int pId, String pCategory)
            throws XmlPullParserException, IOException {
        ArrayList<Poem> _list = null;
        XmlResourceParser _parser = pContext.getResources().getXml((int) pId);
        Poem _poem = null;

        int _eventType = _parser.getEventType();
        int pos = 0;
        int cpPos = 0;
        while (_eventType != XmlPullParser.END_DOCUMENT) {
            switch (_eventType) {
                case XmlPullParser.START_DOCUMENT:
                    _list = new ArrayList<Poem>();
                    break;
                case XmlPullParser.START_TAG:
                    String name = _parser.getName();
                    if (_parser.getName().equals("poetry")) {
                        _poem = new Poem();
                        _poem.mCategory = pCategory;
                        cpPos = 0;
                    } else if (_parser.getName().equals("id")) {
                        _eventType = _parser.next();
                        pos = 0;
                        _poem.mId = Integer.parseInt(_parser.getText());
                    } else if (_parser.getName().equals("title")) {
                        pos = 1;
                        _poem.mTitle = new Verse();
                    } else if (_parser.getName().equals("author")) {
                        pos = 2;
                        _poem.mAuthor = new Verse();
                    } else if (_parser.getName().equals("content")) {
                        pos = 3;
                        _poem.mContentList = new ArrayList<Verse>();
                    } else if (_parser.getName().equals("comment")) {
                        pos = 4;
                        _poem.mCommentList = new ArrayList<String>();
                    } else if (_parser.getName().equals("rhyme")) {
                        pos = 5;
                        _poem.mRhymeList = new ArrayList<String>();
                    } else if (_parser.getName().equals("appreciation")) {
                        pos = 6;
                        _poem.mAppreciationList = new ArrayList<String>();
                    } else if (_parser.getName().equals("cp")) {
                        if (pos == 3) {
                            _poem.mContentList.add(new Verse());
                        }
                    } else if (_parser.getName().equals("yinpin")) {
                        _eventType = _parser.next();
                        switch (pos) {
                            case 1:
                                _poem.mTitle.mPinyin = _parser.getText();
                                break;
                            case 2:
                                _poem.mAuthor.mPinyin = _parser.getText();
                                break;
                            case 3:
                                _poem.mContentList.get(cpPos).mPinyin = _parser.getText();
                                break;
                        }
                    } else if (_parser.getName().equals("data")) {
                        _eventType = _parser.next();
                        switch (pos) {
                            case 1:
                                _poem.mTitle.mContent = _parser.getText();
                                _poem.mTitleName = _poem.mTitle.mContent;
                                break;
                            case 2:
                                _poem.mAuthor.mContent = _parser.getText();
                                _poem.mAuthorName = _poem.mAuthor.mContent;
                                break;
                            case 3:
                                _poem.mContentList.get(cpPos).mContent = _parser.getText();
                                ++cpPos;
                                break;
                        }
                    } else if (_parser.getName().equals("p")) {
                        _eventType = _parser.next();
                        switch (pos) {
                            case 4:
                                _poem.mCommentList.add(_parser.getText());
                                break;
                            case 5:
                                _poem.mRhymeList.add(_parser.getText());
                                break;
                            case 6:
                                _poem.mAppreciationList.add(_parser.getText());
                                break;
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    String name1 = _parser.getName();
                    if (_parser.getName().equals("poetry")) {
                        _list.add(_poem);
                        _poem = null;
                    }
                    break;
            }
            _eventType = _parser.next();
        }
        return _list;
    }
}
