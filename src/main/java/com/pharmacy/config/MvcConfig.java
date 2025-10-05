package com.pharmacy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull; // <--- ADD THIS IMPORT

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    // FIX: Add @NonNull annotation to the parameter
    public void addViewControllers(@NonNull ViewControllerRegistry registry) {

        // Explicitly map /prescriptions URL to the "prescriptions" view name
        registry.addViewController("/prescriptions").setViewName("prescriptions");

        // Add other explicit mappings here to stabilize routes
        registry.addViewController("/dashboard").setViewName("dashboard");
    }

    // NOTE: If you have other overridden methods (like addResourceHandlers),
    // they may also require @NonNull annotations.
}
