package com.royalreserve.assetservice.service;

import com.royalreserve.assetservice.dto.AssetRequest;
import com.royalreserve.assetservice.dto.AssetResponse;
import com.royalreserve.assetservice.exception.AssetNotFoundException;
import com.royalreserve.assetservice.model.Asset;
import com.royalreserve.assetservice.repository.AssetRepository;
import com.royalreserve.assetservice.service.impl.AssetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AssetServiceImplTest {
    @Mock
    private AssetRepository repository;

    private AssetServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new AssetServiceImpl(repository);
    }

    @Test
    void createAsset_shouldReturnResponse() {
        AssetRequest req = new AssetRequest();
        req.setOwnerId("user1");
        req.setName("Gold");
        req.setValue(new BigDecimal("1000"));

        Asset saved = new Asset("user1", "Gold", new BigDecimal("1000"), LocalDateTime.now());
        saved.setId(10L);
        when(repository.save(any(Asset.class))).thenReturn(saved);

        AssetResponse resp = service.createAsset(req);
        assertEquals(10L, resp.getId());
        assertEquals("user1", resp.getOwnerId());
        assertEquals("Gold", resp.getName());
        assertEquals(new BigDecimal("1000"), resp.getValue());
        verify(repository).save(any(Asset.class));
    }

    @Test
    void getAssetById_existing_returnsResponse() {
        Asset asset = new Asset("user2", "Silver", new BigDecimal("500"), LocalDateTime.now());
        asset.setId(20L);
        when(repository.findById(20L)).thenReturn(Optional.of(asset));

        AssetResponse resp = service.getAssetById(20L);
        assertEquals(20L, resp.getId());
        assertEquals("Silver", resp.getName());
    }

    @Test
    void getAssetById_missing_throwsNotFound() {
        when(repository.findById(30L)).thenReturn(Optional.empty());
        assertThrows(AssetNotFoundException.class, () -> service.getAssetById(30L));
    }

    @Test
    void getAllAssets_returnsList() {
        Asset a1 = new Asset("u1", "Stock", new BigDecimal("200"), LocalDateTime.now());
        a1.setId(1L);
        Asset a2 = new Asset("u2", "Bond", new BigDecimal("300"), LocalDateTime.now());
        a2.setId(2L);
        when(repository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<AssetResponse> list = service.getAllAssets();
        assertEquals(2, list.size());
    }

    @Test
    void updateAsset_existing_returnsUpdated() {
        Asset existing = new Asset("u3", "Old", new BigDecimal("50"), LocalDateTime.now());
        existing.setId(3L);
        when(repository.findById(3L)).thenReturn(Optional.of(existing));

        AssetRequest req = new AssetRequest();
        req.setOwnerId("u3");
        req.setName("New");
        req.setValue(new BigDecimal("75"));

        Asset updated = new Asset("u3", "New", new BigDecimal("75"), existing.getCreatedAt());
        updated.setId(3L);
        when(repository.save(any(Asset.class))).thenReturn(updated);

        AssetResponse resp = service.updateAsset(3L, req);
        assertEquals("New", resp.getName());
        assertEquals(new BigDecimal("75"), resp.getValue());
    }

    @Test
    void deleteAsset_callsRepository() {
        service.deleteAsset(5L);
        verify(repository).deleteById(5L);
    }
}
