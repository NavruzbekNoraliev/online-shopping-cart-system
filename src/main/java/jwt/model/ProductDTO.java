package jwt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private VendorDTO vendor;
    private CategoryDTO category;
}
