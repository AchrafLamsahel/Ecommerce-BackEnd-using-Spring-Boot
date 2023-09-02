package ma.ecommerce.dto;

import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TokenDTO implements Serializable {
        private String username;
        private String jwttoken;
        private List<String> roles=new ArrayList<>();

    }
