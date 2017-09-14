package com.mucfc.crawing;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abby on 2017/9/14.
 */

public class Parser {
    public static final String NAME_SPACE = "<span class=\"name\">";
    public static final String FINISH_TAG = "</span>";
    public static final String CLASS_TAG = " >";
    public static final String CLASS_FINISH_TAG = "</a>";
    public static final String URL_TAG = "<a href=\"";
    public static final String URL_FINISH_TAG = "\" target=\"_blank\"";
    public static final String PHONE_400_TAG = "<div class=\"l\"><em>客服电话：</em></div>";
    public static final String PHONE_TAG_2 = "<div class=\"r\">\n" +
            "                                     ";
    public static final String PHONE_TAG_FINISH = "\n";
    public static final String PHONE_TAG = "<div class=\"l\"><em>座机电话：</em></div>";
    public static final String PAGE_NUMBER_TAG = "<span class=\"all\">";
    public static final String PAGE_NUMBER_STRONG_TAG = "<strong>";
    public static final String PAGE_NUMBER_STRONG_TAG_FINISH = "</strong>";
    private static List<ResultBean> mResultList = new ArrayList<>();

    public static String[] NameGroupParser(String raw){
        String[] names = null;
        if(raw.contains(NAME_SPACE)) {
            names = raw.split(NAME_SPACE);
            int i = 1;
            while(i < names.length){
                String nameTag = names[i].split(FINISH_TAG)[0];
                String name = nameTag.split(CLASS_TAG)[1].split(CLASS_FINISH_TAG)[0];
                String url = nameTag.split(URL_TAG)[1].split(URL_FINISH_TAG)[0];
                final ResultBean bean = new ResultBean();
                bean.setName(name);
                url = MainActivity.URL + url;
                Crawer.syncGet(url, new Crawer.OnCrawerReadyListener() {
                    @Override
                    public void onReady(String raw) {
                        PhoneParser(raw, bean);
                    }
                });

                i ++;
            }
        }

        return names;
    }

    public static void PhoneParser(String raw, ResultBean bean){
        String phone400 = null;
        String phone = null;
        if(raw.contains(PHONE_400_TAG)){
            phone400 = raw.split(PHONE_400_TAG)[1].split(PHONE_TAG_2)[1].split(PHONE_TAG_FINISH)[0];
            phone = raw.split(PHONE_TAG)[1].split(PHONE_TAG_2)[1].split(PHONE_TAG_FINISH)[0];
            bean.setPhone400(phone400);
            bean.setPhone(phone);
            String aList = bean.getName() + "," + bean.getPhone400() + "," + bean.getPhone() + "\n";
            Log.e("aList", aList);
            Writer.write(aList);
            mResultList.add(bean);
        }
    }

    public static int PageNumberParser(String raw){
        String pageNumber = raw.split(PAGE_NUMBER_TAG)[1].
                split(PAGE_NUMBER_STRONG_TAG)[3].split(PAGE_NUMBER_STRONG_TAG_FINISH)[0];
        return Integer.parseInt(pageNumber);
    }
}
