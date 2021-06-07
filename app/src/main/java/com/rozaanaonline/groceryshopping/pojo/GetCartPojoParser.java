package com.rozaanaonline.groceryshopping.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCartPojoParser {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cart")
    @Expose
    private Integer cart;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCart() {
        return cart;
    }

    public void setCart(Integer cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public class Product {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("image_id")
        @Expose
        private Integer imageId;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("minamount")
        @Expose
        private String minamount;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("create_at")
        @Expose
        private String createAt;
        @SerializedName("update_at")
        @Expose
        private String updateAt;
        @SerializedName("special_price")
        @Expose
        private Integer specialPrice;
        @SerializedName("special_from_date")
        @Expose
        private String specialFromDate;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("is_top")
        @Expose
        private Boolean isTop;
        @SerializedName("super_deal")
        @Expose
        private Boolean superDeal;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("r_coins")
        @Expose
        private Integer rCoins;
        @SerializedName("shelf_life")
        @Expose
        private String shelfLife;
        @SerializedName("product")
        @Expose
        private Integer product;
        @SerializedName("color")
        @Expose
        private Object color;
        @SerializedName("size")
        @Expose
        private Object size;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getImageId() {
            return imageId;
        }

        public void setImageId(Integer imageId) {
            this.imageId = imageId;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMinamount() {
            return minamount;
        }

        public void setMinamount(String minamount) {
            this.minamount = minamount;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(String updateAt) {
            this.updateAt = updateAt;
        }

        public Integer getSpecialPrice() {
            return specialPrice;
        }

        public void setSpecialPrice(Integer specialPrice) {
            this.specialPrice = specialPrice;
        }

        public String getSpecialFromDate() {
            return specialFromDate;
        }

        public void setSpecialFromDate(String specialFromDate) {
            this.specialFromDate = specialFromDate;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public Boolean getIsTop() {
            return isTop;
        }

        public void setIsTop(Boolean isTop) {
            this.isTop = isTop;
        }

        public Boolean getSuperDeal() {
            return superDeal;
        }

        public void setSuperDeal(Boolean superDeal) {
            this.superDeal = superDeal;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getrCoins() {
            return rCoins;
        }

        public void setrCoins(Integer rCoins) {
            this.rCoins = rCoins;
        }

        public String getShelfLife() {
            return shelfLife;
        }

        public void setShelfLife(String shelfLife) {
            this.shelfLife = shelfLife;
        }

        public Integer getProduct() {
            return product;
        }

        public void setProduct(Integer product) {
            this.product = product;
        }

        public Object getColor() {
            return color;
        }

        public void setColor(Object color) {
            this.color = color;
        }

        public Object getSize() {
            return size;
        }

        public void setSize(Object size) {
            this.size = size;
        }
    }
}
