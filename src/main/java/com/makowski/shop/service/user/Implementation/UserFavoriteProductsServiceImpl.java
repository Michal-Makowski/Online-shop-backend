package com.makowski.shop.service.user.Implementation;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.product.Product;
import com.makowski.shop.entity.user.User;
import com.makowski.shop.entity.user.UserFavoriteProducts;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.user.UserFavoriteProductsRepository;
import com.makowski.shop.security.MySecurityContextHolder;
import com.makowski.shop.service.product.ProductService;
import com.makowski.shop.service.user.UserFavoriteProductsService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserFavoriteProductsServiceImpl implements UserFavoriteProductsService {

    private UserFavoriteProductsRepository userFavoriteProductsRepository;
    private ProductService productService;
    private MySecurityContextHolder mySecurityContextHolder;

    @Override
    public UserFavoriteProducts createUserFavoriteProducts(User user) {
        UserFavoriteProducts userFavoriteProducts = new UserFavoriteProducts();
        userFavoriteProducts.setUser(user);
        return userFavoriteProductsRepository.save(userFavoriteProducts);
    }

    @Override
    public UserFavoriteProducts getFavoriteProductsByUserId(Long userId) {
        mySecurityContextHolder.userIsValid(userId);
        return userFavoriteProductsRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId, User.class, UserFavoriteProducts.class));
    }

    @Override
    public UserFavoriteProducts addProductToUserFavoriteProducts(Long userId, Long productId) {
        mySecurityContextHolder.userIsValid(userId);
        Product product = productService.getProduct(productId);
        UserFavoriteProducts userFavoriteProducts = getFavoriteProductsByUserId(userId);
        userFavoriteProducts.getProducts().add(product);
        return userFavoriteProductsRepository.save(userFavoriteProducts);
    }

    @Override
    public void deleteProductFromUserFavoriteProducts(Long userId, Long productId) {
        mySecurityContextHolder.userIsValid(userId);
        UserFavoriteProducts userFavoriteProducts = getFavoriteProductsByUserId(userId);
        Product product = productService.getProduct(productId);
        userFavoriteProducts.getProducts().remove(product);
        userFavoriteProductsRepository.save(userFavoriteProducts);
    }

    @Override
    public void deleteAllProductFromUserFavoriteProducts(Long userId) {
        mySecurityContextHolder.userIsValid(userId);
        UserFavoriteProducts userFavoriteProducts = getFavoriteProductsByUserId(userId);
        userFavoriteProducts.getProducts().clear();
        userFavoriteProductsRepository.save(userFavoriteProducts);
    }

}
