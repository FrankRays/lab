package shu.lab.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import shu.lab.dao.SearchDao;
import shu.lab.util.HibernateUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/13.
 */
public class SearchDaoImpl implements SearchDao {
    public List<Map> getCCFNum(String ccf) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {

            String sql = "SELECT username ,count(*) FROM paper,user_paper,user where user.user_id=user_paper.user_id and paper.paper_id=user_paper.paper_id and CCF_status=? GROUP BY user.user_id";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, ccf);
            if (ccf == "CCFnull") {
                sql = "SELECT username ,count(*) FROM paper,user_paper,user where user.user_id=user_paper.user_id and paper.paper_id=user_paper.paper_id and CCF_status is NULL GROUP BY user.user_id";
                query = session.createSQLQuery(sql);
            }

            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map> getYearNum(String year) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {

            String sql = "SELECT username ,count(*) FROM paper,user_paper,user where user.user_id=user_paper.user_id and paper.paper_id=user_paper.paper_id and post_year=? GROUP BY user.user_id";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, year);
            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map> getCategoryNum(String category) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {

            String sql = "SELECT username ,count(*) FROM paper,user_paper,user where user.user_id=user_paper.user_id and paper.paper_id=user_paper.paper_id and category=? GROUP BY user.user_id";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, category);
            if (category == "categorynull") {
                sql = "SELECT username ,count(*) FROM paper,user_paper,user where user.user_id=user_paper.user_id and paper.paper_id=user_paper.paper_id and category is NULL GROUP BY user.user_id";
                query = session.createSQLQuery(sql);
            }

            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map> getGroupNum(Integer group) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {

            String sql = "SELECT groups.group_name,COUNT(DISTINCT paper.paper_id)\n" +
                    "FROM group_user,user_paper,paper,groups\n" +
                    "where group_user.group_id=? and group_user.user_id=user_paper.user_id and user_paper.paper_id=paper.paper_id and groups.group_id=group_user.group_id";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, group);
            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getGroupNumAll() {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {

            String sql = "SELECT COUNT(DISTINCT paper.paper_id)\n" +
                    "FROM group_user,user_paper,paper,groups\n" +
                    "where group_user.user_id=user_paper.user_id and user_paper.paper_id=paper.paper_id";
            SQLQuery query = session.createSQLQuery(sql);
            List list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getGroupNumWithYear() {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            String sql = "SELECT groups.group_name,post_year,count(DISTINCT user_paper.user_id)" +
                    "FROM group_user,user_paper,paper,groups" +
                    "where group_user.user_id=user_paper.user_id and user_paper.paper_id=paper.paper_id and groups.group_id=group_user.group_id" +
                    "group by group_user.group_id,post_year";
            SQLQuery query = session.createSQLQuery(sql);
            List list = query.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getGroupNumWithCCF() {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            String sql = "SELECT groups.group_name,CCF_status,count(DISTINCT user_paper.user_id)" +
                    "FROM group_user,user_paper,paper,groups" +
                    "where group_user.user_id=user_paper.user_id and user_paper.paper_id=paper.paper_id and groups.group_id=group_user.group_id" +
                    "group by group_user.group_id,CCF_status";
            SQLQuery query = session.createSQLQuery(sql);
            List list = query.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getGroupNumWithCategory() {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            String sql = "SELECT groups.group_name,category,count(DISTINCT user_paper.user_id)" +
                    "FROM group_user,user_paper,paper,groups" +
                    "where group_user.user_id=user_paper.user_id and user_paper.paper_id=paper.paper_id and groups.group_id=group_user.group_id" +
                    "group by group_user.group_id,category";
            SQLQuery query = session.createSQLQuery(sql);
            List list = query.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String args[])
    {
        SearchDao searchDao = new SearchDaoImpl();
        System.out.println(searchDao.getGroupNum(1).toString());
        System.out.println(searchDao.getGroupNumAll().toString());
        System.out.println(searchDao.getGroupNumWithCCF().toString());
        System.out.println(searchDao.getGroupNumWithYear().toString());
        System.out.println(searchDao.getGroupNumWithCategory().toString());

    }

}
