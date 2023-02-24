package com.makowski.shop.service.user.Implementation;

import org.springframework.stereotype.Service;

import com.makowski.shop.entity.product.Product;
import com.makowski.shop.entity.user.User;
import com.makowski.shop.entity.user.UserCart;
import com.makowski.shop.exception.EntityNotFoundException;
import com.makowski.shop.repository.user.UserCartRepository;
import com.makowski.shop.security.MySecurityContextHolder;
import com.makowski.shop.service.product.ProductService;
import com.makowski.shop.service.user.UserCartService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserCartServiceImpl implements UserCartService {

    private UserCartRepository userCartRepository;
    private ProductService productService;
    private MySecurityContextHolder mySecurityContextHolder;

    @Override
    public UserCart createUserCart(User user) {
        UserCart userCart = new UserCart();
        userCart.setUser(user);
        return userCartRepository.save(userCart);
    }

    @Override
    public UserCart getCartByUserId(Long userId) {
        mySecurityContextHolder.userIsValid(userId);
        return userCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId, User.class, UserCart.class));
    }

    @Override
    public UserCart addProductToUserCart(Long userId, Long productId) {
        mySecurityContextHolder.userIsValid(userId);
        Product product = productService.getProduct(productId);
        UserCart userCart = getCartByUserId(userId);
        userCart.getProducts().add(product);
        return userCartRepository.save(userCart);
    }

    @Override
    public void deleteProductFromUserCart(Long userId, Long productId) {
        mySecurityContextHolder.userIsValid(userId);
        UserCart userCart = getCartByUserId(userId);
        Product product = productService.getProduct(productId);
        userCart.getProducts().remove(product);
        userCartRepository.save(userCart);
    }

    @Override
    public void deleteAllProductFromUserCart(Long userId) {
        mySecurityContextHolder.userIsValid(userId);
        UserCart userCart = getCartByUserId(userId);
        userCart.getProducts().clear();
        userCartRepository.save(userCart);
    }

}
