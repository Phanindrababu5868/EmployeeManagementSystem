package com.Quess.FinalProject.Service;

//import com.Quess.FinalProject.Exception.ResourceNotFoundException;
import com.Quess.FinalProject.Exception.ResourceNotFoundException;
import com.Quess.FinalProject.Model.Assets;
import com.Quess.FinalProject.Repository.AssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssetsServiceImpl implements AssetsService{
    @Autowired
    private AssetsRepository assetsRepository;

    public AssetsServiceImpl(AssetsRepository assetsRepository) {
        this.assetsRepository = assetsRepository;
    }

    @Override
    public Assets saveAsset(Assets assets) {
        return assetsRepository.save(assets);
    }

    @Override
    public List<Assets> getAllAssets() {
        return (List<Assets>) assetsRepository.findAll();
    }

    @Override
    public Assets getDataBYId(int id) {
        return assetsRepository.findById(id).orElseThrow(() -> new com.Quess.FinalProject.Exception.ResourceNotFoundException("Assets not found By given Id : "+id));
    }

    @Override
    public Assets updateData(Assets assets, int id) {
        Assets assets1=assetsRepository.findById(id).orElseThrow(()-> new com.Quess.FinalProject.Exception.ResourceNotFoundException("Assets not found By given Id : "+id));
        assets1.setAssetName(assets.getAssetName());
        assets1.setAssetType(assets.getAssetType());
        assets1.setNumberOfItems(assets.getNumberOfItems());
        assets1.setPurchaseCost(assets.getPurchaseCost());
        assets1.setPresentCost(assets.getPresentCost());
        assets1.setOrganizationId(assets1.getOrganizationId());
        assetsRepository.save(assets1);
        return assets1;
    }

    @Override
    public void deleteAssets(int id) {
        Assets assets = assetsRepository.findById(id).orElseThrow(() -> new com.Quess.FinalProject.Exception.ResourceNotFoundException("Assets not found By given Id : "+id));
            assetsRepository.deleteById(id);

    }
}
