package com.yangblog.utils.webapp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebAppApiResponse implements Serializable {

    private static final Map<String, Object> EMPTY_MAP = Collections.emptyMap();

    private static final long serialVersionUID = 1L;

    private int code = 1;
    private String message = "";
    private Map<String, Object> data = EMPTY_MAP;

    public WebAppApiResponse(Map<String, Object> data) {
        this.data = data;
    }

}
