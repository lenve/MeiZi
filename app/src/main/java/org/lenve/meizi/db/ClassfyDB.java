package org.lenve.meizi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.lenve.meizi.bean.ClassfyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王松 on 2016/9/13.
 */
public class ClassfyDB {
    private SQLiteDatabase db;
    private Context context;
    private int id;
    private String name;
    private String title;
    private String keywords;
    private String description;
    private int seq;//排序 从0。。。。10开始

    public ClassfyDB(Context context) {
        this.context = context;
        db = new DBHelper(context).getReadableDatabase();
    }

    public List<ClassfyBean.TngouBean> getClassfies() {
        List<ClassfyBean.TngouBean> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + DBHelper.CLASSFYTABLE, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            String title = cursor.getString(cursor.getColumnIndex("TITLE"));
            String keywords = cursor.getString(cursor.getColumnIndex("KEYWORDS"));
            String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            int seq = cursor.getInt(cursor.getColumnIndex("SEQ"));
            ClassfyBean.TngouBean bean = new ClassfyBean.TngouBean();
            bean.setDescription(description);
            bean.setId(id);
            bean.setKeywords(keywords);
            bean.setName(name);
            bean.setSeq(seq);
            bean.setTitle(title);
            list.add(bean);
        }
        cursor.close();
        return list;
    }

    public void add(List<ClassfyBean.TngouBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        try {
            db.beginTransaction();
            for (ClassfyBean.TngouBean classfyBean : list) {
                add(classfyBean);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void add(ClassfyBean.TngouBean classfyBean) {
        ContentValues values = new ContentValues();
        values.put("ID", classfyBean.getId());
        values.put("DESCRIPTION", classfyBean.getDescription());
        values.put("KEYWORDS", classfyBean.getKeywords());
        values.put("NAME", classfyBean.getName());
        values.put("SEQ", classfyBean.getSeq());
        values.put("TITLE", classfyBean.getTitle());
        db.insert(DBHelper.CLASSFYTABLE, null, values);
    }
}
