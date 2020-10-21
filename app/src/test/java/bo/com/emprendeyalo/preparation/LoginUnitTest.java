package bo.com.emprendeyalo.preparation;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Rule;
import org.junit.Test;

import bo.com.emprendeyalo.preparation.models.users.UserProfile;
import bo.com.emprendeyalo.preparation.repository.MockRepository;
import bo.com.emprendeyalo.preparation.repository.RepositoryImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LoginUnitTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void loginWithEmailAndPasswordRegular() {
        String email = "test@email.com";
        String password = "pass123";

        RepositoryImpl repository = new MockRepository();
        repository.loginWithEmailPassword(email, password)
                .observeForever(user -> {
                    assertNull(user.getException());//No errors
                    assertEquals(user.getData().getProfile(), UserProfile.REGULAR);
                });
    }
}
