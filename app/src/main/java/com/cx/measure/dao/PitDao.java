package com.cx.measure.dao;

import com.cx.measure.bean.Pit;
import com.cx.measure.bean.Workbench;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.sql.Timestamp;

/**
 * Created by yyao on 2016/6/6.
 */
public class PitDao extends BaseDao {

    public void add(Pit pit) throws DbException {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        DbManager db = x.getDb(daoConfig);
        pit.setCreateTime(now);
        pit.setUpdateTime(now);
        db.saveBindingId(pit);

        WorkbenchDao workbenchDao = new WorkbenchDao();
        for (Workbench workbench:pit.getWorkbenches()) {
            workbench.setPitId(pit.getId());
            workbenchDao.add(workbench);
        }
    }
}