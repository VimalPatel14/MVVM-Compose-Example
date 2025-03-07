package com.vimal.myapplication

import com.vimal.myapplication.model.Post
import com.vimal.myapplication.usecases.GetPostsUseCase
import com.vimal.myapplication.viewModel.PostViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vimal.myapplication.model.user.Address
import com.vimal.myapplication.model.user.Company
import com.vimal.myapplication.model.user.Geo
import com.vimal.myapplication.model.user.User
import com.vimal.myapplication.usecases.GetUsersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
class PostViewModelTest {

    // Rule to ensure LiveData updates happen synchronously
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Mocks
    private val getPostsUseCase: GetPostsUseCase = mockk()
    private val getUsersUseCase: GetUsersUseCase = mockk()

    private lateinit var postViewModel: PostViewModel

    // Test Coroutine Dispatcher
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        // Set the Main dispatcher to the test dispatcher for ViewModel's coroutine calls
        Dispatchers.setMain(testDispatcher)

        // Initialize the ViewModel with the mocked use cases
        postViewModel = PostViewModel(getPostsUseCase, getUsersUseCase)
    }

    @After
    fun tearDown() {
        // Reset the Main dispatcher after tests
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test fetchPosts success`() = runBlockingTest {
        // Given
        val mockPosts = listOf(
            Post(id = 1, title = "Post 1", body = "Body 1"),
            Post(id = 2, title = "Post 2", body = "Body 2")
        )
        coEvery { getPostsUseCase() } returns flowOf(mockPosts)

        // Wait for coroutines to finish executing
        advanceUntilIdle()

        // Then
        assertEquals(mockPosts, postViewModel.posts.first())
        assertEquals(false, postViewModel.isPostLoading.first()) // Ensure loading is false after fetching
    }

    @Test
    fun `test fetchUsers success`() = runBlockingTest {
        // Given
        val mockUsers = listOf(
            User(
                id = 1,
                name = "Leanne Graham",
                username = "Bret",
                email = "Sincere@april.biz",
                address = Address(
                    street = "Kulas Light",
                    suite = "Apt. 556",
                    city = "Gwenborough",
                    zipcode = "92998-3874",
                    geo = Geo(lat = "-37.3159", lng = "81.1496")
                ),
                phone = "1-770-736-8031 x56442",
                website = "hildegard.org",
                company = Company(
                    name = "Romaguera-Crona",
                    catchPhrase = "Multi-layered client-server neural-net",
                    bs = "harness real-time e-markets"
                )
            )
        )
        coEvery { getUsersUseCase() } returns flowOf(mockUsers)

        // Wait for coroutines to finish executing
        advanceUntilIdle()

        // Then
        assertEquals(mockUsers, postViewModel.users.first())
        assertEquals(false, postViewModel.isUserLoading.first()) // Ensure loading is false after fetching
    }

    @Test
    fun `test isPostLoading state when fetching posts`() = runBlockingTest {
        // Given
        val mockPosts = listOf(Post(id = 1, title = "Post 1", body = "Body 1"))
        coEvery { getPostsUseCase() } returns flowOf(mockPosts)

        // Then
        assertEquals(true, postViewModel.isPostLoading.first()) // Initially loading should be true
        advanceUntilIdle()
        assertEquals(false, postViewModel.isPostLoading.first()) // After fetching, loading should be false
    }

    @Test
    fun `test isUserLoading state when fetching users`() = runBlockingTest {
        // Given
        val mockUsers = listOf(
            User(
                id = 1,
                name = "Leanne Graham",
                username = "Bret",
                email = "Sincere@april.biz",
                address = Address(
                    street = "Kulas Light",
                    suite = "Apt. 556",
                    city = "Gwenborough",
                    zipcode = "92998-3874",
                    geo = Geo(lat = "-37.3159", lng = "81.1496")
                ),
                phone = "1-770-736-8031 x56442",
                website = "hildegard.org",
                company = Company(
                    name = "Romaguera-Crona",
                    catchPhrase = "Multi-layered client-server neural-net",
                    bs = "harness real-time e-markets"
                )
            )
        )
        coEvery { getUsersUseCase() } returns flowOf(mockUsers)

        // Then
        assertEquals(true, postViewModel.isUserLoading.first()) // Initially loading should be true
        advanceUntilIdle()
        assertEquals(false, postViewModel.isUserLoading.first()) // After fetching, loading should be false
    }
}