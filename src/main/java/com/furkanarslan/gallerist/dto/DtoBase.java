package com.furkanarslan.gallerist.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;
@Data
@MappedSuperclass
public class DtoBase {

    private Long id;

    private Date createTime;
}
