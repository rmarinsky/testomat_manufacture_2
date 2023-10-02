package io.testomat.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuiteDto {

    private DataItem data;

    @JsonProperty("included")
    private List<Object> included;

}
