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
    private HibernateUtil util = new HibernateUtil();
    private Session session = util.openSession();

    public List<Map> getCCFNum(String ccf) {
        session.clear();
        try {
            String sql = "SELECT username ,count(*) as num FROM paper,user_paper,user where user.user_id=user_paper.user_id and paper.paper_id=user_paper.paper_id and CCF_status GROUP BY user.user_id";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, ccf);
            if (ccf == "CCFnull") {
                sql = "SELECT username ,count(*) as num FROM paper,user_paper,user where user.user_id=user_paper.user_id and paper.paper_id=user_paper.paper_id and CCF_status is NULL GROUP BY user.user_id";
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


    public List<Map> getCCFNum() {
        session.clear();
        try {

            String sql = "SELECT user.user_id,username ,count(DISTINCT user_paper.paper_id) as num,CCF_status\n" +
                    "FROM user_paper \n" +
                    "RIGHT JOIN user on user_paper.user_id = user.user_id \n" +
                    "left JOIN paper on user_paper.paper_id = paper.paper_id\n" +
                    "GROUP BY user.user_id,CCF_status";
            SQLQuery query = session.createSQLQuery(sql);


            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map> getYearNum(String year) {
        session.clear();
        try {

            String sql = "SELECT username ,count(*) as num FROM paper,user_paper,user where user.user_id=user_paper.user_id and paper.paper_id=user_paper.paper_id and post_year=? GROUP BY user.user_id";
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

    public List<Map> getYearNum() {
        session.clear();
        try {

            String sql = "SELECT user.user_id,username,post_year,count(DISTINCT user_paper.paper_id) as num \n" +
                    "FROM user_paper \n" +
                    "RIGHT JOIN user on user_paper.user_id = user.user_id\n" +
                    "LEFT JOIN paper on user_paper.paper_id = paper.paper_id\n" +
                    "GROUP BY user.user_id,post_year";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map> getCategoryNum(String category) {
        session.clear();
        try {

            String sql = "SELECT username ,count(*) as num FROM paper,user_paper,user where user.user_id=user_paper.user_id and paper.paper_id=user_paper.paper_id and category=? GROUP BY user.user_id";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, category);
            if (category == "categorynull") {
                sql = "SELECT username ,count(*) as num FROM paper,user_paper,user where user.user_id=user_paper.user_id and paper.paper_id=user_paper.paper_id and category is NULL GROUP BY user.user_id";
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

    public List<Map> getCategoryNum() {
        session.clear();
        try {

            String sql = "SELECT user.user_id,username,category,count(DISTINCT user_paper.paper_id) as num \n" +
                    "FROM user_paper\n" +
                    "RIGHT JOIN user on user.user_id=user_paper.user_id\n" +
                    "LEFT JOIN paper on paper.paper_id=user_paper.paper_id\n" +
                    "GROUP BY user.user_id,category";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map> getGroupNum(Integer group) {
        session.clear();
        try {

            String sql = "SELECT groups.group_id,groups.group_name,COUNT(DISTINCT paper.paper_id) as num FROM group_user,user_paper,paper,groups where group_user.group_id=? and group_user.user_id=user_paper.user_id and user_paper.paper_id=paper.paper_id and groups.group_id=group_user.group_id";
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

    public List<Map> getGroupNumAll() {
        session.clear();
        try {

            String sql = "SELECT COUNT(DISTINCT paper.paper_id) as num FROM group_user,user_paper,paper,groups where group_user.user_id=user_paper.user_id and user_paper.paper_id=paper.paper_id";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map> getGroupNumWithYear() {
        session.clear();
        try {
            String sql = "SELECT groups.group_id,group_name,post_year,COUNT(DISTINCT user_paper.paper_id) as num \n" +
                    "FROM group_user \n" +
                    "RIGHT JOIN groups on group_user.group_id = groups.group_id \n" +
                    "LEFT JOIN user_paper on group_user.user_id=user_paper.user_id \n" +
                    "LEFT JOIN paper on user_paper.paper_id = paper.paper_id\n" +
                    "GROUP BY groups.group_id,post_year";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map> getGroupNumWithCCF() {
        session.clear();
        try {
            String sql = "SELECT groups.group_id,group_name,CCF_status,COUNT(DISTINCT user_paper.paper_id) as num \n" +
                    "FROM group_user \n" +
                    "RIGHT JOIN groups on group_user.group_id = groups.group_id \n" +
                    "LEFT JOIN user_paper on group_user.user_id=user_paper.user_id \n" +
                    "LEFT JOIN paper on user_paper.paper_id = paper.paper_id\n" +
                    "GROUP BY groups.group_id,CCF_status";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map> getGroupNumWithCategory() {
        session.clear();
        try {
            String sql = "SELECT groups.group_id,group_name,category,COUNT(DISTINCT user_paper.paper_id) as num \n" +
                    "FROM group_user \n" +
                    "RIGHT JOIN groups on group_user.group_id = groups.group_id \n" +
                    "LEFT JOIN user_paper on group_user.user_id=user_paper.user_id \n" +
                    "LEFT JOIN paper on user_paper.paper_id = paper.paper_id\n" +
                    "GROUP BY groups.group_id,category";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Map> getTypeNum(String type) {
        session.clear();
        try {

            String sql = "SELECT user.user_id,username ,count(*) as num FROM paper,user_paper,user where user.user_id=user_paper.user_id and paper.paper_id=user_paper.paper_id and type=? GROUP BY user.user_id";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, type);
            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map> getTypeNum() {
        session.clear();
        try {

            String sql = "SELECT user.user_id,username ,type,COUNT(DISTINCT user_paper.paper_id) as num\n" +
                    "FROM paper \n" +
                    "RIGHT JOIN user_paper on paper.paper_id = user_paper.paper_id \n" +
                    "RIGHT JOIN user on user_paper.user_id= user.user_id\n" +
                    "GROUP BY user.user_id,type";
            SQLQuery query = session.createSQLQuery(sql);

            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map> getFieldNum(String field) {
        session.clear();
        try {

            String sql = "SELECT  field_descr,count(*) as num\n" +
                    "FROM paper,field_paper,field \n" +
                    "where field.field_id=field_paper.field_id and paper.paper_id=field_paper.paper_id and field_descr=? \n" +
                    "GROUP BY field.field_id";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, field);
            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map> getFieldNum() {
        session.clear();
        try {

            String sql = "SELECT  field_descr,count(DISTINCT field_paper.paper_id) as num\n" +
                    "FROM field_paper\n" +
                    "LEFT JOIN paper on paper.paper_id = field_paper.paper_id\n" +
                    "RIGHT JOIN field on field_paper.field_id = field.field_id\n" +
                    "GROUP BY field.field_id";
            SQLQuery query = session.createSQLQuery(sql);

            query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
            List<Map> list = query.list();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
/*
    public static void main(String args[]) {
        SearchDao searchDao = new SearchDaoImpl();

        System.out.println(searchDao.getFieldNum().toString());

    }
*/
}
