package com.truxxkart.sellerservice_v1;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dq2ajr3ih");
        config.put("api_key", "981163776419521");
        config.put("api_secret", "HVK_SffLCMdA6UqtZWzI9Mdj7yg");
        return new Cloudinary(config);
    }
}
