package jwt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VendorDTO {

    private int id;
    private String name;
    private String phoneNumber;
    private String email;

}
