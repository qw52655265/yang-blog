package com.yangblog.utils.webapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public abstract class WebappBaseApiController {

    private ObjectMapper mapper = new ObjectMapper();

    public WebappBaseApiController() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    protected Integer getUserId(HttpServletRequest request) {
        return Integer.parseInt((String)request.getAttribute("userId"));
    }

    protected Long getUserCode(HttpServletRequest request) {
        return (Long) request.getAttribute("userCode");
    }

    protected Integer getOptionalIntField(JsonNode jsonNode, String fieldName) {
        if (Objects.isNull(jsonNode)) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, "jsonNode is null", false);
        } else {
            JsonNode node = jsonNode.get(fieldName);
            if (node == null || node.isNull()) {
                return null;
            }
            return node.asInt();
        }
    }

    protected int getRequiredIntField(JsonNode jsonNode, String fieldName) {
        if (Objects.isNull(jsonNode) || Objects.isNull(jsonNode.get(fieldName))) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, String.format("required field %s doesn't exist", fieldName), false);
        } else {
            return jsonNode.get(fieldName).asInt();
        }
    }


    protected String getOptionalTextField(JsonNode jsonNode, String fieldName) {
        if (Objects.isNull(jsonNode)) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, "jsonNode is null", false);
        } else {
            return Objects.isNull(jsonNode.get(fieldName)) ? null : jsonNode.get(fieldName).asText();
        }
    }

    protected String getRequiredTextField(JsonNode jsonNode, String fieldName) {
        if (Objects.isNull(jsonNode) || Objects.isNull(jsonNode.get(fieldName))) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, String.format("required field %s doesn't exist", fieldName), false);
        } else {
            return jsonNode.get(fieldName).asText();
        }
    }

    protected Boolean getOptionalBooleanField(JsonNode jsonNode, String fieldName) {
        if (Objects.isNull(jsonNode)) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, "jsonNode is null", false);
        } else {
            JsonNode node = jsonNode.get(fieldName);
            if (node == null || node.isNull()) {
                return null;
            }
            return node.asBoolean();
        }
    }

    protected boolean getRequiredBooleanField(JsonNode jsonNode, String fieldName) {
        if (Objects.isNull(jsonNode) || Objects.isNull(jsonNode.get(fieldName))) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, String.format("required field %s doesn't exist", fieldName), false);
        } else {
            return jsonNode.get(fieldName).asBoolean();
        }
    }

    protected Long getOptionalLongField(JsonNode jsonNode, String fieldName) {
        if (Objects.isNull(jsonNode)) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, "jsonNode is null", false);
        } else {
            JsonNode node = jsonNode.get(fieldName);
            if (node == null || node.isNull()) {
                return null;
            }
            return node.asLong();
        }
    }

    protected long getRequiredLongField(JsonNode jsonNode, String fieldName) {
        if (Objects.isNull(jsonNode) || Objects.isNull(jsonNode.get(fieldName))) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, String.format("required field %s doesn't exist", fieldName), false);
        } else {
            return jsonNode.get(fieldName).asLong();
        }
    }

    protected Double getOptionalDoubleField(JsonNode jsonNode, String fieldName) {
        if (Objects.isNull(jsonNode)) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, "jsonNode is null", false);
        } else {
            JsonNode node = jsonNode.get(fieldName);
            if (node == null || node.isNull()) {
                return null;
            } else {
                return node.asDouble();
            }
        }
    }

    protected <T> T getObject(JsonNode object, String key, Class<T> clazz) {
        if (null == object || null == object.get(key) || object.isNull()) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, String.format("required field %s doesn't exist", key));
        } else {
            return mapper.convertValue(object.get(key), clazz);
        }
    }

    protected <T> T getObject(JsonNode object, String key, TypeReference<T> clazz) {
        if (null == object || null == object.get(key) || object.isNull()) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, String.format("required field %s doesn't exist", key));
        } else {
            return mapper.convertValue(object.get(key), clazz);
        }
    }

    protected <T> T getObject(JsonNode object, Class<T> clazz) {
        if (null == object || object.isNull()) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, "object is null");
        } else {
            return mapper.convertValue(object, clazz);
        }
    }

    protected <T> List<T> getObjects(JsonNode node, Class<T> clazz) {
        if (node == null || !node.isArray()) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, "bad request");
        }
        return mapper.convertValue(node, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    protected <T> List<T> getObjects(JsonNode node, String key, Class<T> clazz) {
        if (node == null || !node.has(key) || !node.get(key).isArray()) {
            throw new WebappServiceException(WebappRetCode.BAD_REQUEST, "bad request");
        }
        return mapper.convertValue(node.get(key), mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
