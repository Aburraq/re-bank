package com.royalreserve.assetservice.service;

import com.royalreserve.assetservice.dto.AssetRequest;
import com.royalreserve.assetservice.dto.AssetResponse;
import java.util.List;

public interface AssetService {
    AssetResponse createAsset(AssetRequest request);
    AssetResponse getAssetById(Long id);
    List<AssetResponse> getAllAssets();
    AssetResponse updateAsset(Long id, AssetRequest request);
    void deleteAsset(Long id);
}
