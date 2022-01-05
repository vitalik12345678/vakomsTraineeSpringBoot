package com.valoms.vakomstraineespringboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationJSON extends Throwable implements Serializable {

    private static final long serialVersionUID = 7702L;

    private String longitude;
    private String latitude;

}
