package com.website.online.sale.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SpecificationProductDto {
    private InfoProduct infoProduct;
    private ProductDesignWeight productDesignWeight;
    private Processor processor;
    private Ram ram;
    private Screen screen;
    private Graphics graphics;
    private Storage storage;
    private RearCamera rearCamera;
    private SelfieCamera selfieCamera;
    private Security security;
    private Connectivity connectivity;
    private BatteryInfo batteryInfo;
    private OperatingSystem operatingSystem;
    private BoxAccessories boxAccessories;


    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class InfoProduct {
        private String origin;
        private  String launchDate;
        private String warrantyPeriod;
        private String storageInstructions;
        private String usageInstructions;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class ProductDesignWeight {
        private String dimensions;
        private String productWeight;
        private String material;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Processor {
        private String cpuVersion;
        private String numberOfCores;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Ram {
        private String ram;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Screen {
        private String screenSize;
        private String screenTechnology;
        private String screenStandard;
        private String screenResolution;
        private String screenColors;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Graphics {
        private String graphicsCPU;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Storage {
        private String storageRom;
        private String contactStorage;
        private String memoryCardSupport;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class RearCamera {
        private String numberOfRearCameras;
        private String camera;
        private String resolution;
        private String rearCameraVideo;
        private String features;

    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class SelfieCamera {
        private String numberOfSelfieCameras;
        private String resolution;
        private String features;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Security {
        private String security;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Connectivity {
        private String simSlotCount;
        private String simType;
        private String networkSupport;
        private String communicationPort;
        private String wifi;
        private String bluetooth;
        private String otherConnections;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class BatteryInfo {
        private String batteryType;
        private String batteryCapacity;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class OperatingSystem {
        private String oS;
        private String version;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class BoxAccessories {
        private String boxAccessories;
    }
}
