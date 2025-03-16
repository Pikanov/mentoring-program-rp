package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dashboard {
    private Long id;
    private String name;
    private String description;
    private String owner;
    private List<Widget> widgets;
}
