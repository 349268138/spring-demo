package com.meituan.pay.finsecurity.po;

import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.po.enums.StatusEnum;
import java.util.ArrayList;
import java.util.List;

public class DataRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected boolean forUpdate;

    public DataRuleExample() {
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
        DataRuleExample other = (DataRuleExample) that;
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
        protected List<Criterion> typeCriteria;

        protected List<Criterion> statusCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            typeCriteria = new ArrayList<Criterion>();
            statusCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getTypeCriteria() {
            return typeCriteria;
        }

        protected void addTypeCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            typeCriteria.add(new Criterion(condition, value, "com.meituan.pay.finsecurity.dao.typehandler.EnumTypeHandler"));
            allCriteria = null;
        }

        protected void addTypeCriterion(String condition, DataAccessTypeEnum value1, DataAccessTypeEnum value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            typeCriteria.add(new Criterion(condition, value1, value2, "com.meituan.pay.finsecurity.dao.typehandler.EnumTypeHandler"));
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
                || typeCriteria.size() > 0
                || statusCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(typeCriteria);
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

        public Criteria andEventIdIsNull() {
            addCriterion("event_id is null");
            return (Criteria) this;
        }

        public Criteria andEventIdIsNotNull() {
            addCriterion("event_id is not null");
            return (Criteria) this;
        }

        public Criteria andEventIdEqualTo(Long value) {
            addCriterion("event_id =", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdNotEqualTo(Long value) {
            addCriterion("event_id <>", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdGreaterThan(Long value) {
            addCriterion("event_id >", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdGreaterThanOrEqualTo(Long value) {
            addCriterion("event_id >=", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdLessThan(Long value) {
            addCriterion("event_id <", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdLessThanOrEqualTo(Long value) {
            addCriterion("event_id <=", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdIn(List<Long> values) {
            addCriterion("event_id in", values, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdNotIn(List<Long> values) {
            addCriterion("event_id not in", values, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdBetween(Long value1, Long value2) {
            addCriterion("event_id between", value1, value2, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdNotBetween(Long value1, Long value2) {
            addCriterion("event_id not between", value1, value2, "eventId");
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

        public Criteria andAliasIsNull() {
            addCriterion("alias is null");
            return (Criteria) this;
        }

        public Criteria andAliasIsNotNull() {
            addCriterion("alias is not null");
            return (Criteria) this;
        }

        public Criteria andAliasEqualTo(String value) {
            addCriterion("alias =", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotEqualTo(String value) {
            addCriterion("alias <>", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThan(String value) {
            addCriterion("alias >", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasGreaterThanOrEqualTo(String value) {
            addCriterion("alias >=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThan(String value) {
            addCriterion("alias <", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLessThanOrEqualTo(String value) {
            addCriterion("alias <=", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasLike(String value) {
            addCriterion("alias like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotLike(String value) {
            addCriterion("alias not like", value, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasIn(List<String> values) {
            addCriterion("alias in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotIn(List<String> values) {
            addCriterion("alias not in", values, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasBetween(String value1, String value2) {
            addCriterion("alias between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andAliasNotBetween(String value1, String value2) {
            addCriterion("alias not between", value1, value2, "alias");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(DataAccessTypeEnum value) {
            addTypeCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(DataAccessTypeEnum value) {
            addTypeCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(DataAccessTypeEnum value) {
            addTypeCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(DataAccessTypeEnum value) {
            addTypeCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(DataAccessTypeEnum value) {
            addTypeCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(DataAccessTypeEnum value) {
            addTypeCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<DataAccessTypeEnum> values) {
            addTypeCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<DataAccessTypeEnum> values) {
            addTypeCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(DataAccessTypeEnum value1, DataAccessTypeEnum value2) {
            addTypeCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(DataAccessTypeEnum value1, DataAccessTypeEnum value2) {
            addTypeCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andKeyExprIsNull() {
            addCriterion("key_expr is null");
            return (Criteria) this;
        }

        public Criteria andKeyExprIsNotNull() {
            addCriterion("key_expr is not null");
            return (Criteria) this;
        }

        public Criteria andKeyExprEqualTo(String value) {
            addCriterion("key_expr =", value, "keyExpr");
            return (Criteria) this;
        }

        public Criteria andKeyExprNotEqualTo(String value) {
            addCriterion("key_expr <>", value, "keyExpr");
            return (Criteria) this;
        }

        public Criteria andKeyExprGreaterThan(String value) {
            addCriterion("key_expr >", value, "keyExpr");
            return (Criteria) this;
        }

        public Criteria andKeyExprGreaterThanOrEqualTo(String value) {
            addCriterion("key_expr >=", value, "keyExpr");
            return (Criteria) this;
        }

        public Criteria andKeyExprLessThan(String value) {
            addCriterion("key_expr <", value, "keyExpr");
            return (Criteria) this;
        }

        public Criteria andKeyExprLessThanOrEqualTo(String value) {
            addCriterion("key_expr <=", value, "keyExpr");
            return (Criteria) this;
        }

        public Criteria andKeyExprLike(String value) {
            addCriterion("key_expr like", value, "keyExpr");
            return (Criteria) this;
        }

        public Criteria andKeyExprNotLike(String value) {
            addCriterion("key_expr not like", value, "keyExpr");
            return (Criteria) this;
        }

        public Criteria andKeyExprIn(List<String> values) {
            addCriterion("key_expr in", values, "keyExpr");
            return (Criteria) this;
        }

        public Criteria andKeyExprNotIn(List<String> values) {
            addCriterion("key_expr not in", values, "keyExpr");
            return (Criteria) this;
        }

        public Criteria andKeyExprBetween(String value1, String value2) {
            addCriterion("key_expr between", value1, value2, "keyExpr");
            return (Criteria) this;
        }

        public Criteria andKeyExprNotBetween(String value1, String value2) {
            addCriterion("key_expr not between", value1, value2, "keyExpr");
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
            return (typeCriteria == other.typeCriteria || (typeCriteria != null && typeCriteria.equals(other.typeCriteria)))
                && (statusCriteria == other.statusCriteria || (statusCriteria != null && statusCriteria.equals(other.statusCriteria)))
                && (allCriteria == other.allCriteria || (allCriteria != null && allCriteria.equals(other.allCriteria)))
                && (criteria == other.criteria || (criteria != null && criteria.equals(other.criteria)));
        }

        @Override
        public int hashCode() {
            final int prime = 17;
            int result = 1;
            result = prime * result + (null == typeCriteria ? 0 : typeCriteria.hashCode());
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