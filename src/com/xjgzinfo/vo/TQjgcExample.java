package com.xjgzinfo.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TQjgcExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TQjgcExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(BigDecimal value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(BigDecimal value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(BigDecimal value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(BigDecimal value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<BigDecimal> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<BigDecimal> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andQjrIsNull() {
            addCriterion("QJR is null");
            return (Criteria) this;
        }

        public Criteria andQjrIsNotNull() {
            addCriterion("QJR is not null");
            return (Criteria) this;
        }

        public Criteria andQjrEqualTo(String value) {
            addCriterion("QJR =", value, "qjr");
            return (Criteria) this;
        }

        public Criteria andQjrNotEqualTo(String value) {
            addCriterion("QJR <>", value, "qjr");
            return (Criteria) this;
        }

        public Criteria andQjrGreaterThan(String value) {
            addCriterion("QJR >", value, "qjr");
            return (Criteria) this;
        }

        public Criteria andQjrGreaterThanOrEqualTo(String value) {
            addCriterion("QJR >=", value, "qjr");
            return (Criteria) this;
        }

        public Criteria andQjrLessThan(String value) {
            addCriterion("QJR <", value, "qjr");
            return (Criteria) this;
        }

        public Criteria andQjrLessThanOrEqualTo(String value) {
            addCriterion("QJR <=", value, "qjr");
            return (Criteria) this;
        }

        public Criteria andQjrLike(String value) {
            addCriterion("QJR like", value, "qjr");
            return (Criteria) this;
        }

        public Criteria andQjrNotLike(String value) {
            addCriterion("QJR not like", value, "qjr");
            return (Criteria) this;
        }

        public Criteria andQjrIn(List<String> values) {
            addCriterion("QJR in", values, "qjr");
            return (Criteria) this;
        }

        public Criteria andQjrNotIn(List<String> values) {
            addCriterion("QJR not in", values, "qjr");
            return (Criteria) this;
        }

        public Criteria andQjrBetween(String value1, String value2) {
            addCriterion("QJR between", value1, value2, "qjr");
            return (Criteria) this;
        }

        public Criteria andQjrNotBetween(String value1, String value2) {
            addCriterion("QJR not between", value1, value2, "qjr");
            return (Criteria) this;
        }

        public Criteria andQjsjIsNull() {
            addCriterion("QJSJ is null");
            return (Criteria) this;
        }

        public Criteria andQjsjIsNotNull() {
            addCriterion("QJSJ is not null");
            return (Criteria) this;
        }

        public Criteria andQjsjEqualTo(Date value) {
            addCriterion("QJSJ =", value, "qjsj");
            return (Criteria) this;
        }

        public Criteria andQjsjNotEqualTo(Date value) {
            addCriterion("QJSJ <>", value, "qjsj");
            return (Criteria) this;
        }

        public Criteria andQjsjGreaterThan(Date value) {
            addCriterion("QJSJ >", value, "qjsj");
            return (Criteria) this;
        }

        public Criteria andQjsjGreaterThanOrEqualTo(Date value) {
            addCriterion("QJSJ >=", value, "qjsj");
            return (Criteria) this;
        }

        public Criteria andQjsjLessThan(Date value) {
            addCriterion("QJSJ <", value, "qjsj");
            return (Criteria) this;
        }

        public Criteria andQjsjLessThanOrEqualTo(Date value) {
            addCriterion("QJSJ <=", value, "qjsj");
            return (Criteria) this;
        }

        public Criteria andQjsjIn(List<Date> values) {
            addCriterion("QJSJ in", values, "qjsj");
            return (Criteria) this;
        }

        public Criteria andQjsjNotIn(List<Date> values) {
            addCriterion("QJSJ not in", values, "qjsj");
            return (Criteria) this;
        }

        public Criteria andQjsjBetween(Date value1, Date value2) {
            addCriterion("QJSJ between", value1, value2, "qjsj");
            return (Criteria) this;
        }

        public Criteria andQjsjNotBetween(Date value1, Date value2) {
            addCriterion("QJSJ not between", value1, value2, "qjsj");
            return (Criteria) this;
        }

        public Criteria andQjtsIsNull() {
            addCriterion("QJTS is null");
            return (Criteria) this;
        }

        public Criteria andQjtsIsNotNull() {
            addCriterion("QJTS is not null");
            return (Criteria) this;
        }

        public Criteria andQjtsEqualTo(BigDecimal value) {
            addCriterion("QJTS =", value, "qjts");
            return (Criteria) this;
        }

        public Criteria andQjtsNotEqualTo(BigDecimal value) {
            addCriterion("QJTS <>", value, "qjts");
            return (Criteria) this;
        }

        public Criteria andQjtsGreaterThan(BigDecimal value) {
            addCriterion("QJTS >", value, "qjts");
            return (Criteria) this;
        }

        public Criteria andQjtsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("QJTS >=", value, "qjts");
            return (Criteria) this;
        }

        public Criteria andQjtsLessThan(BigDecimal value) {
            addCriterion("QJTS <", value, "qjts");
            return (Criteria) this;
        }

        public Criteria andQjtsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("QJTS <=", value, "qjts");
            return (Criteria) this;
        }

        public Criteria andQjtsIn(List<BigDecimal> values) {
            addCriterion("QJTS in", values, "qjts");
            return (Criteria) this;
        }

        public Criteria andQjtsNotIn(List<BigDecimal> values) {
            addCriterion("QJTS not in", values, "qjts");
            return (Criteria) this;
        }

        public Criteria andQjtsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QJTS between", value1, value2, "qjts");
            return (Criteria) this;
        }

        public Criteria andQjtsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QJTS not between", value1, value2, "qjts");
            return (Criteria) this;
        }

        public Criteria andSpyjIsNull() {
            addCriterion("SPYJ is null");
            return (Criteria) this;
        }

        public Criteria andSpyjIsNotNull() {
            addCriterion("SPYJ is not null");
            return (Criteria) this;
        }

        public Criteria andSpyjEqualTo(String value) {
            addCriterion("SPYJ =", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjNotEqualTo(String value) {
            addCriterion("SPYJ <>", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjGreaterThan(String value) {
            addCriterion("SPYJ >", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjGreaterThanOrEqualTo(String value) {
            addCriterion("SPYJ >=", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjLessThan(String value) {
            addCriterion("SPYJ <", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjLessThanOrEqualTo(String value) {
            addCriterion("SPYJ <=", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjLike(String value) {
            addCriterion("SPYJ like", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjNotLike(String value) {
            addCriterion("SPYJ not like", value, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjIn(List<String> values) {
            addCriterion("SPYJ in", values, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjNotIn(List<String> values) {
            addCriterion("SPYJ not in", values, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjBetween(String value1, String value2) {
            addCriterion("SPYJ between", value1, value2, "spyj");
            return (Criteria) this;
        }

        public Criteria andSpyjNotBetween(String value1, String value2) {
            addCriterion("SPYJ not between", value1, value2, "spyj");
            return (Criteria) this;
        }

        public Criteria andSprIsNull() {
            addCriterion("SPR is null");
            return (Criteria) this;
        }

        public Criteria andSprIsNotNull() {
            addCriterion("SPR is not null");
            return (Criteria) this;
        }

        public Criteria andSprEqualTo(String value) {
            addCriterion("SPR =", value, "spr");
            return (Criteria) this;
        }

        public Criteria andSprNotEqualTo(String value) {
            addCriterion("SPR <>", value, "spr");
            return (Criteria) this;
        }

        public Criteria andSprGreaterThan(String value) {
            addCriterion("SPR >", value, "spr");
            return (Criteria) this;
        }

        public Criteria andSprGreaterThanOrEqualTo(String value) {
            addCriterion("SPR >=", value, "spr");
            return (Criteria) this;
        }

        public Criteria andSprLessThan(String value) {
            addCriterion("SPR <", value, "spr");
            return (Criteria) this;
        }

        public Criteria andSprLessThanOrEqualTo(String value) {
            addCriterion("SPR <=", value, "spr");
            return (Criteria) this;
        }

        public Criteria andSprLike(String value) {
            addCriterion("SPR like", value, "spr");
            return (Criteria) this;
        }

        public Criteria andSprNotLike(String value) {
            addCriterion("SPR not like", value, "spr");
            return (Criteria) this;
        }

        public Criteria andSprIn(List<String> values) {
            addCriterion("SPR in", values, "spr");
            return (Criteria) this;
        }

        public Criteria andSprNotIn(List<String> values) {
            addCriterion("SPR not in", values, "spr");
            return (Criteria) this;
        }

        public Criteria andSprBetween(String value1, String value2) {
            addCriterion("SPR between", value1, value2, "spr");
            return (Criteria) this;
        }

        public Criteria andSprNotBetween(String value1, String value2) {
            addCriterion("SPR not between", value1, value2, "spr");
            return (Criteria) this;
        }

        public Criteria andSpsjIsNull() {
            addCriterion("SPSJ is null");
            return (Criteria) this;
        }

        public Criteria andSpsjIsNotNull() {
            addCriterion("SPSJ is not null");
            return (Criteria) this;
        }

        public Criteria andSpsjEqualTo(Date value) {
            addCriterion("SPSJ =", value, "spsj");
            return (Criteria) this;
        }

        public Criteria andSpsjNotEqualTo(Date value) {
            addCriterion("SPSJ <>", value, "spsj");
            return (Criteria) this;
        }

        public Criteria andSpsjGreaterThan(Date value) {
            addCriterion("SPSJ >", value, "spsj");
            return (Criteria) this;
        }

        public Criteria andSpsjGreaterThanOrEqualTo(Date value) {
            addCriterion("SPSJ >=", value, "spsj");
            return (Criteria) this;
        }

        public Criteria andSpsjLessThan(Date value) {
            addCriterion("SPSJ <", value, "spsj");
            return (Criteria) this;
        }

        public Criteria andSpsjLessThanOrEqualTo(Date value) {
            addCriterion("SPSJ <=", value, "spsj");
            return (Criteria) this;
        }

        public Criteria andSpsjIn(List<Date> values) {
            addCriterion("SPSJ in", values, "spsj");
            return (Criteria) this;
        }

        public Criteria andSpsjNotIn(List<Date> values) {
            addCriterion("SPSJ not in", values, "spsj");
            return (Criteria) this;
        }

        public Criteria andSpsjBetween(Date value1, Date value2) {
            addCriterion("SPSJ between", value1, value2, "spsj");
            return (Criteria) this;
        }

        public Criteria andSpsjNotBetween(Date value1, Date value2) {
            addCriterion("SPSJ not between", value1, value2, "spsj");
            return (Criteria) this;
        }

        public Criteria andProidIsNull() {
            addCriterion("PROID is null");
            return (Criteria) this;
        }

        public Criteria andProidIsNotNull() {
            addCriterion("PROID is not null");
            return (Criteria) this;
        }

        public Criteria andProidEqualTo(Object value) {
            addCriterion("PROID =", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidNotEqualTo(Object value) {
            addCriterion("PROID <>", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidGreaterThan(Object value) {
            addCriterion("PROID >", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidGreaterThanOrEqualTo(Object value) {
            addCriterion("PROID >=", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidLessThan(Object value) {
            addCriterion("PROID <", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidLessThanOrEqualTo(Object value) {
            addCriterion("PROID <=", value, "proid");
            return (Criteria) this;
        }

        public Criteria andProidIn(List<Object> values) {
            addCriterion("PROID in", values, "proid");
            return (Criteria) this;
        }

        public Criteria andProidNotIn(List<Object> values) {
            addCriterion("PROID not in", values, "proid");
            return (Criteria) this;
        }

        public Criteria andProidBetween(Object value1, Object value2) {
            addCriterion("PROID between", value1, value2, "proid");
            return (Criteria) this;
        }

        public Criteria andProidNotBetween(Object value1, Object value2) {
            addCriterion("PROID not between", value1, value2, "proid");
            return (Criteria) this;
        }

        public Criteria andTasknameIsNull() {
            addCriterion("TASKNAME is null");
            return (Criteria) this;
        }

        public Criteria andTasknameIsNotNull() {
            addCriterion("TASKNAME is not null");
            return (Criteria) this;
        }

        public Criteria andTasknameEqualTo(String value) {
            addCriterion("TASKNAME =", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameNotEqualTo(String value) {
            addCriterion("TASKNAME <>", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameGreaterThan(String value) {
            addCriterion("TASKNAME >", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameGreaterThanOrEqualTo(String value) {
            addCriterion("TASKNAME >=", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameLessThan(String value) {
            addCriterion("TASKNAME <", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameLessThanOrEqualTo(String value) {
            addCriterion("TASKNAME <=", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameLike(String value) {
            addCriterion("TASKNAME like", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameNotLike(String value) {
            addCriterion("TASKNAME not like", value, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameIn(List<String> values) {
            addCriterion("TASKNAME in", values, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameNotIn(List<String> values) {
            addCriterion("TASKNAME not in", values, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameBetween(String value1, String value2) {
            addCriterion("TASKNAME between", value1, value2, "taskname");
            return (Criteria) this;
        }

        public Criteria andTasknameNotBetween(String value1, String value2) {
            addCriterion("TASKNAME not between", value1, value2, "taskname");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}