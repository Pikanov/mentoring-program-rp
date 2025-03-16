package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Widget {
    private String widgetName;
    private Integer widgetId;
    private String widgetType;
    private WidgetSize widgetSize;
    private WidgetPosition widgetPosition;
    private Map<String, Object> widgetOptions;
}
