package com.purgerteam.log.trace.starter.metadata;

/**
 * 日志格式 信息
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class TraceFormatInfo {

    /**
     * 格式字段
     */
    private String formatField;

    /**
     * 字段显示内容
     */
    private String fieldVal;

    public String getFormatField() {
        return formatField;
    }

    public void setFormatField(String formatField) {
        this.formatField = formatField;
    }

    public String getFieldVal() {
        return fieldVal;
    }

    public void setFieldVal(String fieldVal) {
        this.fieldVal = fieldVal;
    }
}
