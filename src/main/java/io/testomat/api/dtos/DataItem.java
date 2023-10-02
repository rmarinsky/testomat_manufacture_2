package io.testomat.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataItem {

    private Relationships relationships;
    private Attributes attributes;
    private String id;
    private String type;

}