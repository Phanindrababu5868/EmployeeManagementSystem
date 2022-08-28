package com.Quess.FinalProject.Service;

import com.Quess.FinalProject.Model.Assets;

import java.util.List;

public interface AssetsService {
    Assets saveAsset(Assets assets);
    List<Assets> getAllAssets();
    Assets getDataBYId(int id);
    Assets updateData(Assets assets,int id);
    void deleteAssets(int id);
}
