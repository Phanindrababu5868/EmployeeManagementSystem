package com.Quess.FinalProject.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Assets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Assets_id;

    @NotEmpty(message = "assets name Should not empty")
    private String assetName;

    @NotEmpty(message = "assetsType should not empty")
    private String assetType;

    @Min(value = 1)
    private int numberOfItems;

    @Min(value = 1)
    private long purchaseCost;

    @Min(value =1)
    private long presentCost;

    @Column
    @Min(value = 1,message = "Entre correct organizationId")
    private int organizationId;



}
