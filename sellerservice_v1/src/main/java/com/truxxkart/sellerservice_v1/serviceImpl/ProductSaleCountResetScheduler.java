package com.truxxkart.sellerservice_v1.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.truxxkart.sellerservice_v1.repository.ProductRepository;

@Component
public class ProductSaleCountResetScheduler {
@Autowired
    private ProductRepository productRepository;

   

    /**
     * Reset monthSaleCount on the first day of every month at midnight.
     */
    @Scheduled(cron = "0 0 0 1 * ?") // First day of the month at 00:00
    public void resetMonthlySaleCount() {
        productRepository.findAll().forEach(product -> {
            product.setMonthSaleCount(0L);
            productRepository.save(product);
            System.out.println("Reset monthSaleCount for product: " + product.getName());
        });
        productRepository.flush();
    }

    /**
     * Reset weekSaleCount every Monday at midnight.
     */
    // Every Monday at 00:00
    @Scheduled(cron = "0 0 0 * * MON")
    public void resetWeeklySaleCount() {
        productRepository.findAll().forEach(product -> {
            product.setWeekSaleCount(0L);
            productRepository.save(product);
            System.out.println("Reset weekSaleCount for product: " + product.getName());
        });
        productRepository.flush();
    }
}
