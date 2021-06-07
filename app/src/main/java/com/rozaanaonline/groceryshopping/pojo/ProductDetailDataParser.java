package com.rozaanaonline.groceryshopping.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailDataParser {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("color")
    @Expose
    private Object color;
    @SerializedName("size")
    @Expose
    private Object size;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("parent_product")
    @Expose
    private ParentProduct parentProduct;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("r_coins")
    @Expose
    private Integer rCoins;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("shelf_life")
    @Expose
    private String shelf_life;
    @SerializedName("variants")
    @Expose
    private List<Variant> variants = null;

    public String getShelf_life() {
        return shelf_life;
    }

    public void setShelf_life(String shelf_life) {
        this.shelf_life = shelf_life;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public ParentProduct getParentProduct() {
        return parentProduct;
    }

    public void setParentProduct(ParentProduct parentProduct) {
        this.parentProduct = parentProduct;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Integer getrCoins() {
        return rCoins;
    }

    public void setrCoins(Integer rCoins) {
        this.rCoins = rCoins;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }


    public class Image {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("image")
        @Expose
        private String image;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }

    public class ParentProduct {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("keywords")
        @Expose
        private String keywords;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("amount")
        @Expose
        private Integer amount;
        @SerializedName("minamount")
        @Expose
        private Integer minamount;
        @SerializedName("variant")
        @Expose
        private String variant;
        @SerializedName("detail")
        @Expose
        private String detail;
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
        @SerializedName("small_image")
        @Expose
        private String smallImage;
        @SerializedName("special_price")
        @Expose
        private Integer specialPrice;
        @SerializedName("special_from_date")
        @Expose
        private String specialFromDate;
        @SerializedName("discount")
        @Expose
        private Integer discount;
        @SerializedName("manufactured_by")
        @Expose
        private String manufacturedBy;
        @SerializedName("is_top")
        @Expose
        private Boolean isTop;
        @SerializedName("super_deal")
        @Expose
        private Boolean superDeal;
        @SerializedName("category")
        @Expose
        private Integer category;

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

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public Integer getMinamount() {
            return minamount;
        }

        public void setMinamount(Integer minamount) {
            this.minamount = minamount;
        }

        public String getVariant() {
            return variant;
        }

        public void setVariant(String variant) {
            this.variant = variant;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
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

        public String getSmallImage() {
            return smallImage;
        }

        public void setSmallImage(String smallImage) {
            this.smallImage = smallImage;
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

        public Integer getDiscount() {
            return discount;
        }

        public void setDiscount(Integer discount) {
            this.discount = discount;
        }

        public String getManufacturedBy() {
            return manufacturedBy;
        }

        public void setManufacturedBy(String manufacturedBy) {
            this.manufacturedBy = manufacturedBy;
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

        public Integer getCategory() {
            return category;
        }

        public void setCategory(Integer category) {
            this.category = category;
        }

    }

    public class Variant {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("quantity")
        @Expose
        private String quantity;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }


    }
}
