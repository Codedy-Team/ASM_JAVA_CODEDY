package Model;

import MyUtilities.DatabaseUtility;
import Service.ProductDatabaseService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public class Product {
    private int id;
    private int ProductCategoryId;
    private int RestaurantId;
    private String Name;
    private String Ingredients;
    private Double Price;
    private String Image;
    private String Country;
    private String Tag;
    private String Description;
    private Boolean Featured;

    private Date CreatedAt;
    private String CreatedBy;
    private Date UpdatedAt;
    private String UpdatedBy;

    private int Version;
    private Boolean Deleted;

    public Product(int id, int productCategoryId, int restaurantId, String name, String ingredients, Double price, String image, String country, String tag, String description, Boolean featured, Date createdAt, String createdBy, Date updatedAt, String updatedBy, int version, Boolean deleted) {
        this.id = id;
        ProductCategoryId = productCategoryId;
        RestaurantId = restaurantId;
        Name = name;
        Ingredients = ingredients;
        Price = price;
        Image = image;
        Country = country;
        Tag = tag;
        Description = description;
        Featured = featured;
        CreatedAt = createdAt;
        CreatedBy = createdBy;
        UpdatedAt = updatedAt;
        UpdatedBy = updatedBy;
        Version = version;
        Deleted = deleted;
    }

    public Product(){

    }

    public Product(int productCategoryId, int restaurantId, String name, String ingredients, Double price, String image, String country, String tag, String description, Boolean featured) {
        ProductCategoryId = productCategoryId;
        RestaurantId = restaurantId;
        Name = name;
        Ingredients = ingredients;
        Price = price;
        Image = image;
        Country = country;
        Tag = tag;
        Description = description;
        Featured = featured;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductCategoryId() {
        return ProductCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        ProductCategoryId = productCategoryId;
    }

    public int getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        RestaurantId = restaurantId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Boolean getFeatured() {
        return Featured;
    }

    public void setFeatured(Boolean featured) {
        Featured = featured;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public Date getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        UpdatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int version) {
        Version = version;
    }

    public Boolean getDeleted() {
        return Deleted;
    }

    public void setDeleted(Boolean deleted) {
        Deleted = deleted;
    }

    //region - Query SQL Methods -
    private static ProductDatabaseService getProductDatabaseService() {
        try {
            Context initContext = null;
            DataSource dataSource;

            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/asm_codedy");

            return new ProductDatabaseService(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Product> all() {
        //Cách 1: Mapping model bằng tay thủ công
        /*try {
            return getProductDatabaseService().all();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/

        //Cách 2: Tự lập trình chức năng "Auto Mapping To Model", để tái sử dụng nhiều lần
        return (List<Product>) DatabaseUtility.executeQuery_AutoMappingToModel("SELECT * FROM product WHERE deleted = false ORDER BY id DESC", Product.class.getName());
    }

    public static Product find(int id) {
        //Cách 1: Mapping model bằng tay thủ công
        /*try {
            return getProductDatabaseService().find(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/

        //Cách 2: Tự lập trình chức năng "Auto Mapping To Model", để tái sử dụng nhiều lần
        List<?> result = DatabaseUtility.executeQuery_AutoMappingToModel("SELECT * FROM product WHERE deleted = false and id = " + id, Product.class.getName());
        if (result.size() == 0) {
            return null;
        }

        return (Product) result.get(0);
    }

    public static void create(Product product) {
        try {
            //getProductDatabaseService().create(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(Product product) {
        try {
            getProductDatabaseService().update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            //getProductDatabaseService().delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Product> search(String keyword) {
        //Cách 1: Mapping model bằng tay thủ công
        /*try {
            return getProductDatabaseService().search();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/

        //Cách 2: Tự lập trình chức năng "Auto Mapping To Model", để tái sử dụng nhiều lần
        return (List<Product>) DatabaseUtility.executeQuery_AutoMappingToModel("SELECT * FROM product WHERE deleted = false and name like '%" + keyword + "%' ORDER BY id DESC", Product.class.getName());
    }
    //endregion
}
