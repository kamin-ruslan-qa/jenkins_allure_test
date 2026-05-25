package storage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Warehouse {
    private String warehouseCode;
    private String manager;
    private List<StockItem> stockItems;

    // === Геттеры и сеттеры ===
    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public List<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    @Override
    public String toString() {
        return "Warehouse{code='" + warehouseCode + "', manager='" + manager + "', items=" + stockItems + "}";
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StockItem {
        private int articleId;
        private String productLabel;
        private double unitPrice;
        private int quantityAvailable;
        private boolean active;  // ← переименовал

        public int getArticleId() {
            return articleId;
        }

        public String getProductLabel() {
            return productLabel;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public int getQuantityAvailable() {
            return quantityAvailable;
        }

        public boolean isActive() {
            return active;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public void setProductLabel(String productLabel) {
            this.productLabel = productLabel;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public void setQuantityAvailable(int quantityAvailable) {
            this.quantityAvailable = quantityAvailable;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
    }
}
