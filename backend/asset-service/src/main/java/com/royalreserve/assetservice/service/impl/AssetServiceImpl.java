package com.royalreserve.assetservice.service.impl;

import com.royalreserve.assetservice.dto.AssetRequest;
import com.royalreserve.assetservice.dto.AssetResponse;
import com.royalreserve.assetservice.exception.AssetNotFoundException;
import com.royalreserve.assetservice.model.Asset;
import com.royalreserve.assetservice.repository.AssetRepository;
import com.royalreserve.assetservice.service.AssetService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository repository;

    public AssetServiceImpl(AssetRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssetResponse createAsset(AssetRequest request) {
        Asset asset = new Asset(request.getOwnerId(), request.getName(), request.getValue(), LocalDateTime.now());
        Asset saved = repository.save(asset);
        return toResponse(saved);
    }

    @Override
    public AssetResponse getAssetById(Long id) {
        Asset asset = repository.findById(id)
                .orElseThrow(() -> new AssetNotFoundException(id));
        return toResponse(asset);
    }

    @Override
    public List<AssetResponse> getAllAssets() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AssetResponse updateAsset(Long id, AssetRequest request) {
        Asset asset = repository.findById(id)
                .orElseThrow(() -> new AssetNotFoundException(id));
        asset.setOwnerId(request.getOwnerId());
        asset.setName(request.getName());
        asset.setValue(request.getValue());
        Asset updated = repository.save(asset);
        return toResponse(updated);
    }

    @Override
    public void deleteAsset(Long id) {
        repository.deleteById(id);
    }

    private AssetResponse toResponse(Asset asset) {
        return new AssetResponse(
                asset.getId(),
                asset.getOwnerId(),
                asset.getName(),
                asset.getValue(),
                asset.getCreatedAt()
        );
    }
}
