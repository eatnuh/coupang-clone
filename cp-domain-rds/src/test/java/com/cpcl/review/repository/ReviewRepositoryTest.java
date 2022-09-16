package com.cpcl.review.repository;

import com.cpcl.RepositoryTest;
import com.cpcl.product.Product;
import com.cpcl.product.repository.ProductRepository;
import com.cpcl.product.type.ProductCategory;
import com.cpcl.review.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("ReviewRepository 테스트")
class ReviewRepositoryTest extends RepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;
    private Review givenReview;

    @BeforeEach
    void setUp() {
        Product givenProduct = Product.builder()
                .name("A product")
                .productCategory(ProductCategory.DIGITAL_VIDEO)
                .build();

        givenReview = Review.builder()
                .rating(10)
                .content("좋아요")
                .product(givenProduct)
                .build();
    }

    @DisplayName("Review 저장")
    @Test
    void save() {
        //given
        //when
        Review savedReview = reviewRepository.save(givenReview);
        //then
        assertThat(savedReview.getId()).isNotNull();
    }

    @DisplayName("Review id 조회")
    @Test
    void findById() {
        //given
        Review savedReview = reviewRepository.save(givenReview);
        //when
        Optional<Review> findReview = reviewRepository.findById(savedReview.getId());
        //then
        assertThat(findReview).containsSame(savedReview);
    }

    @DisplayName("Review Search 조건 조회")
    @Test
    void searchReview() {
        //given
        Product savedProduct = productRepository.save(givenReview.getProduct());
        reviewRepository.save(givenReview);
        //when
        List<Review> findReviewList = reviewRepository.searchReview(
                savedProduct.getId()
                , PageRequest.of(0, 10)
        );
        //then
        assertThat(findReviewList).isNotEmpty();
        assertThat(findReviewList.get(0)).isEqualTo(givenReview);
    }


}