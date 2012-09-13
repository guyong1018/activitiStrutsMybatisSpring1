package com.xjgzinfo.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TQjdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TQjdExample() {
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

        public Criteria andQjidIsNull() {
            addCriterion("QJID is null");
            return (Criteria) this;
        }

        public Criteria andQjidIsNotNull() {
            addCriterion("QJID is not null");
            return (Criteria) this;
        }

        public Criteria andQjidEqualTo(BigDecimal value) {
            addCriterion("QJID =", value, "qjid");
            return (Criteria) this;
        }

        public Criteria andQjidNotEqualTo(BigDecimal value) {
            addCriterion("QJID <>", value, "qjid");
            return (Criteria) this;
        }

        public Criteria andQjidGreaterThan(BigDecimal value) {
            addCriterion("QJID >", value, "qjid");
            return (Criteria) this;
        }

        public Criteria andQjidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("QJID >=", value, "qjid");
            return (Criteria) this;
        }

        public Criteria andQjidLessThan(BigDecimal value) {
            addCriterion("QJID <", value, "qjid");
            return (Criteria) this;
        }

        public Criteria andQjidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("QJID <=", value, "qjid");
            return (Criteria) this;
        }

        public Criteria andQjidIn(List<BigDecimal> values) {
            addCriterion("QJID in", values, "qjid");
            return (Criteria) this;
        }

        public Criteria andQjidNotIn(List<BigDecimal> values) {
            addCriterion("QJID not in", values, "qjid");
            return (Criteria) this;
        }

        public Criteria andQjidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QJID between", value1, value2, "qjid");
            return (Criteria) this;
        }

        public Criteria andQjidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QJID not between", value1, value2, "qjid");
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

        public Criteria andQjlyIsNull() {
            addCriterion("QJLY is null");
            return (Criteria) this;
        }

        public Criteria andQjlyIsNotNull() {
            addCriterion("QJLY is not null");
            return (Criteria) this;
        }

        public Criteria andQjlyEqualTo(String value) {
            addCriterion("QJLY =", value, "qjly");
            return (Criteria) this;
        }

        public Criteria andQjlyNotEqualTo(String value) {
            addCriterion("QJLY <>", value, "qjly");
            return (Criteria) this;
        }

        public Criteria andQjlyGreaterThan(String value) {
            addCriterion("QJLY >", value, "qjly");
            return (Criteria) this;
        }

        public Criteria andQjlyGreaterThanOrEqualTo(String value) {
            addCriterion("QJLY >=", value, "qjly");
            return (Criteria) this;
        }

        public Criteria andQjlyLessThan(String value) {
            addCriterion("QJLY <", value, "qjly");
            return (Criteria) this;
        }

        public Criteria andQjlyLessThanOrEqualTo(String value) {
            addCriterion("QJLY <=", value, "qjly");
            return (Criteria) this;
        }

        public Criteria andQjlyLike(String value) {
            addCriterion("QJLY like", value, "qjly");
            return (Criteria) this;
        }

        public Criteria andQjlyNotLike(String value) {
            addCriterion("QJLY not like", value, "qjly");
            return (Criteria) this;
        }

        public Criteria andQjlyIn(List<String> values) {
            addCriterion("QJLY in", values, "qjly");
            return (Criteria) this;
        }

        public Criteria andQjlyNotIn(List<String> values) {
            addCriterion("QJLY not in", values, "qjly");
            return (Criteria) this;
        }

        public Criteria andQjlyBetween(String value1, String value2) {
            addCriterion("QJLY between", value1, value2, "qjly");
            return (Criteria) this;
        }

        public Criteria andQjlyNotBetween(String value1, String value2) {
            addCriterion("QJLY not between", value1, value2, "qjly");
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