package com.jobrapido.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ApplicationOutput {

    private Position position;
    private OutputStatus status;
}
