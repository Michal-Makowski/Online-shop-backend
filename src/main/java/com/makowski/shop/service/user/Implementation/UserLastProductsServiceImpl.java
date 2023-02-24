package com.makowski.shop.service.user.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.product.Product;
import com.makowski.shop.entity.user.User;
import com.makowski.shop.entity.user.UserLastProducts;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.user.UserLastProductsRepository;
import com.makowski.shop.security.MySecurityContextHolder;
import com.makowski.shop.service.product.ProductService;
import com.makowski.shop.service.user.UserLastProductsService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserLastProductsServiceImpl implements UserLastProductsService {

    private UserLastProductsRepository userLastProductsRepository;
    private ProductService productService;
    private MySecurityContextHolder mySecurityContextHolder;

    @Override
    public UserLastProducts createUserLastProducts(User user) {
        UserLastProducts userLastProducts = new UserLastProducts();
        userLastProducts.setUser(user);
        return userLastProductsRepository.save(userLastProducts);
    }

    @Override
    public UserLastProducts getLastProductsByUserId(Long userId) {
        mySecurityContextHolder.userIsValid(userId);
        return userLastProductsRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId, User.class, UserLastProducts.class));
    }

    @Override
    public UserLastProducts addProductToUserLastProducts(Long userId, Long productId) {
        mySecurityContextHolder.userIsValid(userId);
        Product product = productService.getProduct(productId);
        UserLastProducts userLastProducts = getLastProductsByUserId(userId);
        List<Product> products = userLastProducts.getProducts();
        if (products.size() < 10) {
            products.add(product);
        } else {
            products.remove(0);
            products.add(product);
        }
        return userLastProductsRepository.save(userLastProducts);
    }

    @Override
    public void deleteProductFromUserLastProducts(Long userId, Long productId) {
        mySecurityContextHolder.userIsValid(userId);
        UserLastProducts userLastProducts = getLastProductsByUserId(userId);
        Product product = productService.getProduct(productId);
        userLastProducts.getProducts().remove(product);
        userLastProductsRepository.save(userLastProducts);
    }

    @Override
    public void deleteAllProductFromUserLastProducts(Long userId) {
        mySecurityContextHolder.userIsValid(userId);
        UserLastProducts userLastProducts = getLastProductsByUserId(userId);
        userLastProducts.getProducts().clear();
        userLastProductsRepository.save(userLastProducts);
    }

}
