package com.purgerteam.log.trace.starter;

/**
 * trace 格式 枚举
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public enum TraceFormatEnum {

    /**
     * 当前项目名称
     */
    LOCAL_NAME {
        @Override
        public String getValue() {
            return Constants.LOCAL_NAME;
        }
    },

    /**
     * 布尔类型。表示是否要将该信息输出到类似Zipkin这样的聚合器进行收集和展示
     */
    EXPORT {
        @Override
        public String getValue() {
            return Constants.LEGACY_EXPORTABLE_NAME;
        }
    },

    /**
     * parent id 父请求id
     */
    PARENT_SPAN_ID {
        @Override
        public String getValue() {
            return Constants.LEGACY_PARENT_ID_NAME;
        }
    },

    /**
     * parent service name 父服务名称
     */
    PARENT_NAME {
        @Override
        public String getValue() {
            return Constants.LEGACY_PARENT_SERVICE_NAME;
        }
    },

    /**
     * 为一个请求分配的ID号，用来标识一条请求链路。
     */
    TRACE_ID {
        @Override
        public String getValue() {
            return Constants.LEGACY_TRACE_ID_NAME;
        }
    },

    /**
     * 表示一个基本的工作单元，一个请求可以包含多个步骤，每个步骤都拥有自己的spanId。
     * 一个请求包含一个TraceId，多个SpanId
     */
    SPAN_ID {
        @Override
        public String getValue() {
            return Constants.LEGACY_SPAN_ID_NAME;
        }
    };

    /**
     * 获取参数名称
     */
    public abstract String getValue();
}
