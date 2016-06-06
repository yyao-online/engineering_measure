package com.cx.measure.dao;

import com.cx.measure.bean.WorkPoint;
import com.cx.measure.bean.Workbench;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.sql.Timestamp;

/**
 * Created by yyao on 2016/6/6.
 */
public class WorkbenchDao extends BaseDao {

    public void add(Workbench workbench) throws DbException {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        DbManager db = x.getDb(daoConfig);
        workbench.setCreateTime(now);
        workbench.setUpdateTime(now);
        db.saveBindingId(workbench);

        WorkPointDao workPointDao = new WorkPointDao();
        for (WorkPoint workPoint : workbench.getWorkPoints()) {
            workPoint.setWorkbenchId(workbench.getId());
            workPointDao.add(workPoint);
        }
    }
}
