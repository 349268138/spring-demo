package com.meituan.pay.finsecurity.po;

import com.meituan.pay.finsecurity.po.Vector;
import com.meituan.pay.finsecurity.po.enums.StatusEnum;
import java.util.ArrayList;
import java.util.List;

public class EventRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected boolean forUpdate;

    public EventRuleExample() {
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

    public void setForUpdate(boolean forUpdate) {
        this.forUpdate = forUpdate;
    }

    public boolean isForUpdate() {
        return forUpdate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that)
        return true;
        if((that == null) || (that.getClass() != this.getClass()))
        return false;
        EventRuleExample other = (EventRuleExample) that;
        return (orderByClause == other.orderByClause || (orderByClause != null && orderByClause.equals(other.orderByClause)))
            && distinct == other.distinct
            && (oredCriteria == other.oredCriteria || (oredCriteria != null && oredCriteria.equals(other.oredCriteria)))
            && forUpdate == other.forUpdate;
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int result = 1;
        result = prime * result + (null == orderByClause ? 0 : orderByClause.hashCode());
        result = prime * result + (distinct ? 1 : 0);
        result = prime * result + (null == oredCriteria ? 0 : oredCriteria.hashCode());
        result = prime * result + (forUpdate ? 1 : 0);
        return result;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> vectorListCriteria;

        protected List<Criterion> statusCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            vectorListCriteria = new ArrayList<Criterion>();
            statusCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getVectorListCriteria() {
            return vectorListCriteria;
        }

        protected void addVectorListCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            vectorListCriteria.add(new Criterion(condition, value, "com.meituan.pay.finsecurity.dao.typehandler.ListHandler"));
            allCriteria = null;
        }

        protected void addVectorListCriterion(String condition, List<Vector> value1, List<Vector> value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            vectorListCriteria.add(new Criterion(condition, value1, value2, "com.meituan.pay.finsecurity.dao.typehandler.ListHandler"));
            allCriteria = null;
        }

        public List<Criterion> getStatusCriteria() {
            return statusCriteria;
        }

        protected void addStatusCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            statusCriteria.add(new Criterion(condition, value, "com.meituan.pay.finsecurity.dao.typehandler.EnumTypeHandler"));
            allCriteria = null;
        }

        protected void addStatusCriterion(String condition, StatusEnum value1, StatusEnum value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            statusCriteria.add(new Criterion(condition, value1, value2, "com.meituan.pay.finsecurity.dao.typehandler.EnumTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0
                || vectorListCriteria.size() > 0
                || statusCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(vectorListCriteria);
                allCriteria.addAll(statusCriteria);
            }
            return allCriteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
            allCriteria = null;
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andVectorListIsNull() {
            addCriterion("vector_list is null");
            return (Criteria) this;
        }

        public Criteria andVectorListIsNotNull() {
            addCriterion("vector_list is not null");
            return (Criteria) this;
        }

        public Criteria andVectorListEqualTo(List<Vector> value) {
            addVectorListCriterion("vector_list =", value, "vectorList");
            return (Criteria) this;
        }

        public Criteria andVectorListNotEqualTo(List<Vector> value) {
            addVectorListCriterion("vector_list <>", value, "vectorList");
            return (Criteria) this;
        }

        public Criteria andVectorListGreaterThan(List<Vector> value) {
            addVectorListCriterion("vector_list >", value, "vectorList");
            return (Criteria) this;
        }

        public Criteria andVectorListGreaterThanOrEqualTo(List<Vector> value) {
            addVectorListCriterion("vector_list >=", value, "vectorList");
            return (Criteria) this;
        }

        public Criteria andVectorListLessThan(List<Vector> value) {
            addVectorListCriterion("vector_list <", value, "vectorList");
            return (Criteria) this;
        }

        public Criteria andVectorListLessThanOrEqualTo(List<Vector> value) {
            addVectorListCriterion("vector_list <=", value, "vectorList");
            return (Criteria) this;
        }

        public Criteria andVectorListLike(List<Vector> value) {
            addVectorListCriterion("vector_list like", value, "vectorList");
            return (Criteria) this;
        }

        public Criteria andVectorListNotLike(List<Vector> value) {
            addVectorListCriterion("vector_list not like", value, "vectorList");
            return (Criteria) this;
        }

        public Criteria andVectorListIn(List<List<Vector>> values) {
            addVectorListCriterion("vector_list in", values, "vectorList");
            return (Criteria) this;
        }

        public Criteria andVectorListNotIn(List<List<Vector>> values) {
            addVectorListCriterion("vector_list not in", values, "vectorList");
            return (Criteria) this;
        }

        public Criteria andVectorListBetween(List<Vector> value1, List<Vector> value2) {
            addVectorListCriterion("vector_list between", value1, value2, "vectorList");
            return (Criteria) this;
        }

        public Criteria andVectorListNotBetween(List<Vector> value1, List<Vector> value2) {
            addVectorListCriterion("vector_list not between", value1, value2, "vectorList");
            return (Criteria) this;
        }

        public Criteria andExtendedDataIsNull() {
            addCriterion("extended_data is null");
            return (Criteria) this;
        }

        public Criteria andExtendedDataIsNotNull() {
            addCriterion("extended_data is not null");
            return (Criteria) this;
        }

        public Criteria andExtendedDataEqualTo(String value) {
            addCriterion("extended_data =", value, "extendedData");
            return (Criteria) this;
        }

        public Criteria andExtendedDataNotEqualTo(String value) {
            addCriterion("extended_data <>", value, "extendedData");
            return (Criteria) this;
        }

        public Criteria andExtendedDataGreaterThan(String value) {
            addCriterion("extended_data >", value, "extendedData");
            return (Criteria) this;
        }

        public Criteria andExtendedDataGreaterThanOrEqualTo(String value) {
            addCriterion("extended_data >=", value, "extendedData");
            return (Criteria) this;
        }

        public Criteria andExtendedDataLessThan(String value) {
            addCriterion("extended_data <", value, "extendedData");
            return (Criteria) this;
        }

        public Criteria andExtendedDataLessThanOrEqualTo(String value) {
            addCriterion("extended_data <=", value, "extendedData");
            return (Criteria) this;
        }

        public Criteria andExtendedDataLike(String value) {
            addCriterion("extended_data like", value, "extendedData");
            return (Criteria) this;
        }

        public Criteria andExtendedDataNotLike(String value) {
            addCriterion("extended_data not like", value, "extendedData");
            return (Criteria) this;
        }

        public Criteria andExtendedDataIn(List<String> values) {
            addCriterion("extended_data in", values, "extendedData");
            return (Criteria) this;
        }

        public Criteria andExtendedDataNotIn(List<String> values) {
            addCriterion("extended_data not in", values, "extendedData");
            return (Criteria) this;
        }

        public Criteria andExtendedDataBetween(String value1, String value2) {
            addCriterion("extended_data between", value1, value2, "extendedData");
            return (Criteria) this;
        }

        public Criteria andExtendedDataNotBetween(String value1, String value2) {
            addCriterion("extended_data not between", value1, value2, "extendedData");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(StatusEnum value) {
            addStatusCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(StatusEnum value) {
            addStatusCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(StatusEnum value) {
            addStatusCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(StatusEnum value) {
            addStatusCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(StatusEnum value) {
            addStatusCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(StatusEnum value) {
            addStatusCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<StatusEnum> values) {
            addStatusCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<StatusEnum> values) {
            addStatusCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(StatusEnum value1, StatusEnum value2) {
            addStatusCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(StatusEnum value1, StatusEnum value2) {
            addStatusCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        @Override
        public boolean equals(Object that) {
            if (this == that)
            return true;
            if((that == null) || (that.getClass() != this.getClass()))
            return false;
            GeneratedCriteria other = (GeneratedCriteria) that;
            return (vectorListCriteria == other.vectorListCriteria || (vectorListCriteria != null && vectorListCriteria.equals(other.vectorListCriteria)))
                && (statusCriteria == other.statusCriteria || (statusCriteria != null && statusCriteria.equals(other.statusCriteria)))
                && (allCriteria == other.allCriteria || (allCriteria != null && allCriteria.equals(other.allCriteria)))
                && (criteria == other.criteria || (criteria != null && criteria.equals(other.criteria)));
        }

        @Override
        public int hashCode() {
            final int prime = 17;
            int result = 1;
            result = prime * result + (null == vectorListCriteria ? 0 : vectorListCriteria.hashCode());
            result = prime * result + (null == statusCriteria ? 0 : statusCriteria.hashCode());
            result = prime * result + (null == allCriteria ? 0 : allCriteria.hashCode());
            result = prime * result + (null == criteria ? 0 : criteria.hashCode());
            return result;
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

        @Override
        public boolean equals(Object that) {
            if (this == that)
            return true;
            if((that == null) || (that.getClass() != this.getClass()))
            return false;
            Criterion other = (Criterion) that;
            return (condition == other.condition || (condition != null && condition.equals(other.condition)))
                && (value == other.value || (value != null && value.equals(other.value)))
                && (secondValue == other.secondValue || (secondValue != null && secondValue.equals(other.secondValue)))
                && noValue == other.noValue
                && singleValue == other.singleValue
                && betweenValue == other.betweenValue
                && listValue == other.listValue
                && (typeHandler == other.typeHandler || (typeHandler != null && typeHandler.equals(other.typeHandler)));
        }

        @Override
        public int hashCode() {
            final int prime = 17;
            int result = 1;
            result = prime * result + (null == condition ? 0 : condition.hashCode());
            result = prime * result + (null == value ? 0 : value.hashCode());
            result = prime * result + (null == secondValue ? 0 : secondValue.hashCode());
            result = prime * result + (noValue ? 1 : 0);
            result = prime * result + (singleValue ? 1 : 0);
            result = prime * result + (betweenValue ? 1 : 0);
            result = prime * result + (listValue ? 1 : 0);
            result = prime * result + (null == typeHandler ? 0 : typeHandler.hashCode());
            return result;
        }
    }
}