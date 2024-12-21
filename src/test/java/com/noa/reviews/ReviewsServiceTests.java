package com.noa.reviews;

import com.noa.reviews.dtos.CreateReviewRequest;
import com.noa.reviews.dtos.UpdateReviewRequest;
import com.noa.reviews.entities.Review;
import com.noa.reviews.repositories.ReviewRepository;
import com.noa.reviews.repositories.SyndicationRepository;
import com.noa.reviews.services.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReviewsServiceTests {
	@Mock
	private ReviewRepository reviewRepository;

	@Mock
	private SyndicationRepository syndicationRepository;

	@InjectMocks
	private ReviewService reviewService;

	@Test
	public void testCreateReview() {
		CreateReviewRequest request = new CreateReviewRequest();
		request.setContent("Great product!");
		request.setRating(5);
		request.setStoreId(1L);
		request.setProductId(2001L);
		request.setRequestTime(LocalDateTime.now());

		when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> invocation.getArgument(0));

		boolean result = reviewService.createReview(request);

		assertTrue(result);
		verify(reviewRepository, times(1)).save(any(Review.class));
	}

	@Test
	public void testUpdateReview() {
		Long reviewId = 1L;
		UpdateReviewRequest request = new UpdateReviewRequest();
		request.setContent("Updated content");
		request.setRating(4);
		request.setRequestTime(LocalDateTime.now());

		Review existingReview = new Review();
		existingReview.setId(reviewId);

		when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(existingReview));
		when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> invocation.getArgument(0));

		boolean result = reviewService.updateReview(reviewId, request);

		assertTrue(result);
		verify(reviewRepository, times(1)).findById(reviewId);
		verify(reviewRepository, times(1)).save(existingReview);
		assertEquals("Updated content", existingReview.getContent());
		assertEquals(4, existingReview.getRating());
	}

	@Test
	public void testDeleteReview() {
		Long reviewId = 1L;

		when(reviewRepository.existsById(reviewId)).thenReturn(true);
		doNothing().when(reviewRepository).deleteById(reviewId);

		boolean result = reviewService.deleteReview(reviewId);

		assertTrue(result);
		verify(reviewRepository, times(1)).existsById(reviewId);
		verify(reviewRepository, times(1)).deleteById(reviewId);
	}

	@Test
	public void testGetReviewsByStoreId() {
		Long storeId = 1L;
		Review review1 = new Review();
		review1.setContent("Review 1");
		Review review2 = new Review();
		review2.setContent("Review 2");

		when(reviewRepository.findAllReviewsByStoreIdAndSyndications(storeId)).thenReturn(Arrays.asList(review1, review2));

		List<String> reviews = reviewService.getReviewsByStoreId(storeId);

		assertEquals(2, reviews.size());
		assertTrue(reviews.contains("Review 1"));
		assertTrue(reviews.contains("Review 2"));
		verify(reviewRepository, times(1)).findAllReviewsByStoreIdAndSyndications(storeId);
	}
}
