package io.testomat.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Relationships {

    private Templates templates;
    private Users users;
    //    private Branch branch;
    private Children children;

}