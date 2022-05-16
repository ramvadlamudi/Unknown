package com.proj.donemcd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*@Entity
@Table(name = "product")*/
public class Product {
    private int id;
    private String prodcuctName;
    private String productDescription;
}
